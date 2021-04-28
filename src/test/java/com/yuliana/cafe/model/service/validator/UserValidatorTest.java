package com.yuliana.cafe.model.service.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Test
public class UserValidatorTest {

    private Map<String, String> accountFields;
    private Map<String, String> registerFields;

    @BeforeTest
    public void init(){
        accountFields = new HashMap<>();
        registerFields = new HashMap<>();
        accountFields.put("user_email","yuliana@gmail.com");
        accountFields.put("user_name", "Yuliana");
        registerFields.put("user_email","yuliana@gmail.com");
        registerFields.put("user_name", "Yuliana");
        registerFields.put("user_password", "lala61217A");
    }

    @Test
    public void isValidAccountEditFormTest() {
        boolean isValid = UserValidator.isValidAccountEditForm(accountFields);
        Assert.assertTrue(isValid);
    }

    @Test
    public void isValidRegisterFormTest() {
        boolean isValid = UserValidator.isValidRegistrationForm(registerFields);
        Assert.assertTrue(isValid);
    }

    @Test
    public void isValidEmailTest() {
        String email = "yuliana@gmail.com";
        boolean isValid = UserValidator.isValidEmail(email);
        Assert.assertTrue(isValid);
    }

    @Test
    public void isValidPasswordTest() {
        String password = "lala2314Aa";
        boolean isValid = UserValidator.isValidPassword(password);
        Assert.assertTrue(isValid);
    }
}
