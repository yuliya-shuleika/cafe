package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.PromoCode;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface PromoCodeDao extends BaseDao{

    Optional<PromoCode> findPromoCodeByName(String name) throws DaoException;
    Optional<PromoCode> findPromoCodeById(int promoCodeId) throws DaoException;
    int addPromoCode(PromoCode promoCode) throws DaoException;
    void deletePromoCode(int promoCodeId) throws DaoException;
    List<PromoCode> findPromoCodesByNamePart(String namePart) throws DaoException;
    List<PromoCode> findAllPromoCodes() throws DaoException;
    void editPromoCode(PromoCode promoCode) throws DaoException;
    List<PromoCode> findAllPromoCodesSortedByName() throws DaoException;
}
