package com.yuliana.cafe.service.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class DishValidator extends BaseValidator{

    private static final Logger logger = LogManager.getLogger();
    private static final String FIELD_DISH_NAME = "dish_name";
    private static final String FIELD_DISH_CATEGORY = "dish_category";
    private static final String FIELD_DISH_PICTURE_NAME = "dish_picture_name";
    private static final String FIELD_DISH_PRICE = "dish_price";
    private static final String FIELD_DISH_DISCOUNT = "dish_discount";
    private static final String NAME_REGEX = "[A-Za-z]{5,45}";
    private static final String CATEGORY_REGEX = "[A-Za-z]{4,30}";
    private static final String PICTURE_NAME_REGEX = "[A-Za-zА-Яа-яёЁ0-9._/]{5,50}";
    private static final String PRICE_REGEX = "[1-9][0-9]{0,4}\\.[0-9]?[1-9]";
    private static final String DISCOUNT_PERCENTS_REGEX = "[1-9][0-9]?";

    public static boolean isValidDishForm(Map<String, String> dishForm){
        boolean isValidForm = true;
        boolean isValidField;
        String value = "";
        for(String field : dishForm.keySet()){
            isValidField = false;
            value = dishForm.get(field);
            switch (field){
                case FIELD_DISH_NAME:
                    isValidField = isValidField(NAME_REGEX, value);
                    break;
                case FIELD_DISH_DISCOUNT:
                    isValidField = isValidField(DISCOUNT_PERCENTS_REGEX, value);
                    break;
                case FIELD_DISH_CATEGORY:
                    isValidField = isValidField(CATEGORY_REGEX, value);
                    break;
                case FIELD_DISH_PICTURE_NAME:
                    isValidField = isValidField(PICTURE_NAME_REGEX, value);
                    break;
                case FIELD_DISH_PRICE:
                    isValidField = isValidField(PRICE_REGEX, value);
                    break;
                default:
                    logger.log(Level.WARN, "Unknown field in the dish form.");
            }
            if(!isValidField){
                dishForm.replace(field, "");
                isValidForm = false;
            }
        }
        return isValidForm;
    }

}
