package com.yuliana.cafe.model.entity;

/**
 * Promo code entity.
 *
 * @author Yulia Shuleiko
 */
public class PromoCode {

    private int promoCodeId;
    private String name;
    private short discountPercents;

    /**
     * Constructs the {@code PromoCode} object with given id, name and discount percents.
     *
     * @param promoCodeId id of the promo code
     * @param name name of the promo code
     * @param discountPercents discount percents that provides promo code
     */
    public PromoCode(int promoCodeId, String name, short discountPercents) {
        this.promoCodeId = promoCodeId;
        this.name = name;
        this.discountPercents = discountPercents;
    }

    /**
     * Constructs the {@code PromoCode} object with given name and discount percents.
     *
     * @param name name of the promo code
     * @param discountPercents discount percents that provides promo code
     */
    public PromoCode(String name, short discountPercents) {
        this.name = name;
        this.discountPercents = discountPercents;
    }

    /**
     * Setter method of the promo code's id.
     *
     * @return id of the promo code
     */
    public int getPromoCodeId() {
        return promoCodeId;
    }

    /**
     * Getter method of the promo code's id.
     *
     * @param promoCodeId id of the promo code
     */
    public void setPromoCodeId(int promoCodeId) {
        this.promoCodeId = promoCodeId;
    }

    /**
     * Getter method of the name.
     *
     * @return name of the promo code
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method of the name.
     *
     * @param name name of the promo code
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method of the discount percents.
     *
     * @return discount percents that provides promo code
     */
    public short getDiscountPercents() {
        return discountPercents;
    }

    /**
     * Setter method of the discount percents.
     *
     * @param discountPercents discount percents that provides promo code
     */
    public void setDiscountPercents(short discountPercents) {
        this.discountPercents = discountPercents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromoCode promoCode = (PromoCode) o;
        if (promoCodeId != promoCode.promoCodeId) return false;
        if (discountPercents != promoCode.discountPercents) return false;
        return name != null ? name.equals(promoCode.name) : promoCode.name == null;
    }

    @Override
    public int hashCode() {
        int result = promoCodeId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + discountPercents;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PromoCode{");
        sb.append("promoCodeId=").append(promoCodeId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", discountPercents=").append(discountPercents);
        sb.append('}');
        return sb.toString();
    }
}
