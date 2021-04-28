package com.yuliana.cafe.model.service.impl;

import com.yuliana.cafe.model.dao.PromoCodeDao;
import com.yuliana.cafe.model.dao.impl.PromoCodeDaoImpl;
import com.yuliana.cafe.model.entity.PromoCode;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.PromoCodeService;
import com.yuliana.cafe.model.service.validator.PromoCodeValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the {@code PromoCodeService} interface.
 *
 * @author Yulia Shuleiko
 */
public class PromoCodeServiceImpl implements PromoCodeService {

    private static final PromoCodeServiceImpl INSTANCE = new PromoCodeServiceImpl();
    private PromoCodeDao promoCodeDao = PromoCodeDaoImpl.getInstance();
    private static final String PROMO_CODE_NAME = "promo_code_name";
    private static final String PROMO_CODE_DISCOUNT_PERCENTS = "promo_code_discount_percents";

    /**
     * Forbid creation of the new objects of the class.
     */
    private PromoCodeServiceImpl(){}

    public static PromoCodeService getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<PromoCode> findPromoCodeByName(String name) throws ServiceException {
        boolean isValid = PromoCodeValidator.isValidPromoCodeName(name);
        Optional<PromoCode> promoCodeOptional = Optional.empty();
        if (isValid) {
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
        if (isValid) {
            PromoCode promoCode = createPromoCode(promoCodeFields);
            try {
                promoCodeId = promoCodeDao.addPromoCode(promoCode);
            } catch (DaoException e) {
                throw new ServiceException(e);
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
    public void editPromoCode(Map<String, String> promoCodeFields, int promoCodeId) throws ServiceException {
        boolean isValid = PromoCodeValidator.isValidPromoCodeForm(promoCodeFields);
        if (isValid) {
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

    /**
     * Create the {@code PromoCode} object from user's input.
     *
     * @param promoCodeFields map of the string.
     *                        The key represents field of the form and the value is the user's input
     * @return the {@code PromoCode} object
     */
    private PromoCode createPromoCode(Map<String, String> promoCodeFields) {
        String name = promoCodeFields.get(PROMO_CODE_NAME);
        String discountPercents = promoCodeFields.get(PROMO_CODE_DISCOUNT_PERCENTS);
        short discount = Short.parseShort(discountPercents);
        PromoCode promoCode = new PromoCode(name, discount);
        return promoCode;
    }
}
