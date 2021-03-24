package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.PromoCode;
import com.yuliana.cafe.exception.DaoException;

import java.util.Optional;

public interface PromoCodeDao {

    Optional<PromoCode> findPromoCodeByName(String name) throws DaoException;
    void addPromoCode(String name, int discountPercents) throws DaoException;
    void deletePromoCode(String name) throws DaoException;
}
