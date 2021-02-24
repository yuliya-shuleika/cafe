package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;

public interface UserService {

    User loginUser(String email, String password) throws ServiceException;
    void registerUser(String name, String email, String password) throws ServiceException;
}
