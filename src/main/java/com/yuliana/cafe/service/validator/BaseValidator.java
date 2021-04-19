package com.yuliana.cafe.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseValidator {

    protected static boolean isValidField(String regex, String statement) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(statement);
        return matcher.matches();
    }

}
