package com.yuliana.cafe.model.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Order entity.
 *
 * @author Yulia Shuleiko
 */
public class Order {

    private int orderId;
    private Date date;
    private double total;
    private String comment;
    private Map<Dish, Integer> orderedDishes;
    private PaymentType paymentType;
    private GettingType gettingType;

    /**
     * Constructs the {@code Order} object with given date, total, comment, paymentType and gettingType.
     *
     * @param date {@code Date} object that represents the date of order
     * @param total total amount of money
     * @param comment comment of the order
     * @param paymentType {@code PaymentType} object that represents type of the payment (cash or bank card)
     * @param gettingType {@code GettingType} object that represents type of the getting order (delivery or pickup)
     */
    public Order(Date date, double total, String comment, PaymentType paymentType, GettingType gettingType) {
        this.date = date;
        this.total = total;
        this.comment = comment;
        this.paymentType = paymentType;
        this.gettingType = gettingType;
    }

    /**
     * Constructs the {@code Order} object with given order's id, date, total, comment,
     * orderedDishes, paymentType and gettingType.
     *
     * @param orderId id of the order
     * @param date {@code Date} object that represents the date of order
     * @param total total amount of money
     * @param comment comment of the order
     * @param orderedDishes map of the {@code Dish} object and integer.
     *                      The key represents ordered dish and the value is it's count.
     * @param paymentType {@code PaymentType} object that represents type of the payment (cash or bank card)
     * @param gettingType {@code GettingType} object that represents type of the getting order (delivery or pickup)
     */
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

    /**
     *  Constructs the {@code Order} object with given date, total, comment,
     *  orderedDishes, paymentType and gettingType.
     *
     * @param date {@code Date} object that represents the date of order
     * @param total total amount of money
     * @param comment comment of the order
     * @param orderedDishes map of the {@code Dish} object and integer.
     *                      The key represents ordered dish and the value is it's count.
     * @param paymentType {@code PaymentType} object that represents type of the payment (cash or bank card)
     * @param gettingType {@code GettingType} object that represents type of the getting order (delivery or pickup)
     */
    public Order(Date date, double total, String comment, Map<Dish, Integer> orderedDishes,
                 PaymentType paymentType, GettingType gettingType) {
        this.date = date;
        this.total = total;
        this.comment = comment;
        this.orderedDishes = orderedDishes;
        this.paymentType = paymentType;
        this.gettingType = gettingType;
    }

    /**
     *  Constructs the {@code Order} object.
     */
    public Order() {
    }

    /**
     * Getter method of the order's id.
     *
     * @return id of the order
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Getter method of the date.
     *
     * @return {@code Date} object that represents the date of order
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter method of the date.
     *
     * @param date {@code Date} object that represents the date of order
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter method of the total.
     *
     * @return total amount of money
     */
    public double getTotal() {
        return total;
    }

    /**
     * Setter method of the total.
     *
     * @param total total amount of money
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Getter method of the ordered dishes.
     *
     * @return map of the {@code Dish} object and integer.
     * The key represents ordered dish and the value is it's count.
     */
    public Map<Dish, Integer> getOrderedDishes() {
        return new HashMap<>(orderedDishes);
    }

    /**
     * Getter method of the comment.
     *
     * @return comment of the order
     */
    public String getComment() {
        return comment;
    }

    /**
     * Setter method of the comment.
     *
     * @param comment comment of the order
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Getter method of the payment type.
     *
     * @return {@code PaymentType} object that represents type of the payment (cash or bank card)
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Setter method of the payment type.
     *
     * @param paymentType {@code PaymentType} object that represents type of the payment (cash or bank card)
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Getter method of the getting type.
     *
     * @return {@code GettingType} object that represents type of the getting order (delivery or pickup)
     */
    public GettingType getGettingType() {
        return gettingType;
    }

    /**
     * Setter method of the getting type.
     *
     * @param gettingType {@code GettingType} object that represents type of the getting order (delivery or pickup)
     */
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
