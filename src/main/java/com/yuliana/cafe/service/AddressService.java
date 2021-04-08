package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.exception.ServiceException;

import java.util.Map;
import java.util.Optional;

public interface AddressService {
    int addAddress(Map<String, String> addressForm) throws ServiceException;
    Optional<Address> findAddressById(int addressId) throws ServiceException;
    void updateAddress(Map<String, String> addressForm) throws ServiceException;
}
