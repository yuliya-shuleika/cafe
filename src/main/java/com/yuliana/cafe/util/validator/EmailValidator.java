package com.yuliana.cafe.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private static final String CORRECT_EMAIL = "[A-Za-z0-9_.]{2,22}@[a-z]{2,10}\\.[a-z]{2,6}";

    public static boolean isEmail(String email){
        Pattern pattern = Pattern.compile(CORRECT_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
