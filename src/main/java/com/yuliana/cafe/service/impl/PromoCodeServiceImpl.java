package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.PromoCodeDao;
import com.yuliana.cafe.dao.impl.PromoCodeDaoImpl;
import com.yuliana.cafe.entity.PromoCode;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.PromoCodeService;
import com.yuliana.cafe.service.validator.CheckoutValidator;
import com.yuliana.cafe.service.validator.PromoCodeValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PromoCodeServiceImpl implements PromoCodeService {

    private static final PromoCodeServiceImpl INSTANCE = new PromoCodeServiceImpl();
    private PromoCodeDao promoCodeDao = new PromoCodeDaoImpl();
    private static final String PROMO_CODE_NAME = "promo_code_name";
    private static final String PROMO_CODE_DISCOUNT_PERCENTS = "promo_code_discount_percents";

    public static PromoCodeService getInstance(){
        return INSTANCE;
    }

    @Override
    public Optional<PromoCode> findPromoCodeByName(String name) throws ServiceException{
        boolean isValid = CheckoutValidator.isValidPromoCode(name);
        Optional<PromoCode> promoCodeOptional = Optional.empty();
        if(isValid) {
            try {
                promoCodeOptional = promoCodeDao.findPromoCodeByName(name);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return promoCodeOptional;
    }

    @Override
    public void deletePromoCode(int promoCodeId) throws ServiceException {
        try {
            promoCodeDao.deletePromoCode(promoCodeId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<PromoCode> findPromoCodesByNamePart(String namePart) throws ServiceException {
        List<PromoCode> promoCodes;
        try {
            promoCodes = promoCodeDao.findPromoCodesByNamePart(namePart);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return promoCodes;
    }

    @Override
    public Optional<PromoCode> findPromoCodeById(int promoCodeId) throws ServiceException {
        Optional<PromoCode> promoCodeOptional;
        try {
            promoCodeOptional = promoCodeDao.findPromoCodeById(promoCodeId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return promoCodeOptional;
    }

    @Override
    public int addPromoCode(Map<String, String> promoCodeFields) throws ServiceException {
        int promoCodeId = 0;
        boolean isValid = PromoCodeValidator.isValidPromoCodeForm(promoCodeFields);
        if(isValid) {
            PromoCode promoCode = createPromoCode(promoCodeFields);
            try {
                promoCodeId = promoCodeDao.addPromoCode(promoCode);
            } catch (DaoException e) {
                throw new ServiceException();
            }
        }
        return promoCodeId;
    }

    @Override
    public List<PromoCode> findAllPromoCodes() throws ServiceException {
        List<PromoCode> promoCodes;
        try {
            promoCodes = promoCodeDao.findAllPromoCodes();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return promoCodes;
    }

    @Override
    public void editPromoCode(Map<String,String> promoCodeFields, int promoCodeId) throws ServiceException {
        boolean isValid = PromoCodeValidator.isValidPromoCodeForm(promoCodeFields);
        if(isValid) {
            PromoCode promoCode = createPromoCode(promoCodeFields);
            promoCode.setPromoCodeId(promoCodeId);
            try {
                promoCodeDao.editPromoCode(promoCode);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public List<PromoCode> findAllPromoCodesSortedByName() throws ServiceException {
        List<PromoCode> promoCodes;
        try {
            promoCodes = promoCodeDao.findAllPromoCodesSortedByName();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return promoCodes;
    }

    private PromoCode createPromoCode(Map<String, String> promoCodeFields){
        String name = promoCodeFields.get(PROMO_CODE_NAME);
        String discountPercents = promoCodeFields.get(PROMO_CODE_DISCOUNT_PERCENTS);
        short discount = Short.parseShort(discountPercents);
        PromoCode promoCode = new PromoCode(name, discount);
        return promoCode;
    }
}
