package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Order;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.OrderService;
import com.yuliana.cafe.model.service.UserService;
import com.yuliana.cafe.model.service.impl.OrderServiceImpl;
import com.yuliana.cafe.model.service.impl.UserServiceImpl;
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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Action command that provides editing user's account.
 *
 * @author Yulia Shuleiko
 */
public class EditAccountCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String IMAGE_FOLDER = "avatars";
    private static final String ENCODING_UTF8 = "UTF-8";
    private static final String ERROR_MESSAGE = "edit_profile_error";
    private static final int USER_FORM_SIZE = 2;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = PagePath.ACCOUNT_PAGE;
        Map<String, String> userFields = new HashMap<>();
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
        String avatar = processFileItems(iterator, userFields, request, response);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        UserService userService = UserServiceImpl.getInstance();
        try {
            userService.editUser(userFields, avatar, user);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if (userFields.size() < USER_FORM_SIZE) {
            request.setAttribute(AttributeName.EDIT_ERROR_MESSAGE, ERROR_MESSAGE);
            request.setAttribute(AttributeName.USER_FIELDS, userFields);
        } else {
            user.setName(userFields.get(RequestParameter.USER_NAME));
            user.setEmail(userFields.get(RequestParameter.USER_EMAIL));
            user.setAvatar(avatar);
            session.setAttribute(AttributeName.USER, user);
        }
        OrderService orderService = OrderServiceImpl.getInstance();
        try {
            int userId = user.getUserId();
            List<Order> orders = orderService.findOrdersByUserId(userId);
            request.setAttribute(AttributeName.USER_ORDERS, orders);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }

    /**
     * Process list of the {@code FileItem} objects.
     * Upload avatar of the user and save user's input.
     *
     * @param iterator the {@code Iterator<T>} object
     * @param userFields map of the string.
     *                   The key represents field of the form and the value is the user's input
     * @param response the {@code HttpServletResponse} object
     * @return filepath of saved dish picture
     * @throws IOException if occurs an error while sending error's code with response
     */
    private String processFileItems(Iterator<FileItem> iterator, Map<String, String> userFields,
                                    HttpServletRequest request, HttpServletResponse response) throws IOException{
        String avatar = null;
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
                userFields.put(name, value);
            } else {
                if(item.getSize() > 0) {
                    FileUploader fileUploader = FileUploader.getInstance();
                    avatar = fileUploader.uploadPicture(IMAGE_FOLDER, item);
                }
            }
        }
        if (avatar == null) {
            avatar = request.getParameter(RequestParameter.USER_AVATAR);
        }
        return avatar;
    }
}
