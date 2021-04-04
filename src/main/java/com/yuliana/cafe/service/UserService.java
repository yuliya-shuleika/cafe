package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> loginUser(String email, String password) throws ServiceException;
    void registerUser(String name, String email, String password) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    List<User> findUsersSortedByName() throws ServiceException;
    List<User> findUsersByEmail(String email) throws ServiceException;
    void blockUser(int userId) throws ServiceException;
    Optional<Address> findUserAddress(int userId) throws ServiceException;
}
