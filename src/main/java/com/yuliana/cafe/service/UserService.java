package com.yuliana.cafe.service;

import com.yuliana.cafe.dao.UserDao;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.util.validator.EmailValidator;
import com.yuliana.cafe.util.validator.PasswordValidator;

public class UserService {
    private UserDao userDao;

    public UserService(){
        userDao = new UserDao();
    }

    public User loginUser(String email, String password){
        if(!PasswordValidator.isPassword(password) || !EmailValidator.isEmail(email)){
            return null;
        }
        User user = new User(email, password);
        return userDao.login(user);
    }

    public void registerUser(String name, String email, String password){
        User user = new User(name, email, password, "user");
        userDao.register(user);
    }
}
