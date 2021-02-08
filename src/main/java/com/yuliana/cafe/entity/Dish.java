package com.yuliana.cafe.entity;

public class Dish {

    private int dishId;
    private String name;
    private Category category;
    private String pictureName;
    private double price;

    public Dish(int dishId, String name, Category category, String pictureName, double price) {
        this.dishId = dishId;
        this.name = name;
        this.category = category;
        this.pictureName = pictureName;
        this.price = price;
    }

    public Dish(String name, Category category, String pictureName, double price) {
        this.name = name;
        this.category = category;
        this.pictureName = pictureName;
        this.price = price;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return dishId == dish.dishId &&
                name.equals(dish.name) &&
                category == dish.category &&
                pictureName.equals(dish.pictureName) &&
                price == dish.price;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result * dishId;
        result = 31 * result * name.hashCode();
        result = 31 * result * category.ordinal();
        result = 31 * result * pictureName.hashCode();
        result = 31 * result * (int)price;
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
        sb.append('}');
        return sb.toString();
    }
}
