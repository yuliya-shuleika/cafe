package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.DishService;
import com.yuliana.cafe.model.service.impl.DishServiceImpl;
import com.yuliana.cafe.util.FileUploader;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;


/**
 * Action command that provides adding new dishes to the menu.
 *
 * @author Yulia Shuleiko
 */
public class AddDishToMenuCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String IMAGE_FOLDER = "dishes";
    private static final String ENCODING_UTF8 = "UTF-8";
    private static final String ERROR_MESSAGE = "add_error";
    private static final int DISH_FORM_SIZE = 5;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = PagePath.DISHES_LIST_PAGE;
        Map<String, String> dishFields = new HashMap<>();
        List<FileItem> items = new ArrayList<>();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext servletContext = request.getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding(ENCODING_UTF8);
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            logger.log(Level.ERROR, "Error uploading photo.");
        }
        Iterator<FileItem> iterator = items.iterator();
        String pictureName = processFileItems(iterator, dishFields, response);
        DishService dishService = DishServiceImpl.getInstance();
        try {
            dishService.addDishToMenu(dishFields, pictureName);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
        if (dishFields.size() < DISH_FORM_SIZE) {
            request.setAttribute(AttributeName.EDIT_ERROR_MESSAGE, ERROR_MESSAGE);
            request.setAttribute(AttributeName.DISH_FIELDS, dishFields);
        }
        try {
            List<Dish> dishes = dishService.findAllDishes();
            request.setAttribute(AttributeName.DISHES_LIST, dishes);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
        return page;
    }

    /**
     * Process list of the {@code FileItem} objects.
     * Upload picture of the dish and save user's input.
     *
     * @param iterator
     * @param dishFields map of the string.
     *                   The key represents field of the form and the value is the user's input
     * @param response the {@code HttpServletResponse} object
     * @return filepath of saved dish picture
     * @throws IOException if occurs an error while sending error's code with response
     */
    private String processFileItems(Iterator<FileItem> iterator,
                                    Map<String, String> dishFields,
                                    HttpServletResponse response) throws IOException{
        String pictureName = "";
        while (iterator.hasNext()) {
            FileItem item = iterator.next();
            if (item.isFormField()) {
                String name = item.getFieldName();
                String value = null;
                try {
                    value = item.getString(ENCODING_UTF8);
                } catch (UnsupportedEncodingException e) {
                    logger.log(Level.ERROR, e);
                    response.sendError(500);
                }
                dishFields.put(name, value);
            } else {
                FileUploader fileUploader = FileUploader.getInstance();
                pictureName = fileUploader.uploadPicture(IMAGE_FOLDER, item);
            }
        }
        return pictureName;
    }
}
