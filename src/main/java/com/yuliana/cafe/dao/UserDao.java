package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.entity.UserRole;
import com.yuliana.cafe.entity.UserStatus;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface UserDao defines operations with users table and the related tables
 *
 * @author Yulia Shuleiko
 */
public interface UserDao extends BaseDao {

    /**
     *
     * Register the user. Add information about the new user like email, name and password.
     * Password saved in the encrypted view.
     *
     * @param user the {@code User} object
     * @param password encrypted password of the user
     * @throws DaoException is thrown when occurred error with access to database
     */
    void register(User user, String password) throws DaoException;

    /**
     *
     * Login the user by email and password hash.
     *
     * @param email user's email
     * @param password hash of the user's password
     * @return the {@code User} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    Optional<User> login(String email, String password) throws DaoException;

    /**
     *
     * Update user's status to the one of the values: online, offline or blocked.
     *
     * @param userId id of the user
     * @param status the {@code UserStatus} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    void updateStatus(int userId, UserStatus status) throws DaoException;

    /**
     *
     * Update user's role to the one of two values: admin or user.
     *
     * @param userId id of the user
     * @param role the {@code UserRole} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    void updateRole(int userId, UserRole role) throws DaoException;

    /**
     *
     * Find all the users.
     *
     * @return list of the users
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<User> findAllUsers() throws DaoException;

    /**
     *
     * Find all the users ordered by the email.
     *
     * @return list of the users
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<User> findUsersSortedByEmail() throws DaoException;

    /**
     *
     * Find users that email contains certain sequence of symbols. Case-insensitive.
     *
     * @param email part of the email of the user
     * @return list of the users
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<User> findUsersByEmail(String email) throws DaoException;

    Optional<User> findUserById(int userId) throws DaoException;

    /**
     *
     * Find address object of the user by user's id.
     *
     * @param userId id of the user
     * @return the {@code Address} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    Optional<Address> findUserAddress(int userId) throws DaoException;

    /**
     *
     * Update user.
     *
     * @param user the {@code User} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    void updateUser(User user) throws DaoException;

    /**
     *
     * Define if table users contains email or not.
     *
     * @param email email of the user
     * @return <code>true</code> if email was found otherwise <code>false</code>
     * @throws DaoException is thrown when occurred error with access to database
     */
    boolean findEmail(String email) throws DaoException;

    /**
     *
     * Add address to user by address's and user's ids.
     *
     * @param addressId id of the address
     * @param userId id of the user
     * @throws DaoException is thrown when occurred error with access to database
     */
    void addUserAddress(int addressId, int userId) throws DaoException;
}