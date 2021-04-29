package com.yuliana.cafe.model.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract validator class that provides common methods to the validators.
 *
 * @author Yulia Shuleiko
 */
public abstract class BaseValidator {

    /**
     * Check if statement matches regular expression or not.
     *
     * @param regex regular expression
     * @param statement statement
     * @return {@code true} if statement matches regular expression otherwise {@code false}
     */
    protected static boolean isValidField(String regex, String statement) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(statement);
        return matcher.matches();
    }

}
