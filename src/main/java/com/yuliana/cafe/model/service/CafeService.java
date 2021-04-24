package com.yuliana.cafe.model.service;

import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;

public interface CafeService {

    List<Address> findAllCafeAddresses() throws ServiceException;
}
