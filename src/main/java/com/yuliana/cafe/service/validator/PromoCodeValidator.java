package com.yuliana.cafe.service.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class PromoCodeValidator extends BaseValidator{

    private static final Logger logger = LogManager.getLogger();
    private static final String FIELD_PROMO_CODE_NAME = "promo_code_name";
    private static final String FIELD_PROMO_CODE_DISCOUNT_PERCENTS = "promo_code_discount_percents";
    private static final String NAME_REGEX = "[A-Za-zА-Яа-яёЁ0-9_]{5,45}";
    private static final String DISCOUNT_PERCENTS_REGEX = "[1-9][0-9]?";

    public static boolean isValidPromoCodeForm(Map<String, String> promoCodeForm){
        boolean isValidForm = true;
        boolean isValidField;
        String value = "";
        for(String field : promoCodeForm.keySet()){
            isValidField = false;
            value = promoCodeForm.get(field);
            switch (field){
                case FIELD_PROMO_CODE_NAME:
                    isValidField = isValidField(NAME_REGEX, value);
                    break;
                case FIELD_PROMO_CODE_DISCOUNT_PERCENTS:
                    isValidField = isValidField(DISCOUNT_PERCENTS_REGEX, value);
                    break;
                default:
                    logger.log(Level.WARN, "Unknown field in the add promo code form.");
            }
            if(!isValidField){
                promoCodeForm.replace(field, "");
                isValidForm = false;
            }
        }
        return isValidForm;
    }

}
