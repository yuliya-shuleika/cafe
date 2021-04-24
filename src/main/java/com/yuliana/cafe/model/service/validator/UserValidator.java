package com.yuliana.cafe.model.service.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Map;

/**
 * UserValidator class's purpose is to validate the fields from user input forms.
 *
 * @author Yulia Shuleiko
 */
public class UserValidator extends BaseValidator {

    private static final Logger logger = LogManager.getLogger();
    private static final String FIELD_USER_EMAIL = "user_email";
    private static final String FIELD_USER_NAME = "user_name";
    private static final String FIELD_USER_PASSWORD = "user_password";
    private static final String EMAIL_REGEX = "[A-Za-z0-9_.]{2,22}@[a-z]{2,10}\\.[a-z]{2,6}";
    private static final String PASSWORD_REGEX = "[A-Za-z0-9_]{5,20}";
    private static final String NAME_REGEX = "[A-Za-zА-Яа-яёЁ]{3,25}";
    private static final String EMAIL_SEARCH_REGEX = "[A-Za-z0-9_.@]{1,30}";

    /**
     * Validate the fields from the registration form.
     *
     * @param registrationForm map of the string.
     *                         The key represents name of the field and the value is the user's input.
     *                         Invalid fields will be removed from map.
     * @return {@code true} if all fields are valid otherwise {@code false}
     */
    public static boolean isValidRegistrationForm(Map<String, String> registrationForm) {
        boolean isValidForm = true;
        String value;
        Iterator<Map.Entry<String, String>> iterator = registrationForm.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String field = entry.getKey();
            boolean isValidField = false;
            value = registrationForm.get(field);
            switch (field) {
                case FIELD_USER_NAME:
                    isValidField = isValidField(NAME_REGEX, value);
                    break;
                case FIELD_USER_EMAIL:
                    isValidField = isValidField(EMAIL_REGEX, value);
                    break;
                case FIELD_USER_PASSWORD:
                    isValidField = isValidField(PASSWORD_REGEX, value);
                    break;
                default:
                    logger.log(Level.WARN, "Unknown field in the registration form.");
            }
            if (!isValidField) {
                iterator.remove();
                isValidForm = false;
            }
        }
        return isValidForm;
    }

    /**
     * Validate the fields from the login form.
     *
     * @param loginForm map of the string.
     *                  The key represents name of the field and the value is the user's input.
     *                  Invalid fields will be removed from map.
     * @return {@code true} if all fields are valid otherwise {@code false}
     */
    public static boolean isValidLoginForm(Map<String, String> loginForm) {
        boolean isValidForm = true;
        String value;
        Iterator<Map.Entry<String, String>> iterator = loginForm.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String field = entry.getKey();
            boolean isValidField = false;
            value = loginForm.get(field);
            switch (field) {
                case FIELD_USER_EMAIL:
                    isValidField = isValidField(EMAIL_REGEX, value);
                    break;
                case FIELD_USER_PASSWORD:
                    isValidField = isValidField(PASSWORD_REGEX, value);
                    break;
                default:
                    logger.log(Level.WARN, "Unknown field in the login form.");
            }
            if (!isValidField) {
                iterator.remove();
                isValidForm = false;
            }
        }
        return isValidForm;
    }

    /**
     *  Validate the fields from the account edit form.
     *
     * @param userForm map of the string.
     *                 The key represents name of the field and the value is the user's input.
     *                 Invalid fields will be removed from map.
     * @return {@code true} if all fields are valid otherwise {@code false}
     */
    public static boolean isValidAccountEditForm(Map<String, String> userForm) {
        boolean isValidForm = true;
        String value;
        Iterator<Map.Entry<String, String>> iterator = userForm.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String field = entry.getKey();
            boolean isValidField = false;
            value = userForm.get(field);
            switch (field) {
                case FIELD_USER_EMAIL:
                    isValidField = isValidField(EMAIL_REGEX, value);
                    break;
                case FIELD_USER_NAME:
                    isValidField = isValidField(NAME_REGEX, value);
                    break;
                default:
                    logger.log(Level.WARN, "Unknown field in the login form.");
            }
            if (!isValidField) {
                iterator.remove();
                isValidForm = false;
            }
        }
        return isValidForm;
    }

    /**
     * Validate user's email part input.
     *
     * @param emailPart user's email part
     * @return {@code true} if input is valid otherwise {@code false}
     */
    public static boolean isValidEmailSearch(String emailPart) {
        boolean isValid = isValidField(EMAIL_SEARCH_REGEX, emailPart);
        return isValid;
    }

    /**
     * Validate user's email input.
     *
     * @param email user's email
     * @return {@code true} if input is valid otherwise {@code false}
     */
    public static boolean isValidEmail(String email) {
        boolean isValid = isValidField(EMAIL_REGEX, email);
        return isValid;
    }

    /**
     * Validate password input.
     *
     * @param password password
     * @return {@code true} if input is valid otherwise {@code false}
     */
    public static boolean isValidPassword(String password) {
        boolean isValid = isValidField(PASSWORD_REGEX, password);
        return isValid;
    }

}
