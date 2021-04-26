package com.yuliana.cafe.controller;

/**
 * Class is a storage of the request parameter names.
 *
 * @author Yulia Shuleiko
 */
public class RequestParameter {

    private RequestParameter() {
    }

    /**
     * Name of the parameter that contains id of the dish.
     */
    public static final String DISH_ID = "dish_id";

    /**
     * Name of the parameter that contains name of the dish.
     */
    public static final String DISH_NAME = "dish_name";

    /**
     * Name of the parameter that contains category of the dish.
     */
    public static final String DISH_CATEGORY = "dish_category";

    /**
     * Name of the parameter that contains filepath of the picture of the dish.
     */
    public static final String DISH_PICTURE = "dish_picture";

    /**
     * Name of the parameter that contains id of the promo code.
     */
    public static final String PROMO_CODE_ID = "promo_code_id";

    /**
     * Name of the parameter that contains name of the promo code.
     */
    public static final String PROMO_CODE_NAME = "promo_code_name";

    /**
     * Name of the parameter that contains discount percents that provides promo code.
     */
    public static final String PROMO_CODE_DISCOUNT_PERCENTS = "promo_code_discount_percents";

    /**
     * Name of the parameter that contains id of the review.
     */
    public static final String REVIEW_ID = "review_id";

    /**
     * Name of the parameter that contains header of the review.
     */
    public static final String REVIEW_HEADER = "review_header";

    /**
     * Name of the parameter that contains text of the review.
     */
    public static final String REVIEW_TEXT = "review_text";

    /**
     * Name of the parameter that contains rating (from 1 to 5 points) of the review.
     */
    public static final String REVIEW_RATING = "review_rating";

    /**
     * Name of the parameter that contains status (new, approved or rejected) of the review.
     */
    public static final String REVIEW_STATUS = "review_status";

    /**
     * Name of the parameter that contains id of the user.
     */
    public static final String USER_ID = "user_id";

    /**
     * Name of the parameter that contains name of the user.
     */
    public static final String USER_NAME = "user_name";

    /**
     * Name of the parameter that contains email of the user.
     */
    public static final String USER_EMAIL = "user_email";

    /**
     * Name of the parameter that password id of the user.
     */
    public static final String USER_PASSWORD = "user_password";

    /**
     * Name of the parameter that contains filepath of the avatar of the user.
     */
    public static final String USER_AVATAR = "user_avatar";

    /**
     * Name of the parameter that contains role (admin or user) of the user.
     */
    public static final String USER_ROLE = "user_role";

    /**
     * Name of the parameter that contains id of the order.
     */
    public static final String ORDER_ID = "order_id";

    /**
     * Name of the parameter that contains comment text of the order.
     */
    public static final String ORDER_COMMENT = "order_comment";

    /**
     * Name of the parameter that contains getting type (delivery or pickup) of the order.
     */
    public static final String ORDER_GETTING_TYPE = "order_getting_type";

    /**
     * Name of the parameter that contains payment type (cash or bank card) of the order.
     */
    public static final String ORDER_PAYMENT_TYPE = "order_payment_type";

    /**
     * Name of the parameter that contains id of the address.
     */
    public static final String ADDRESS_ID = "address_id";

    /**
     * Name of the parameter that contains city name.
     */
    public static final String CITY = "city";

    /**
     * Name of the parameter that contains street name.
     */
    public static final String STREET = "street";

    /**
     * Name of the parameter that contains house number.
     */
    public static final String HOUSE = "house";

    /**
     * Name of the parameter that contains entrance number.
     */
    public static final String ENTRANCE = "entrance";

    /**
     * Name of the parameter that contains floor number.
     */
    public static final String FLOOR = "floor";

    /**
     * Name of the parameter that contains flat number.
     */
    public static final String FLAT = "flat";

    /**
     * Name of the parameter that contains part of the name of the promo code.
     */
    public static final String PROMO_CODE_NAME_PART_PARAM = "promo_code_name_part";
    public static final String USER_EMAIL_PART_PARAM = "user_email_part";

    /**
     * Name of the parameter that contains command type.
     */
    public static final String COMMAND = "command";

    /**
     * Name of the parameter that contains current language.
     */
    public static final String LANGUAGE = "language";

    /**
     * Name of the parameter that contains a count of items to delete.
     */
    public static final String ITEMS_TO_DELETE = "items_count";
}
