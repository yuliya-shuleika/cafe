package com.yuliana.cafe.model.service.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Map;

/**
 * PromoCodeValidator class's purpose is to validate the fields from promo code input forms.
 *
 * @author Yulia Shuleiko
 */
public class PromoCodeValidator extends BaseValidator {

    private static final Logger logger = LogManager.getLogger();
    private static final String FIELD_PROMO_CODE_NAME = "promo_code_name";
    private static final String FIELD_PROMO_CODE_DISCOUNT_PERCENTS = "promo_code_discount_percents";
    private static final String NAME_REGEX = "[A-Za-zА-Яа-яёЁ0-9_]{5,45}";
    private static final String DISCOUNT_PERCENTS_REGEX = "[1-9][0-9]?";

    /**
     * Validate the fields from the address form. Form consists of the 6 fields.
     *
     * @param promoCodeForm map of the string.
     *                      The key represents name of the field and the value is the user's input.
     *                      Invalid fields will be removed from map.
     * @return {@code true} if all fields are valid otherwise {@code false}
     */
    public static boolean isValidPromoCodeForm(Map<String, String> promoCodeForm) {
        boolean isValidForm = true;
        String value;
        Iterator<Map.Entry<String, String>> iterator = promoCodeForm.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String field = entry.getKey();
            boolean isValidField = false;
            value = promoCodeForm.get(field);
            switch (field) {
                case FIELD_PROMO_CODE_NAME:
                    isValidField = isValidField(NAME_REGEX, value);
                    break;
                case FIELD_PROMO_CODE_DISCOUNT_PERCENTS:
                    isValidField = isValidField(DISCOUNT_PERCENTS_REGEX, value);
                    break;
                default:
                    logger.log(Level.WARN, "Unknown field in the add promo code form.");
            }
            if (!isValidField) {
                iterator.remove();
                isValidForm = false;
            }
        }
        return isValidForm;
    }
    /**
     * Validate promo code name input.
     *
     * @param promoCode promo code name
     * @return {@code true} if input is valid otherwise {@code false}
     */
    public static boolean isValidPromoCode(String promoCode) {
        boolean isValid = isValidField(NAME_REGEX, promoCode);
        return isValid;
    }
}
