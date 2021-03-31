package com.yuliana.cafe.service.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ReviewValidator extends BaseValidator{
    private static final Logger logger = LogManager.getLogger();
    private static final String FIELD_REVIEW_HEADER = "review_header";
    private static final String FIELD_REVIEW_TEXT = "review_text";
    private static final String HEADER_REGEX = ".{1,50}";
    private static final String TEXT_REGEX = ".{1,500}";

    public static boolean isValidReviewForm(Map<String, String> promoCodeForm){
        boolean isValidForm = true;
        boolean isValidField;
        String value;
        for(String field : promoCodeForm.keySet()){
            isValidField = false;
            value = promoCodeForm.get(field);
            switch (field){
                case FIELD_REVIEW_HEADER:
                    isValidField = isValidField(HEADER_REGEX, value);
                    break;
                case FIELD_REVIEW_TEXT:
                    isValidField = isValidField(TEXT_REGEX, value);
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
