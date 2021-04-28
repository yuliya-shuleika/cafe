package com.yuliana.cafe.model.service.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Test
public class PromoCodeValidatorTest {

    private Map<String, String> promoCodeFields;

    @BeforeTest
    public void init(){
        promoCodeFields = new HashMap<>();
        promoCodeFields.put("promo_code_name", "yuliana_13_lalala");
        promoCodeFields.put("promo_code_discount_percents", "23");
    }

    @Test
    public void isValidPromoCodeFormTest(){
        boolean isValid = PromoCodeValidator.isValidPromoCodeForm(promoCodeFields);
        Assert.assertTrue(isValid);
    }

    @Test
    public void isValidPromoCodeTest(){
        String name = "lalalalal12552";
        boolean isValid = PromoCodeValidator.isValidPromoCodeName(name);
        Assert.assertTrue(isValid);
    }
}


