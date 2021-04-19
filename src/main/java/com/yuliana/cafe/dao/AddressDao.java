package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.exception.DaoException;

import java.util.Optional;

public interface AddressDao extends BaseDao {

    int addAddress(Address address) throws DaoException;

    Optional<Address> findAddressById(int addressId) throws DaoException;

    void updateAddress(Address address) throws DaoException;
}
