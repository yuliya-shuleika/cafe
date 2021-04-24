package com.yuliana.cafe.model.service.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Map;

/**
 * DishValidator class's purpose is to validate the fields from dish input forms.
 *
 * @author Yulia Shuleiko
 */
public class DishValidator extends BaseValidator {

    private static final Logger logger = LogManager.getLogger();
    private static final String FIELD_DISH_NAME = "dish_name";
    private static final String FIELD_DISH_PRICE = "dish_price";
    private static final String FIELD_DISH_DISCOUNT = "dish_discount";
    private static final String FIELD_DISH_WEIGHT = "dish_weight";
    private static final String FIELD_DISH_DESCRIPTION = "dish_description";
    private static final String NAME_REGEX = "[A-Za-zА-Яа-яёЁ\\s]{5,45}";
    private static final String PRICE_REGEX = "[1-9][0-9]{0,4}\\.[0-9]?[0-9]";
    private static final String DISCOUNT_PERCENTS_REGEX = "[0-9]{1,2}";
    private static final String WEIGHT_REGEX = "[1-9][0-9]{0,5}";
    private static final String DESCRIPTION_REGEX = ".{1,300}";
    private static final String NAME_PART_REGEX = ".{1,30}";

    /**
     * Validate the fields from the dish form. Form consists of the 5 fields.
     *
     * @param dishForm map of the string.
     *                 The key represents name of the field and the value is the user's input.
     *                 Invalid fields will be removed from map.
     * @return {@code true} if all fields are valid otherwise {@code false}
     */
    public static boolean isValidDishForm(Map<String, String> dishForm) {
        boolean isValidForm = true;
        String value;
        Iterator<Map.Entry<String, String>> iterator = dishForm.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String field = entry.getKey();
            boolean isValidField = false;
            value = dishForm.get(field);
            switch (field) {
                case FIELD_DISH_NAME:
                    isValidField = isValidField(NAME_REGEX, value);
                    break;
                case FIELD_DISH_DISCOUNT:
                    isValidField = isValidField(DISCOUNT_PERCENTS_REGEX, value);
                    break;
                case FIELD_DISH_PRICE:
                    isValidField = isValidField(PRICE_REGEX, value);
                    break;
                case FIELD_DISH_WEIGHT:
                    isValidField = isValidField(WEIGHT_REGEX, value);
                    break;
                case FIELD_DISH_DESCRIPTION:
                    isValidField = isValidField(DESCRIPTION_REGEX, value);
                    break;
                default:
                    logger.log(Level.WARN, "Unknown field in the dish form.");
            }
            if (!isValidField) {
                iterator.remove();
                isValidForm = false;
            }
        }
        return isValidForm;
    }

    /**
     * Validate dish name part input.
     *
     * @param dishNamePart dish name part
     * @return {@code true} if input is valid otherwise {@code false}
     */
    public static boolean isValidDishSearch(String dishNamePart) {
        boolean isValid = isValidField(NAME_PART_REGEX, dishNamePart);
        return isValid;
    }

}
