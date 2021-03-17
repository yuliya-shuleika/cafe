package com.yuliana.cafe.util.validator;

import java.util.Map;

public class CheckoutValidator {

    private static final String CITY_REGEX = "[A-Za-zА-Яа-я][a-zа-я]{1,30}";
    private static final String STREET_REGEX = "[A-Za-zА-Яа-я][a-zа-я]{1,30}";
    private static final String HOUSE_REGEX = "[1-9][0-9]{0,3}";
    private static final String ENTRANCE_REGEX = "[1-9][0-9]{0,2}";
    private static final String FLOOR_REGEX = "(-|[1-9])[0-9]{0,3}";
    private static final String FLAT_REGEX = "[1-9][0-9]{0,5}";
    private static final String PROMO_CODE_REGEX = "[1-9][0-9]{0,5}";

    public static boolean isAddressFormValid(Map<String, String> addressForm){
        return false;
    }
}
