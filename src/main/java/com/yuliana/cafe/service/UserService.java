package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.entity.UserStatus;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    Optional<User> loginUser(Map<String, String> loginForm) throws ServiceException;

    void registerUser(String name, String email, String password) throws ServiceException;

    List<User> findAllUsers() throws ServiceException;

    List<User> findUsersSortedByEmail() throws ServiceException;

    List<User> findUsersByEmail(String email) throws ServiceException;

    Optional<Address> findUserAddress(int userId) throws ServiceException;

    void editUser(Map<String, String> userForm, String avatar, User user) throws ServiceException;

    void addUserAddress(int addressId, int userId) throws ServiceException;

    void updateStatus(int userId, UserStatus status) throws ServiceException;

    void updateRole(int userId, String role) throws ServiceException;

    boolean findEmail(String email) throws ServiceException;
}
