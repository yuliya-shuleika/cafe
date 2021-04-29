package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.model.dao.UserDao;
import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.util.PasswordEncryptor;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

@Test
public class UserDaoImplTest {

    private UserDao userDao;
    private String email;
    private String emailPart;
    private String passwordHash;
    private int userId;

    @BeforeTest
    public void init(){
        userDao = UserDaoImpl.getInstance();
        email = "anna@gmail.com";
        String password = "anna123";
        passwordHash = PasswordEncryptor.encrypt(password);
        userId = 34;
        emailPart = "yulia";
    }

    @Test
    public void findEmailTest() throws DaoException {
        boolean emailExists = userDao.findEmail(email);
        Assert.assertTrue(emailExists);
    }

    @Test
    public void loginTest() throws DaoException {
        Optional<User> userOptional = userDao.login(email, passwordHash);
        Assert.assertNotEquals(userOptional, Optional.empty());
    }

    @Test
    public void findUserAddressTest() throws DaoException {
        Optional<Address> addressOptional = userDao.findUserAddress(userId);
        Assert.assertNotEquals(addressOptional, Optional.empty());
    }

    @Test
    public void findUserById() throws DaoException {
        Optional<User> userOptional = userDao.findUserById(userId);
        Assert.assertNotEquals(userOptional, Optional.empty());
    }

    @Test
    public void findAllUsersTest() throws DaoException {
        List<User> users = userDao.findAllUsers();
        int size = users.size();
        Assert.assertNotEquals(size, 0);
    }

    @Test
    public void findUserByEmailTest() throws DaoException {
        List<User> users = userDao.findUsersByEmail(emailPart);
        int size = users.size();
        Assert.assertNotEquals(size, 0);
    }
}
