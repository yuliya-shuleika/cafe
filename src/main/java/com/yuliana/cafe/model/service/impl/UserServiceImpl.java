package com.yuliana.cafe.model.service.impl;

import com.yuliana.cafe.model.dao.UserDao;
import com.yuliana.cafe.model.dao.impl.UserDaoImpl;
import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.model.entity.UserRole;
import com.yuliana.cafe.model.entity.UserStatus;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.UserService;
import com.yuliana.cafe.model.service.validator.UserValidator;
import com.yuliana.cafe.util.PasswordEncryptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final String FIELD_USER_NAME = "user_name";
    private static final String FIELD_USER_EMAIL = "user_email";
    private static final String FIELD_USER_PASSWORD = "user_password";
    private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    private static final UserDao userDao = new UserDaoImpl();

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    private UserServiceImpl() {
    }

    public Optional<User> loginUser(Map<String, String> loginFields) throws ServiceException {
        Optional<User> userOptional = Optional.empty();
        boolean isValid = UserValidator.isValidLoginForm(loginFields);
        if (isValid) {
            String email = loginFields.get(FIELD_USER_EMAIL);
            String password = loginFields.get(FIELD_USER_PASSWORD);
            String passwordHash = PasswordEncryptor.encrypt(password);
            try {
                userOptional = userDao.login(email, passwordHash);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return userOptional;
    }

    public void registerUser(String name, String email, String password) throws ServiceException {
        User user = new User(name, email, UserRole.USER, UserStatus.OFFLINE);
        String passwordHash = PasswordEncryptor.encrypt(password);
        try {
            userDao.register(user, passwordHash);
        } catch (DaoException e) {
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
    public List<User> findUsersSortedByEmail() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findUsersSortedByEmail();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public List<User> findUsersByEmail(String email) throws ServiceException {
        List<User> users;
        boolean isValid = UserValidator.isValidEmailSearch(email);
        if (isValid) {
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
    public Optional<Address> findUserAddress(int userId) throws ServiceException {
        Optional<Address> addressOptional;
        try {
            addressOptional = userDao.findUserAddress(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return addressOptional;
    }

    @Override
    public void editUser(Map<String, String> userForm, String avatar, User user) throws ServiceException {
        boolean isValid = UserValidator.isValidAccountEditForm(userForm);
        if (isValid) {
            user.setName(userForm.get(FIELD_USER_NAME));
            user.setEmail(userForm.get(FIELD_USER_EMAIL));
            user.setAvatar(avatar);
            try {
                userDao.updateUser(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public void addUserAddress(int addressId, int userId) throws ServiceException {
        try {
            userDao.addUserAddress(addressId, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateStatus(int userId, UserStatus status) throws ServiceException {
        try {
            userDao.updateStatus(userId, status);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void updateRole(int userId, String role) throws ServiceException {
        UserRole userRole = UserRole.valueOf(role);
        try {
            userDao.updateRole(userId, userRole);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean findEmail(String email) throws ServiceException {
        boolean isValid = UserValidator.isValidEmail(email);
        boolean exists = false;
        if (isValid) {
            try {
                exists = userDao.findEmail(email);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return exists;
    }
}
