package com.yuliana.cafe.entity;

import java.io.Serializable;

public class Dish implements Serializable {

    private int dishId;
    private String name;
    private DishCategory category;
    private String pictureName;
    private double price;
    private short discountPercents;

    public Dish(int dishId, String name, DishCategory category,
                String pictureName, double price, short discountPercents) {
        this.dishId = dishId;
        this.name = name;
        this.category = category;
        this.pictureName = pictureName;
        this.price = price;
        this.discountPercents = discountPercents;
    }

    public Dish(String name, DishCategory category, String pictureName,
                double price, short discountPercents) {
        this.name = name;
        this.category = category;
        this.pictureName = pictureName;
        this.price = price;
        this.discountPercents = discountPercents;
    }

    public Dish(){}

    public int getDishId() {
        return dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishCategory getCategory() {
        return category;
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public short getDiscountPercents() {
        return discountPercents;
    }

    public void setDiscountPercents(short discountPercents) {
        this.discountPercents = discountPercents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return dishId == dish.dishId &&
                name.equals(dish.name) &&
                category == dish.category &&
                pictureName.equals(dish.pictureName) &&
                price == dish.price &&
                discountPercents == dish.discountPercents;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result * dishId;
        result = 31 * result * name.hashCode();
        result = 31 * result * category.ordinal();
        result = 31 * result * pictureName.hashCode();
        result = 31 * result * (int)price;
        result = 31 * result * discountPercents;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dish{");
        sb.append("dishId=").append(dishId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", category=").append(category);
        sb.append(", pictureName='").append(pictureName).append('\'');
        sb.append(", price=").append(price);
        sb.append(", discountPercents=").append(discountPercents);
        sb.append('}');
        return sb.toString();
    }
}
