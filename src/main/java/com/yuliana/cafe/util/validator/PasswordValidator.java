package com.yuliana.cafe.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private static final String CORRECT_EMAIL = "[A-Za-z0-9_]{5,20}";

    public static boolean isPassword(String pass){
        Pattern pattern = Pattern.compile(CORRECT_EMAIL);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }
}
