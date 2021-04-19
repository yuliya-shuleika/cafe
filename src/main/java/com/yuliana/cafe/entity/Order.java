package com.yuliana.cafe.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private int orderId;
    private Date date;
    private double total;
    private String comment;
    private Map<Dish, Integer> orderedDishes;
    private PaymentType paymentType;
    private GettingType gettingType;

    public Order(Date date, double total, String comment, PaymentType paymentType, GettingType gettingType) {
        this.date = date;
        this.total = total;
        this.comment = comment;
        this.paymentType = paymentType;
        this.gettingType = gettingType;
    }

    public Order(int orderId, Date date, double total, String comment, Map<Dish,
            Integer> orderedDishes, PaymentType paymentType, GettingType gettingType) {
        this.orderId = orderId;
        this.date = date;
        this.total = total;
        this.comment = comment;
        this.orderedDishes = orderedDishes;
        this.paymentType = paymentType;
        this.gettingType = gettingType;
    }

    public Order(Date date, double total, String comment, Map<Dish, Integer> orderedDishes,
                 PaymentType paymentType, GettingType gettingType) {
        this.date = date;
        this.total = total;
        this.comment = comment;
        this.orderedDishes = orderedDishes;
        this.paymentType = paymentType;
        this.gettingType = gettingType;
    }

    public Order() {
    }

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

    public Map<Dish, Integer> getOrderedDishes() {
        return new HashMap<>(orderedDishes);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public GettingType getGettingType() {
        return gettingType;
    }

    public void setGettingType(GettingType gettingType) {
        this.gettingType = gettingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        if (orderId != order.orderId) return false;
        if (Double.compare(order.total, total) != 0) return false;
        if (!date.equals(order.date)) return false;
        if (comment != null ? !comment.equals(order.comment) : order.comment != null) return false;
        if (orderedDishes != null ? !orderedDishes.equals(order.orderedDishes) : order.orderedDishes != null)
            return false;
        if (paymentType != order.paymentType) return false;
        return gettingType == order.gettingType;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId;
        result = 31 * result + date.hashCode();
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (orderedDishes != null ? orderedDishes.hashCode() : 0);
        result = 31 * result + paymentType.hashCode();
        result = 31 * result + gettingType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", date=").append(date);
        sb.append(", total=").append(total);
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", orderedDishes=").append(orderedDishes);
        sb.append(", paymentType=").append(paymentType);
        sb.append(", gettingType=").append(gettingType);
        sb.append('}');
        return sb.toString();
    }
}
