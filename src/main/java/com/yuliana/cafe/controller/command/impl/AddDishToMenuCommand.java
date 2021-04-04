package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.DishService;
import com.yuliana.cafe.service.impl.DishServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AddDishToMenuCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    //private static final String PICTURE_EXTENSION = ".jpg";
    private static final String IMAGE_PATH = "/images/dishes/";
    private static final String DISH_NAME_PARAM = "dish_name";
    private static final String DISH_CATEGORY_PARAM = "dish_category";
    private static final String DISH_PICTURE_NAME_PARAM= "dish_picture_name";
    private static final String DISH_PRICE_PARAM = "dish_price";
    private static final String DISH_DISCOUNT_PARAM = "dish_discount";
    private static final String DISH_DESCRIPTION_PARAM = "dish_description";
    private static final String DISH_WEIGHT_PARAM = "dish_weight";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> addDishFields = new HashMap<>();
        String pictureName = "fijfio";
        addDishFields.put(DISH_NAME_PARAM, request.getParameter(DISH_NAME_PARAM));
        addDishFields.put(DISH_CATEGORY_PARAM, request.getParameter(DISH_CATEGORY_PARAM));
        addDishFields.put(DISH_PRICE_PARAM, request.getParameter(DISH_PRICE_PARAM));
        addDishFields.put(DISH_DISCOUNT_PARAM, request.getParameter(DISH_DISCOUNT_PARAM));
        addDishFields.put(DISH_DESCRIPTION_PARAM, request.getParameter(DISH_DESCRIPTION_PARAM));
        addDishFields.put(DISH_WEIGHT_PARAM, request.getParameter(DISH_WEIGHT_PARAM));
        DishService dishService = DishServiceImpl.getInstance();
        try {
            dishService.addDishToMenu(addDishFields, pictureName);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.DISHES_LIST_PAGE;
        return page;
    }
}
