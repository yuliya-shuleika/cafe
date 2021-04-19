package com.yuliana.cafe.entity;

import java.io.Serializable;
import java.util.Date;

public class Dish implements Serializable {

    private int dishId;
    private String name;
    private DishCategory category;
    private String pictureName;
    private double price;
    private short discountPercents;
    private Date addedDate;
    private String description;
    private short weight;

    public Dish(int dishId, String name, DishCategory category, String pictureName,
                double price, short discountPercents, Date addedDate, String description, short weight) {
        this.dishId = dishId;
        this.name = name;
        this.category = category;
        this.pictureName = pictureName;
        this.price = price;
        this.discountPercents = discountPercents;
        this.addedDate = addedDate;
        this.description = description;
        this.weight = weight;
    }

    public Dish(String name, DishCategory category, String pictureName, double price,
                short discountPercents, Date addedDate, String description, short weight) {
        this.name = name;
        this.category = category;
        this.pictureName = pictureName;
        this.price = price;
        this.discountPercents = discountPercents;
        this.addedDate = addedDate;
        this.description = description;
        this.weight = weight;
    }

    public Dish() {
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
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

    public short getDiscountPercents() {
        return discountPercents;
    }

    public void setDiscountPercents(short discountPercents) {
        this.discountPercents = discountPercents;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getWeight() {
        return weight;
    }

    public void setWeight(short weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (dishId != dish.dishId) return false;
        if (Double.compare(dish.price, price) != 0) return false;
        if (discountPercents != dish.discountPercents) return false;
        if (weight != dish.weight) return false;
        if (!name.equals(dish.name)) return false;
        if (category != dish.category) return false;
        if (!pictureName.equals(dish.pictureName)) return false;
        if (!addedDate.equals(dish.addedDate)) return false;
        return description != null ? description.equals(dish.description) : dish.description == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = dishId;
        result = 31 * result + name.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + pictureName.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) discountPercents;
        result = 31 * result + addedDate.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) weight;
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
        sb.append(", addedDate=").append(addedDate);
        sb.append(", description='").append(description).append('\'');
        sb.append(", weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }
}
