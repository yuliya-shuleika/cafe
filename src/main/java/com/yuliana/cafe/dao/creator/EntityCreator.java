package com.yuliana.cafe.dao.creator;

import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.DishCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class EntityCreator {

    public static Dish createDish(ResultSet dishData) throws SQLException {
        int dishId = dishData.getInt(1);
        String name = dishData.getString(2);
        String dishCategory = dishData.getString(3);
        DishCategory category = DishCategory.valueOf(dishCategory.toUpperCase());
        String pictureName = dishData.getString(4);
        double price = dishData.getDouble(5);
        short discount = dishData.getShort(6);
        Timestamp timestamp = dishData.getTimestamp(7);
        Date addedDate = new Date(timestamp.getTime());
        String description = dishData.getString(8);
        short weight = dishData.getShort(9);
        Dish dish = new Dish(dishId, name, category, pictureName, price, discount, addedDate, description, weight);
        return dish;
    }

    private EntityCreator(){}
}
