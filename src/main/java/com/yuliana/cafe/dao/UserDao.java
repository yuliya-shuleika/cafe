package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;
import java.util.Optional;


public interface UserDao {

    void register(User user, String password) throws DaoException;
    Optional<User> login(String email, String password) throws DaoException;
    List<User> findAllUsers() throws DaoException;
    List<User> findUsersSortedByEmail() throws DaoException;
    List<User> findUsersByEmail(String email) throws DaoException;

}