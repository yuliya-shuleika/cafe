package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.model.dao.AddressDao;
import com.yuliana.cafe.model.entity.Address;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

@Test
public class AddressDaoImpTest {

    private AddressDao addressDao;

    @BeforeTest
    public void init(){
        addressDao = AddressDaoImpl.getInstance();
    }

    @DataProvider
    public static Object[][] address() {
        int addressId = 23;
        String city = "Minsk";
        String street = "Lenina";
        short house = 122;
        short entrance = 2;
        short floor = 6;
        short flat = 57;
        Address address = new Address(addressId, city, street, house, entrance, floor, flat);
        return new Object[][]{{address}};
    }

    @Test(dataProvider = "address")
    public void addAddressTest(Address address) throws DaoException{
        int addressId = addressDao.addAddress(address);
        Assert.assertNotEquals(addressId, 0);
    }

    @Test
    public void findAddressByIdTest() throws DaoException{
        int addressId = 22;
        Optional<Address> address = addressDao.findAddressById(addressId);
        boolean isPresent = address.isPresent();
        Assert.assertTrue(isPresent);
    }

}
