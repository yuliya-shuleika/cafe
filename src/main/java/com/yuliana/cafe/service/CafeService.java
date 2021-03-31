package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;

public interface CafeService {

    List<Address> findAllCafeAddresses() throws ServiceException;
}
