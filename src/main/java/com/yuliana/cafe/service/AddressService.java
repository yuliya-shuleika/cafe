package com.yuliana.cafe.service;

import com.yuliana.cafe.exception.ServiceException;

public interface AddressService {
    void addAddress(String city, String street, short house, short entrance, short floor, short flat) throws ServiceException;
}
