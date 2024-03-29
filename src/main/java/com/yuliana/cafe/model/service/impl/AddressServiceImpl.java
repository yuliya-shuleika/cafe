package com.yuliana.cafe.model.service.impl;

import com.yuliana.cafe.model.dao.AddressDao;
import com.yuliana.cafe.model.dao.impl.AddressDaoImpl;
import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.AddressService;
import com.yuliana.cafe.model.service.validator.AddressValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the {@code AddressService} interface.
 *
 * @author Yulia Shuleiko
 */
public class AddressServiceImpl implements AddressService {

    private static final AddressServiceImpl INSTANCE = new AddressServiceImpl();
    private static final AddressDao addressDao = AddressDaoImpl.getInstance();
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String ENTRANCE = "entrance";
    private static final String HOUSE = "house";
    private static final String FLOOR = "floor";
    private static final String FLAT = "flat";

    /**
     * Forbid creation of the new objects of the class.
     */
    private AddressServiceImpl() {
    }

    /**
     * Getter method of the instance of the {@code AddressServiceImpl} class.
     *
     * @return the {@code AddressDaoImpl} object
     */
    public static AddressServiceImpl getInstance() {
        return INSTANCE;
    }

    public int addAddress(Map<String, String> addressForm) throws ServiceException {
        int addressId = 0;
        boolean isValid = AddressValidator.isAddressFormValid(addressForm);
        if (isValid) {
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
        boolean isValid = AddressValidator.isAddressFormValid(addressForm);
        if (isValid) {
            Address address = createAddress(addressForm);
            address.setAddressId(addressId);
            try {
                addressDao.updateAddress(address);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public List<Address> findAllCafeAddresses() throws ServiceException {
        List<Address> addresses;
        try {
            addresses = addressDao.findAllCafeAddresses();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return addresses;
    }

    /**
     * Create the {@code Address} object from user's input fields.
     *
     * @param addressForm  map of the string.
     *                     The key represents field of the form and the value is the user's input
     * @return the {@code Address} object
     */
    private Address createAddress(Map<String, String> addressForm) {
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
