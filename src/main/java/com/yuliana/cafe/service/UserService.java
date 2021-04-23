package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.entity.UserStatus;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * UserServise interface provides operations with {@code User} objects and related to it.
 *
 * @author Yulia Shuleiko
 */
public interface UserService {

    /**
     * Login the user by email and password hash. Validate the fields and make a hash from the password string.
     *
     * @param loginForm map of the string.
     *                  The key represents field of the form and the value is the user's input
     * @return the {@code User} object
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    Optional<User> loginUser(Map<String, String> loginForm) throws ServiceException;

    /**
     * Register user. Validate the fields and make a hash from the password string.
     *
     * @param name name of the user
     * @param email email of the user
     * @param password the password string
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void registerUser(String name, String email, String password) throws ServiceException;

    /**
     * Find all the user.
     *
     * @return list of the users
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Find all the users sorted by email.
     *
     * @return list of the users
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<User> findUsersSortedByEmail() throws ServiceException;

    /**
     * Find the user by the part of user's email. Validate email string.
     *
     * @param email tha part of the user's email
     * @return list of the users
     * @throws ServiceException
     */
    List<User> findUsersByEmail(String email) throws ServiceException;

    /**
     * Find address of the user by user's id.
     *
     * @param userId id of the user
     * @return the {@code Address} object
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    Optional<Address> findUserAddress(int userId) throws ServiceException;

    /**
     * Edit user's information. Validate the fields.
     *
     * @param userForm map of the strings.
     *                 The key represents field of the form and the value is the user's input
     * @param avatar file path of the saved photo
     * @param user the {@code User} object
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void editUser(Map<String, String> userForm, String avatar, User user) throws ServiceException;

    /**
     * Add address to user by address's and user's ids.
     *
     * @param addressId id of the address
     * @param userId if of the user
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void addUserAddress(int addressId, int userId) throws ServiceException;

    /**
     * Update status of the user to one of this values: online, offline or blocked.
     *
     * @param userId id of the user
     * @param status the {@code UserStatus} object
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void updateStatus(int userId, UserStatus status) throws ServiceException;

    /**
     * Update role of the user to one of this values: admin or user.
     *
     * @param userId id of the user
     * @param role the {@code UserRole} object
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void updateRole(int userId, String role) throws ServiceException;

    /**
     * Define if account with the certain email exists or not.
     *
     * @param email email of the user
     * @return {@code true} if email was found otherwise {@code false}
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    boolean findEmail(String email) throws ServiceException;
}
