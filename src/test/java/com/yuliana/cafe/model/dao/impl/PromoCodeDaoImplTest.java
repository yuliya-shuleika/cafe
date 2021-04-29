package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.model.dao.PromoCodeDao;
import com.yuliana.cafe.model.entity.PromoCode;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

@Test
public class PromoCodeDaoImplTest {

    private PromoCodeDao promoCodeDao;
    private int promoCodeId;
    private String name;
    private String namePart;

    @BeforeTest
    public void init(){
        promoCodeDao = PromoCodeDaoImpl.getInstance();
        promoCodeId = 1;
        name = "yuliana12";
        namePart = "yu";
    }

    @Test
    public void findAllPromoCodesTest() throws DaoException {
        List<PromoCode> promoCodes = promoCodeDao.findAllPromoCodes();
        int size = promoCodes.size();
        Assert.assertNotEquals(size, 0);
    }

    @Test
    public void findPromoCodeByIdTest() throws DaoException {
        Optional<PromoCode> promoCodeOptional = promoCodeDao.findPromoCodeById(promoCodeId);
        Assert.assertNotEquals(promoCodeOptional, Optional.empty());
    }

    @Test
    public void findPromoCodeByNameTest() throws  DaoException {
        Optional<PromoCode> promoCodeOptional = promoCodeDao.findPromoCodeByName(name);
        Assert.assertNotEquals(promoCodeOptional, Optional.empty());
    }

    @Test
    public void findPromoCodeByNamePartTest() throws  DaoException {
        List<PromoCode> promoCodes = promoCodeDao.findPromoCodesByNamePart(namePart);
        int size = promoCodes.size();
        Assert.assertNotEquals(size, 0);
    }
}
