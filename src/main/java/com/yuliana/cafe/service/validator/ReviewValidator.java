package com.yuliana.cafe.service.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Map;

public class ReviewValidator extends BaseValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final String FIELD_REVIEW_HEADER = "review_header";
    private static final String FIELD_REVIEW_TEXT = "review_text";
    private static final String HEADER_REGEX = ".{1,50}";
    private static final String TEXT_REGEX = ".{1,500}";

    public static boolean isValidReviewForm(Map<String, String> reviewForm) {
        boolean isValidForm = true;
        String value;
        Iterator<Map.Entry<String, String>> iterator = reviewForm.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String field = entry.getKey();
            boolean isValidField = false;
            value = reviewForm.get(field);
            switch (field) {
                case FIELD_REVIEW_HEADER:
                    isValidField = isValidField(HEADER_REGEX, value);
                    break;
                case FIELD_REVIEW_TEXT:
                    isValidField = isValidField(TEXT_REGEX, value);
                    break;
                default:
                    logger.log(Level.WARN, "Unknown field in the review form.");
            }
            if (!isValidField) {
                iterator.remove();
                isValidForm = false;
            }
        }
        return isValidForm;
    }

    public static boolean isValidHeader(String header) {
        boolean isValid = isValidField(HEADER_REGEX, header);
        return isValid;
    }
}