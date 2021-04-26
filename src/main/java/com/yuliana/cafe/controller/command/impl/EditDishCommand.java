package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
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
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Action command that provides editing the dish from menu.
 *
 * @author Yulia Shuleiko
 */
public class EditDishCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String IMAGE_FOLDER = "dishes";
    private static final String ENCODING_UTF8 = "UTF-8";
    private static final String ERROR_MESSAGE = "edit_error";
    private static final int DISH_FORM_SIZE = 5;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
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
        Iterator<FileItem> iter = items.iterator();
        String pictureName = null;
        String dishPicture = null;
        int dishId = 0;
        while (iter.hasNext()) {
            FileItem item = iter.next();
            if (item.isFormField()) {
                String name = item.getFieldName();
                String value = null;
                try {
                    value = item.getString(ENCODING_UTF8);
                } catch (UnsupportedEncodingException e) {
                    logger.log(Level.ERROR, e);
                }
                switch (name) {
                    case RequestParameter.DISH_ID:
                        String dishIdParam = value;
                        dishId = Integer.parseInt(dishIdParam);
                        break;
                    case RequestParameter.DISH_PICTURE:
                        dishPicture = value;
                        break;
                    default:
                        dishFields.put(name, value);
                }
            } else {
                FileUploader fileUploader = FileUploader.getInstance();
                pictureName = fileUploader.uploadPicture(IMAGE_FOLDER, item);
            }
        }
        if (pictureName == null) {
            pictureName = dishPicture;
        }
        DishService dishService = DishServiceImpl.getInstance();
        try {
            dishService.editDish(dishFields, pictureName, dishId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        try {
            List<Dish> dishes = dishService.findAllDishes();
            request.setAttribute(AttributeName.DISHES_LIST, dishes);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if (dishFields.size() < DISH_FORM_SIZE) {
            request.setAttribute(AttributeName.EDIT_ERROR_MESSAGE, ERROR_MESSAGE);
            request.setAttribute(AttributeName.DISH_FIELDS, dishFields);
            request.setAttribute(AttributeName.DISH_ID, dishId);
            request.setAttribute(AttributeName.DISH_PICTURE, pictureName);
        }
        try {
            List<Dish> dishes = dishService.findAllDishes();
            request.setAttribute(AttributeName.DISHES_LIST, dishes);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.DISHES_LIST_PAGE;
        return page;
    }
}
