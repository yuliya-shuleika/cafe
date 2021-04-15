package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.AddressDao;
import com.yuliana.cafe.dao.impl.AddressDaoImpl;
import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.AddressService;
import com.yuliana.cafe.service.validator.CheckoutValidator;

import java.util.Map;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {

    private static final AddressServiceImpl INSTANCE = new AddressServiceImpl();
    private AddressDao addressDao = new AddressDaoImpl();

    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String ENTRANCE = "entrance";
    private static final String HOUSE = "house";
    private static final String FLOOR = "floor";
    private static final String FLAT = "flat";

    private AddressServiceImpl(){}

    public static AddressServiceImpl getInstance(){
        return INSTANCE;
    }

    public int addAddress(Map<String, String> addressForm) throws ServiceException {
        int addressId = 0;
        boolean isValid = CheckoutValidator.isAddressFormValid(addressForm);
        if(isValid){
            Address address = createAddress(addressForm);
            try {
                addressId = addressDao.addAddress(address);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return addressId;
    }

    @Override
    public Optional<Address> findAddressById(int addressId) throws ServiceException {
        Optional<Address> addressOptional;
        try {
            addressOptional = addressDao.findAddressById(addressId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return addressOptional;
    }

    @Override
    public void updateAddress(Map<String, String> addressForm, int addressId) throws ServiceException {
        boolean isValid = CheckoutValidator.isAddressFormValid(addressForm);
        if(isValid){
            Address address = createAddress(addressForm);
            address.setAddressId(addressId);
            try {
                addressDao.updateAddress(address);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
    }

    private Address createAddress(Map<String, String> addressForm){
        String city = addressForm.get(CITY);
        String street = addressForm.get(STREET);
        short house = Short.parseShort(addressForm.get(HOUSE));
        short entrance = Short.parseShort(addressForm.get(ENTRANCE));
        short floor = Short.parseShort(addressForm.get(FLOOR));
        short flat = Short.parseShort(addressForm.get(FLAT));
        Address address = new Address(city, street, house, entrance, floor, flat);
        return address;
    }

}
