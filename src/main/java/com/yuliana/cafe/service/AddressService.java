package com.yuliana.cafe.service;

import com.yuliana.cafe.exception.ServiceException;

import java.util.Map;

public interface AddressService {
    int addAddress(Map<String, String> addressForm) throws ServiceException;
}
