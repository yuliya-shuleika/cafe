package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.PromoCodeDao;
import com.yuliana.cafe.dao.impl.PromoCodeDaoImpl;
import com.yuliana.cafe.entity.PromoCode;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.PromoCodeService;
import com.yuliana.cafe.service.validator.CheckoutValidator;

import java.util.Optional;

public class PromoCodeServiceImpl implements PromoCodeService {

    private static final PromoCodeServiceImpl INSTANCE = new PromoCodeServiceImpl();
    private PromoCodeDao promoCodeDao = new PromoCodeDaoImpl();

    public static PromoCodeService getInstance(){
        return INSTANCE;
    }

    @Override
    public Optional<PromoCode> findDiscountByPromoCodeName(String name) throws ServiceException{
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
}
