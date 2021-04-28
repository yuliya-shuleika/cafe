package com.yuliana.cafe.model.service.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Test
public class DishValidatorTest {

    private Map<String, String> dishFields;

    @BeforeTest
    public void init(){
        dishFields = new HashMap<>();
        dishFields.put("dish_name", "Hanabi maki");
        dishFields.put("dish_price", "12.90");
        dishFields.put("dish_discount", "51");
        dishFields.put("dish_description",
                "Лосось, креветка, сливочный сыр, авокадо, икра летучей рыбы");
        dishFields.put("dish_weight", "1532");
    }

    @Test
    public void isValidDishFormTest(){
        boolean isValid = DishValidator.isValidDishForm(dishFields);
        Assert.assertTrue(isValid);
    }

    @Test
    public void isValidDishSearchTest(){
        String namePart = "hanabi";
        boolean isValid = DishValidator.isValidDishSearch(namePart);
        Assert.assertTrue(isValid);
    }
}
