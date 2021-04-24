package com.yuliana.cafe.model.dao;

import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;

public interface CafeDao extends BaseDao {

    List<Address> findAllCafeAddresses() throws DaoException;
}
