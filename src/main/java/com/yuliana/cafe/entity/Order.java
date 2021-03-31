package com.yuliana.cafe.entity;

import java.util.Date;

public class Order {

    private int orderId;
    private Date date;
    private double total;

    public Order(Date date, double total) {
        this.date = date;
        this.total = total;
    }

    public Order(int orderId, Date date, double total) {
        this.orderId = orderId;
        this.date = date;
        this.total = total;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        if (orderId != order.orderId) return false;
        if (Double.compare(order.total, total) != 0) return false;
        return date != null ? !date.equals(order.date) : order.date != null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", date=").append(date);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }
}
