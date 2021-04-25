package com.yuliana.cafe.model.service;

import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.exception.ServiceException;

import java.util.Map;
import java.util.Optional;

/**
 * AddressService interface provides operations with the {@code Address} object and related to it.
 *
 * @author Yulia Shuleiko
 */
public interface AddressService {

    /**
     * Add new address. Validate the fields.
     *
     * @param addressForm map of the string.
     *                    The key represents field of the form and the value is the user's input
     * @return id of the added address
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    int addAddress(Map<String, String> addressForm) throws ServiceException;

    /**
     *
     * @param addressId id of the address
     * @return the {@code Address} object
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    Optional<Address> findAddressById(int addressId) throws ServiceException;

    /**
     * Update the address by it's id.
     *
     * @param addressForm map of the string.
     *                    The key represents field of the form and the value is the user's input
     * @param addressId id of the address
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void updateAddress(Map<String, String> addressForm, int addressId) throws ServiceException;
}
