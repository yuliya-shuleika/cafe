package com.yuliana.cafe.model.service.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Test
public class AddressValidatorTest {

    private Map<String, String> addressFields;

    @BeforeTest
    public void init(){
        addressFields = new HashMap<>();
        addressFields.put("city", "Moscow");
        addressFields.put("street", "Panchenko");
        addressFields.put("house", "251");
        addressFields.put("entrance", "2");
        addressFields.put("floor", "-1");
        addressFields.put("flat", "238");
    }

    @Test
    public void isValidAddressFormTest(){
        boolean isValid = AddressValidator.isAddressFormValid(addressFields);
        Assert.assertTrue(isValid);
    }

}
