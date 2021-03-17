package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.exception.DaoException;

public interface AddressDao {

    int addAddress(Address address) throws DaoException;
}
