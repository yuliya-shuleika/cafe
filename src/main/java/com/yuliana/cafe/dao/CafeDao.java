package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;

public interface CafeDao extends BaseDao {

    List<Address> findAllCafeAddresses() throws DaoException;
}
