package com.yuliana.cafe.service.validator;

import org.apache.logging.log4j.Level;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckoutValidator extends BaseValidator{

    private static final Logger logger = LogManager.getLogger();
    private static final String FIELD_CITY= "city";
    private static final String FIELD_STREET = "street";
    private static final String FIELD_HOUSE = "house";
    private static final String FIELD_ENTRANCE = "entrance";
    private static final String FIELD_FLOOR = "floor";
    private static final String FIELD_FLAT = "flat";
    private static final String CITY_REGEX = "[A-Za-zА-Яа-я][a-zа-я]{1,30}";
    private static final String STREET_REGEX = "[A-Za-zА-Яа-я][a-zа-я]{1,30}";
    private static final String HOUSE_REGEX = "[1-9][0-9]{0,3}";
    private static final String ENTRANCE_REGEX = "[1-9][0-9]{0,2}";
    private static final String FLOOR_REGEX = "(-|[1-9])[0-9]{0,3}";
    private static final String FLAT_REGEX = "[1-9][0-9]{0,5}";
    private static final String PROMO_CODE_REGEX = "[1-9][0-9]{0,5}";

    public static boolean isAddressFormValid(Map<String, String> addressForm){
        boolean isValidForm = true;
        boolean isValidField = false;
        String value = "";
        for(String field : addressForm.keySet()){
            value = addressForm.get(field);
            switch (field){
                case FIELD_CITY:
                    isValidField = isValidField(CITY_REGEX, value);
                    break;
                case FIELD_STREET:
                    isValidField = isValidField(STREET_REGEX, value);
                    break;
                case FIELD_HOUSE:
                    isValidField = isValidField(HOUSE_REGEX, value);
                    break;
                case FIELD_ENTRANCE:
                    isValidField = isValidField(ENTRANCE_REGEX, value);
                    break;
                case FIELD_FLOOR:
                    isValidField = isValidField(FLOOR_REGEX, value);
                    break;
                case FIELD_FLAT:
                    isValidField = isValidField(FLAT_REGEX, value);
                    break;
                default:
                    logger.log(Level.WARN, "Unknown field in the address form.");
            }
            if(!isValidField){
                addressForm.replace(field, "");
                isValidForm = false;
            }
        }
        return isValidForm;
    }

    public static boolean isValidPromoCode(String promoCode){
        boolean isValid = isValidField(PROMO_CODE_REGEX, promoCode);
        return isValid;
    }
}
