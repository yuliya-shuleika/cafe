package com.yuliana.cafe.model.service.impl;

import com.yuliana.cafe.model.dao.CafeDao;
import com.yuliana.cafe.model.dao.impl.CafeDaoImpl;
import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.CafeService;

import java.util.List;

public class CafeServiceImpl implements CafeService {

    private static final CafeServiceImpl INSTANCE = new CafeServiceImpl();
    private CafeDao cafeDao = new CafeDaoImpl();

    public static CafeServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Address> findAllCafeAddresses() throws ServiceException {
        List<Address> addresses;
        try {
            addresses = cafeDao.findAllCafeAddresses();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return addresses;
    }
}
