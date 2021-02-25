package com.yuliana.cafe.service;

public interface CartService {

    void AddItem(int userId, int dishId, int count);
    void deleteItem(int userId, int dishId, int count);
}
