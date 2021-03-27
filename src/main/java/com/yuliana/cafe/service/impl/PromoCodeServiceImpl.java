package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.PromoCodeDao;
import com.yuliana.cafe.dao.impl.PromoCodeDaoImpl;
import com.yuliana.cafe.entity.PromoCode;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.PromoCodeService;
import com.yuliana.cafe.service.validator.CheckoutValidator;

import java.util.List;
import java.util.Optional;

public class PromoCodeServiceImpl implements PromoCodeService {

    private static final PromoCodeServiceImpl INSTANCE = new PromoCodeServiceImpl();
    private PromoCodeDao promoCodeDao = new PromoCodeDaoImpl();

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
    public void addPromoCode(PromoCode promoCode) throws ServiceException {
        try {
            promoCodeDao.addPromoCode(promoCode);
        } catch (DaoException e) {
            throw new ServiceException();
        }
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
    public void editPromoCode(PromoCode promoCode) throws ServiceException {
        try {
            promoCodeDao.editPromoCode(promoCode);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
