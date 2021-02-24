package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;


public interface UserDao {

    void register(User user, String password) throws DaoException;
    User login(String email, String password) throws DaoException;
    List<User> getUsers() throws DaoException;
}