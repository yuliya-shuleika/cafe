package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.PromoCode;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PromoCodeService {

    Optional<PromoCode> findPromoCodeByName(String name) throws ServiceException;

    void deletePromoCode(int promoCodeId) throws ServiceException;

    List<PromoCode> findPromoCodesByNamePart(String namePart) throws ServiceException;

    Optional<PromoCode> findPromoCodeById(int promoCodeId) throws ServiceException;

    int addPromoCode(Map<String, String> promoCodeFields) throws ServiceException;

    List<PromoCode> findAllPromoCodes() throws ServiceException;

    void editPromoCode(Map<String, String> promoCodeFields, int promoCodeId) throws ServiceException;

    List<PromoCode> findAllPromoCodesSortedByName() throws ServiceException;

}
