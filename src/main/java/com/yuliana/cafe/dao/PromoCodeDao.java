package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.PromoCode;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface PromoCodeDao defines operations with promo_codes table.
 *
 * @author Yulia Shuleiko
 */
public interface PromoCodeDao extends BaseDao {

    /**
     * Find promo code by it's name.
     *
     * @param name name of the promo code
     * @return the {@code PromoCode} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    Optional<PromoCode> findPromoCodeByName(String name) throws DaoException;

    /**
     * Find promo code by it's id.
     *
     * @param promoCodeId
     * @return
     * @throws DaoException is thrown when occurred error with access to database
     */
    Optional<PromoCode> findPromoCodeById(int promoCodeId) throws DaoException;

    /**
     * Add new promo code.
     *
     * @param promoCode the {@code PromoCode} object
     * @return id of the added promo code
     * @throws DaoException is thrown when occurred error with access to database
     */
    int addPromoCode(PromoCode promoCode) throws DaoException;

    /**
     * Delete promo code by it's id.
     *
     * @param promoCodeId id of the promo code
     * @throws DaoException is thrown when occurred error with access to database
     */
    void deletePromoCode(int promoCodeId) throws DaoException;

    /**
     * Find promo code by the part of it's name. Case-insensitive.
     *
     * @param namePart part of the name of the promo code
     * @return the {@code PromoCode} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<PromoCode> findPromoCodesByNamePart(String namePart) throws DaoException;

    /**
     * Find all the promo codes.
     *
     * @return list of the promo codes
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<PromoCode> findAllPromoCodes() throws DaoException;

    /**
     * Edit the promo code.
     *
     * @param promoCode the {@code PromoCode} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    void editPromoCode(PromoCode promoCode) throws DaoException;

    /**
     * Find promo codes ordered by it's name.
     *
     * @return list of the promo codes
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<PromoCode> findAllPromoCodesSortedByName() throws DaoException;
}
