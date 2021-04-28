package com.yuliana.cafe.model.service.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Test
public class ReviewValidatorTest {

    private Map<String, String> reviewFields;

    @BeforeTest
    public void init(){
        reviewFields = new HashMap<>();
        reviewFields.put("review_header", "Header of the review!!!!!");
        reviewFields.put("review_text", "Text of the review!!!!\r\n One more paragraph of the review!!!!");
    }

    @Test
    public void isValidReviewFormTest(){
        boolean isValid = ReviewValidator.isValidReviewForm(reviewFields);
        Assert.assertTrue(isValid);
    }

    @Test
    public void isValidPromoCodeTest(){
        String headerPart = "Header of";
        boolean isValid = ReviewValidator.isValidReviewSearch(headerPart);
        Assert.assertTrue(isValid);
    }
}
