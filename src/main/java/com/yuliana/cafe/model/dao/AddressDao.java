package com.yuliana.cafe.model.dao;

import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.exception.DaoException;

import java.util.Optional;

/**
 * The interface AddressDao defines operations with addresses table
 *
 * @author Yulia Shuleiko
 */
public interface AddressDao extends BaseDao {

    /**
     * Create address.
     *
     * @param address the {@code Address} object
     * @return id of created address
     * @throws DaoException is thrown when occurred error with access to database
     */
    int addAddress(Address address) throws DaoException;

    /**
     * Read address by id.
     *
     * @param addressId id of the address that must be found
     * @return found address object
     * @throws DaoException is thrown when occurred error with access to database
     */
    Optional<Address> findAddressById(int addressId) throws DaoException;

    /**
     * Update address.
     *
     * @param address the {@code Address} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    void updateAddress(Address address) throws DaoException;
}
