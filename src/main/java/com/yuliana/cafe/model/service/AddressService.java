package com.yuliana.cafe.model.service;

import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.exception.ServiceException;

import java.util.Map;
import java.util.Optional;

public interface AddressService {
    int addAddress(Map<String, String> addressForm) throws ServiceException;

    Optional<Address> findAddressById(int addressId) throws ServiceException;

    void updateAddress(Map<String, String> addressForm, int addressId) throws ServiceException;
}
