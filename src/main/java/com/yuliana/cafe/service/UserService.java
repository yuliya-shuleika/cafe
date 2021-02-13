package com.yuliana.cafe.service;

import com.yuliana.cafe.dao.DaoException;
import com.yuliana.cafe.dao.UserDao;
import com.yuliana.cafe.dao.impl.UserDaoImpl;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.entity.UserRole;
import com.yuliana.cafe.util.PasswordEncryptor;
import com.yuliana.cafe.util.validator.EmailValidator;
import com.yuliana.cafe.util.validator.PasswordValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {

    private static final Logger logger = LogManager.getLogger();
    private UserDao userDao;
    private User user;

    public UserService(){
        userDao = new UserDaoImpl();
    }

    public User loginUser(String email, String password){
        if(!PasswordValidator.isPassword(password) || !EmailValidator.isEmail(email)){
            return null;
        }
        String passwordHash = PasswordEncryptor.encrypt(password);
        try {
            user = userDao.login(email, passwordHash);
        }catch (DaoException e){
            logger.log(Level.ERROR, "Database error.");
        }
        return user;
    }

    public void registerUser(String name, String email, String password){
        User user = new User(name, email,  UserRole.USER);
        String passwordHash = PasswordEncryptor.encrypt(password);
        try {
            userDao.register(user, passwordHash);
        }catch (DaoException e){
            logger.log(Level.ERROR, "Database error.");
        }
    }
}
