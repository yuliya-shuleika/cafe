package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.exception.DaoException;

import java.util.Optional;

public interface AddressDao {

    int addAddress(Address address) throws DaoException;
}
