package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.PromoCode;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * PromoCodeServise interface provides operations with {@code PromoCode} objects and related to it.
 *
 * @author Yulia Shuleiko
 */
public interface PromoCodeService {

    /**
     * Find promo code by it's name. Validate promo code name.
     *
     * @param name name of the promo code
     * @return the {@code PromoCode} object
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    Optional<PromoCode> findPromoCodeByName(String name) throws ServiceException;

    /**
     * Delete the promo code by it's id.
     *
     * @param promoCodeId id of the promo code
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void deletePromoCode(int promoCodeId) throws ServiceException;

    /**
     * Find promo code by it's name part. Validate user's input.
     *
     * @param namePart part of the promo code's name
     * @return list of the promo codes
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<PromoCode> findPromoCodesByNamePart(String namePart) throws ServiceException;

    /**
     * Find promo code by it's id
     *
     * @param promoCodeId id of the promo code
     * @return the {@code PromoCode} object
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    Optional<PromoCode> findPromoCodeById(int promoCodeId) throws ServiceException;

    /**
     * Add new promo code. Validate the fields.
     *
     * @param promoCodeFields map of the string.
     *      *                 The key represents field of the form and the value is the user's input
     * @return id of the added promo code
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    int addPromoCode(Map<String, String> promoCodeFields) throws ServiceException;

    /**
     * Find all the promo codes.
     *
     * @return list of the promo codes
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<PromoCode> findAllPromoCodes() throws ServiceException;

    /**
     * Edit promo code. Validate the fields.
     *
     * @param promoCodeFields map of the string.
     *      *                 The key represents field of the form and the value is the user's input
     * @param promoCodeId id of the promo code that must be edited
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void editPromoCode(Map<String, String> promoCodeFields, int promoCodeId) throws ServiceException;

    /**
     * Find all the promo codes sorted by it's name.
     *
     * @return list of the promo codes
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<PromoCode> findAllPromoCodesSortedByName() throws ServiceException;

}
