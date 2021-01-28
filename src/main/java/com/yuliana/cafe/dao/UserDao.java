package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.User;


public interface UserDao {

    void register(User user, String password) throws DaoException;
    User login(String email, String password) throws DaoException;
}