package com.yuliana.cafe.service;

import com.yuliana.cafe.dao.UserDao;
import com.yuliana.cafe.dao.impl.UserDaoImpl;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.util.validator.EmailValidator;
import com.yuliana.cafe.util.validator.PasswordValidator;

public class UserService {
    private UserDao userDao;

    public UserService(){
        userDao = new UserDaoImpl();
    }

    public User loginUser(String email, String password){
        if(!PasswordValidator.isPassword(password) || !EmailValidator.isEmail(email)){
            return null;
        }
        return userDao.login(email, password);
    }

    public void registerUser(String name, String email, String password){
        User user = new User(name, email,  "user");
        userDao.register(user, password);
    }
}
