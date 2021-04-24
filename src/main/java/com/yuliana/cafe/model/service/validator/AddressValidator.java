package com.yuliana.cafe.model.service.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Map;

/**
 * AddressValidator class's purpose is to validate the fields from address input forms.
 *
 * @author Yulia Shuleiko
 */
public class AddressValidator extends BaseValidator {

    private static final Logger logger = LogManager.getLogger();
    private static final String FIELD_CITY = "city";
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

    /**
     * Validate the fields from the address form. Form consists of the 6 fields.
     *
     * @param addressForm  map of the string.
     *                     The key represents name of the field and the value is the user's input.
     *                     Invalid fields will be removed from map.
     * @return {@code true} if all fields are valid otherwise {@code false}
     */
    public static boolean isAddressFormValid(Map<String, String> addressForm) {
        boolean isValidForm = true;
        String value;
        Iterator<Map.Entry<String, String>> iterator = addressForm.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String field = entry.getKey();
            boolean isValidField = false;
            value = addressForm.get(field);
            switch (field) {
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
            if (!isValidField) {
                iterator.remove();
                isValidForm = false;
            }
        }
        return isValidForm;
    }
}
