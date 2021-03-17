package com.yuliana.cafe.entity;

import java.util.Date;

public class Order {

    private int orderId;
    private Date date;
    private double total;
    private int userId;
    private int addressId;

    public Order(int orderId, Date date, double total, int userId, int addressId) {
        this.orderId = orderId;
        this.date = date;
        this.total = total;
        this.userId = userId;
        this.addressId = addressId;
    }

    public Order(Date date, double total, int userId, int addressId) {
        this.date = date;
        this.total = total;
        this.userId = userId;
        this.addressId = addressId;
    }

    public Order(){}

    public int getOrderId() {
        return orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        if (orderId != order.orderId) return false;
        if (Double.compare(order.total, total) != 0) return false;
        if (userId != order.userId) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        return addressId == order.addressId;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + userId;
        result = 31 * result + addressId;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", date=").append(date);
        sb.append(", total=").append(total);
        sb.append(", userId=").append(userId);
        sb.append(", address=").append(addressId);
        sb.append('}');
        return sb.toString();
    }
}
