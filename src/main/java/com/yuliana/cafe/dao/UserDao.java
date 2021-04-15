package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.entity.UserStatus;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;
import java.util.Optional;


public interface UserDao extends BaseDao{

    void register(User user, String password) throws DaoException;
    Optional<User> login(String email, String password) throws DaoException;
    void updateStatus(int userId, UserStatus status) throws DaoException;
    List<User> findAllUsers() throws DaoException;
    List<User> findUsersSortedByEmail() throws DaoException;
    List<User> findUsersByEmail(String email) throws DaoException;
    Optional<User> findUserById(int userId) throws DaoException;
    Optional<Address> findUserAddress(int userId) throws DaoException;
    void updateUser(User user) throws DaoException;
    boolean findEmail(String email) throws DaoException;
    void addUserAddress(int addressId, int userId) throws DaoException;
}