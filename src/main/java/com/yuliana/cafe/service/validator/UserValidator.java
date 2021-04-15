package com.yuliana.cafe.service.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class UserValidator extends BaseValidator{

    private static final Logger logger = LogManager.getLogger();
    private static final String FIELD_USER_EMAIL = "user_email";
    private static final String FIELD_USER_NAME = "user_name";
    private static final String FIELD_USER_PASSWORD = "user_password";
    private static final String EMAIL_REGEX = "[A-Za-z0-9_.]{2,22}@[a-z]{2,10}\\.[a-z]{2,6}";
    private static final String PASSWORD_REGEX = "[A-Za-z0-9_]{5,20}";
    private static final String NAME_REGEX = "[A-Za-zА-Яа-яёЁ]{3,25}";
    private static final String EMAIL_SEARCH_REGEX = "[A-Za-z0-9_.@]{1,25}";

    public static boolean isValidRegistrationForm(Map<String, String> registrationForm){
        boolean isValidForm = true;
        boolean isValidField;
        String value = "";
        for(String field : registrationForm.keySet()){
            isValidField = false;
            value = registrationForm.get(field);
            switch (field){
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
            if(!isValidField){
                registrationForm.replace(field, "");
                isValidForm = false;
            }
        }
        return isValidForm;
    }

    public static boolean isValidLoginForm(Map<String, String> registrationForm){
        boolean isValidForm = true;
        boolean isValidField;
        String value = "";
        for(String field : registrationForm.keySet()){
            isValidField = false;
            value = registrationForm.get(field);
            switch (field){
                case FIELD_USER_EMAIL:
                    isValidField = isValidField(EMAIL_REGEX, value);
                    break;
                case FIELD_USER_PASSWORD:
                    isValidField = isValidField(PASSWORD_REGEX, value);
                    break;
                default:
                    logger.log(Level.WARN, "Unknown field in the login form.");
            }
            if(!isValidField){
                registrationForm.replace(field, "");
                isValidForm = false;
            }
        }
        return isValidForm;
    }

    public static boolean isValidAccountEditForm(Map<String, String> userForm){
        boolean isValidForm = true;
        boolean isValidField;
        String value = "";
        for(String field : userForm.keySet()){
            isValidField = false;
            value = userForm.get(field);
            switch (field){
                case FIELD_USER_EMAIL:
                    isValidField = isValidField(EMAIL_REGEX, value);
                    break;
                case FIELD_USER_NAME:
                    isValidField = isValidField(NAME_REGEX, value);
                    break;
                default:
                    logger.log(Level.WARN, "Unknown field in the login form.");
            }
            if(!isValidField){
                userForm.replace(field, "");
                isValidForm = false;
            }
        }
        return isValidForm;
    }

    public static boolean isValidEmailSearch(String email){
        boolean isValid = isValidField(EMAIL_SEARCH_REGEX, email);
        return isValid;
    }

}
