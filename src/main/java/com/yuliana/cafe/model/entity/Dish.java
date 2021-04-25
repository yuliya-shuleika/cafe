package com.yuliana.cafe.model.entity;

import java.util.Date;

/**
 * Dish entity.
 *
 * @author Yulia Shuleiko
 */
public class Dish {

    private int dishId;
    private String name;
    private DishCategory category;
    private String pictureName;
    private double price;
    private short discountPercents;
    private Date addedDate;
    private String description;
    private short weight;

    /**
     * Constructs the {@code Dish} object with given id, name, category, picture's name, price,
     * discount percents, added date, description and weight.
     *
     * @param dishId id of the dish
     * @param name name of the dish
     * @param category the {@code DishCategory} object represents the category of the dish
     * @param pictureName filepath of the picture of the dish
     * @param price price of the dish
     * @param discountPercents discount percents of the dish
     * @param addedDate {@code Date} object represents the date when the dish was added
     * @param description description of the dish
     * @param weight weight of the dish
     */
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

    /**
     * Constructs the {@code Dish} object with given name, category, picture's name, price,
     * discount percents, added date, description and weight.
     *
     * @param name name of the dish
     * @param category the {@code DishCategory} object represents the category of the dish
     * @param pictureName filepath of the picture of the dish
     * @param price price of the dish
     * @param discountPercents discount percents of the dish
     * @param addedDate {@code Date} object represents the date when the dish was added
     * @param description description of the dish
     * @param weight weight of the dish
     */
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

    /**
     * Constructs the {@code Dish} object
     */
    public Dish() {
    }

    /**
     * Getter method of the dish id.
     *
     * @return id of the dish
     */
    public int getDishId() {
        return dishId;
    }

    /**
     * Setter method of the dish id.
     *
     * @param dishId id of the dish
     */
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    /**
     * Getter method of the name.
     *
     * @return name of the dish
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method of the name
     *
     * @param name name of the dish.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method of the category.
     *
     * @return the {@code DishCategory} object represents the category of the dish
     */
    public DishCategory getCategory() {
        return category;
    }

    /**
     * Setter method of the category.
     *
     * @param category the {@code DishCategory} object represents the category of the dish
     */
    public void setCategory(DishCategory category) {
        this.category = category;
    }

    /**
     * Getter method of the price.
     *
     * @return price of the dish
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter method of the price.
     *
     * @param price price of the dish
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter method of the picture's name.
     *
     * @return filepath of the picture of the dish
     */
    public String getPictureName() {
        return pictureName;
    }

    /**
     * Setter method of the picture's name.
     *
     * @param pictureName filepath of the picture of the dish
     */
    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    /**
     * Getter method of the discount percents.
     *
     * @return discount percents of the dish
     */
    public short getDiscountPercents() {
        return discountPercents;
    }

    /**
     * Setter method of the discount percents
     *
     * @param discountPercents discount percents of the dish
     */
    public void setDiscountPercents(short discountPercents) {
        this.discountPercents = discountPercents;
    }

    /**
     * Getter method of the added date
     *
     * @return da{@code Date} object represents the date when the dish was added
     */
    public Date getAddedDate() {
        return addedDate;
    }

    /**
     * Setter method of the added date
     *
     * @param addedDate {@code Date} object represents the date when the dish was added
     */
    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    /**
     * Getter method of the description.
     *
     * @return description of the dish
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method of the description.
     *
     * @param description description of the dish
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method of the weight.
     *
     * @return weight of the dish
     */
    public short getWeight() {
        return weight;
    }

    /**
     * Setter method of the weight.
     *
     * @param weight weight of the dish
     */
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
