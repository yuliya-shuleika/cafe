package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.entity.UserStatus;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.dao.UserDao;
import com.yuliana.cafe.dao.impl.UserDaoImpl;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.entity.UserRole;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.UserService;
import com.yuliana.cafe.service.validator.UserValidator;
import com.yuliana.cafe.util.PasswordEncryptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    private static final UserDao userDao = new UserDaoImpl();

    public static UserServiceImpl getInstance(){
        return INSTANCE;
    }

    private UserServiceImpl(){}

    public Optional<User> loginUser(String email, String password) throws ServiceException {
        Optional<User> userOptional;
        String passwordHash = PasswordEncryptor.encrypt(password);
        try {
            userOptional = userDao.login(email, passwordHash);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
        if(userOptional.isPresent()){
            User user = userOptional.get();
            try {
                userDao.updateStatus(user.getUserId(), UserStatus.ONLINE);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return userOptional;
    }

    public void registerUser(String name, String email, String password) throws ServiceException {
        User user = new User(name, email,  UserRole.USER, UserStatus.OFFLINE);
        String passwordHash = PasswordEncryptor.encrypt(password);
        try {
            userDao.register(user, passwordHash);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public List<User> findUsersSortedByName() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findUsersSortedByEmail();
        } catch (DaoException e) {
            throw  new ServiceException(e);
        }
        return users;
    }

    @Override
    public List<User> findUsersByEmail(String email) throws ServiceException {
        List<User> users;
        boolean isValid = UserValidator.isValidEmailSearch(email);
        if(isValid) {
            try {
                users = userDao.findUsersByEmail(email);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            users = new ArrayList<>();
        }
        return users;
    }

    @Override
    public void blockUser(int userId) throws ServiceException {
        try {
            userDao.updateStatus(userId, UserStatus.BLOCKED);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Address> findUserAddress(int userId) throws ServiceException {
        Optional<Address> addressOptional;
        try {
           addressOptional = userDao.findUserAddress(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return addressOptional;
    }
}
