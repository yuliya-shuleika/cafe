package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.PromoCode;
import com.yuliana.cafe.exception.ServiceException;

import java.util.Optional;

public interface PromoCodeService {

    Optional<PromoCode> findDiscountByPromoCodeName(String name) throws ServiceException;

}
