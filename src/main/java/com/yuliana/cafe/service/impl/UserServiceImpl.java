package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.dao.UserDao;
import com.yuliana.cafe.dao.impl.UserDaoImpl;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.entity.UserRole;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.UserService;
import com.yuliana.cafe.util.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    private User user;

    public User loginUser(String email, String password) throws ServiceException {
        String passwordHash = PasswordEncryptor.encrypt(password);
        try {
            user = userDao.login(email, passwordHash);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
        return user;
    }

    public void registerUser(String name, String email, String password) throws ServiceException {
        User user = new User(name, email,  UserRole.USER);
        String passwordHash = PasswordEncryptor.encrypt(password);
        try {
            userDao.register(user, passwordHash);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
