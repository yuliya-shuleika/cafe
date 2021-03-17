package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.AddressDao;
import com.yuliana.cafe.dao.impl.AddressDaoImpl;
import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.AddressService;

public class AddressServiceImpl implements AddressService {

    private static final AddressServiceImpl INSTANCE = new AddressServiceImpl();
    private AddressDao addressDao = new AddressDaoImpl();

    public void addAddress(String city, String street, short house, short entrance, short floor, short flat) throws ServiceException{
        //add validation
        Address address = new Address(city, street, house, entrance, floor, flat);
        try {
            addressDao.addAddress(address);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
