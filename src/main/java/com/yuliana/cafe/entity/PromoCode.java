package com.yuliana.cafe.entity;

public class PromoCode {

    private int promoCodeId;
    private String name;
    private int discountPercents;

    public PromoCode(int promoCodeId, String name, int discountPercents) {
        this.promoCodeId = promoCodeId;
        this.name = name;
        this.discountPercents = discountPercents;
    }

    public PromoCode(String name, int discountPercents) {
        this.name = name;
        this.discountPercents = discountPercents;
    }

    public int getPromoCodeId() {
        return promoCodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscountPercents() {
        return discountPercents;
    }

    public void setDiscountPercents(int discountPercents) {
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
