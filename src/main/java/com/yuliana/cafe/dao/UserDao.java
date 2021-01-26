package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.User;


public interface UserDao {

    void register(User user, String password);
    User login(String email, String password);
}