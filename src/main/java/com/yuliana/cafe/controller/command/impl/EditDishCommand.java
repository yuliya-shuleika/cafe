package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.DishService;
import com.yuliana.cafe.service.impl.DishServiceImpl;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class EditDishCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String UPLOAD_PATH = "C:\\Users\\Yulia\\IdeaProjects\\Cafe\\src\\main\\webapp\\images\\dishes\\";
    private static final String IMAGE_FOLDER = "/images/dishes/";
    private static final String ENCODING = "UTF-8";

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
            upload.setHeaderEncoding(ENCODING);
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            logger.log(Level.ERROR, "Error uploading photo.");
        }
        Iterator<FileItem> iter = items.iterator();
        String pictureName = null;
        while (iter.hasNext()) {
            FileItem item = iter.next();
            if (item.isFormField()) {
                String name = item.getFieldName();
                String value = null;
                try {
                    value = item.getString(ENCODING);
                } catch (UnsupportedEncodingException e) {
                    logger.log(Level.ERROR, e);
                }
                dishFields.put(name, value);
            } else {
                String filename = item.getName();
                if(!filename.equals("")) {
                    Path path = Paths.get(filename);
                    File uploadFile = new File(UPLOAD_PATH + path.getFileName());
                    try {
                        item.write(uploadFile);
                        pictureName = IMAGE_FOLDER + path.getFileName();
                    } catch (Exception e) {
                        logger.log(Level.ERROR, "Error saving photo.");
                    }
                }
            }
        }
        if(pictureName == null){
            pictureName = request.getParameter(RequestParameter.DISH_PICTURE);
        }
        String dishIdParam = request.getParameter(RequestParameter.DISH_ID);
        int dishId = Integer.parseInt(dishIdParam);
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
        String page = PagePath.DISHES_LIST_PAGE;
        return page;
    }
}
