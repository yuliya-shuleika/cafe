package com.yuliana.cafe.model.service.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Map;

/**
 * ReviewValidator class's purpose is to validate the fields from review input forms.
 *
 * @author Yulia Shuleiko
 */
public class ReviewValidator extends BaseValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final String FIELD_REVIEW_HEADER = "review_header";
    private static final String FIELD_REVIEW_TEXT = "review_text";
    private static final String HEADER_REGEX = ".{1,50}";
    private static final String TEXT_REGEX = ".{1,500}";

    /**
     * Validate the fields from the review form. Form consists of the 2 fields.
     *
     * @param reviewForm map of the string.
     *                   The key represents name of the field and the value is the user's input.
     *                   Invalid fields will be removed from map.
     * @return {@code true} if all fields are valid otherwise {@code false}
     */
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

    /**
     * Validate header part input.
     *
     * @param header review's header part
     * @return {@code true} if input is valid otherwise {@code false}
     */
    public static boolean isValidHeader(String header) {
        boolean isValid = isValidField(HEADER_REGEX, header);
        return isValid;
    }
}