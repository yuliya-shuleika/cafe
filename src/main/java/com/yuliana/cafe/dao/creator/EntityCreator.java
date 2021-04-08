package com.yuliana.cafe.dao.creator;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.DishCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        Date addedDate = dishData.getDate(7);
        String description = dishData.getString(8);
        short weight = dishData.getShort(9);
        Dish dish = new Dish(dishId, name, category, pictureName, price, discount, addedDate, description, weight);
        return dish;
    }

    public static Address createAddress(ResultSet result) throws SQLException{
        int addressId = result.getInt(1);
        String city = result.getString(2);
        String street = result.getString(3);
        short house = result.getShort(4);
        short entrance = result.getShort(5);
        short floor = result.getShort(6);
        short flat = result.getShort(7);
        Address address = new Address(city, street, house, entrance, floor, flat);
        return address;
    }

    private EntityCreator(){}
}
