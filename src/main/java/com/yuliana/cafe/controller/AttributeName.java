package com.yuliana.cafe.controller;

/**
 * Class is a storage of the attribute names.
 *
 * @author Yulia Shuleiko
 */
public class AttributeName {

    /**
     * Name of the attribute that represents map of the cart items.
     * Key is a {@code Dish} object and value is a count of this item.
     */
    public static final String CART_ITEMS = "cart_items";

    /**
     * Name of the attribute that represents count of all cart items.
     */
    public static final String CART_ITEMS_COUNT = "cart_items_count";

    /**
     * Name of the attribute that represents a list of the {@code Dish} object.
     */
    public static final String DISHES_LIST = "dishes_list";

    /**
     * Name of the attribute that represents a list of the {@code Review} objects.
     */
    public static final String REVIEWS_LIST = "reviews_list";

    /**
     * Name of the attribute that represents a map of all approved reviews and it's authors.
     * Key is a {@code Review} object and value is a {@code User} object
     */
    public static final String REVIEWS_MAP = "reviews_map";

    /**
     * Name of the attribute that represents a list of the {@code PromoCode} objects.
     */
    public static final String PROMO_CODES_LIST = "promo_codes_list";

    /**
     * Name of the attribute that represents current language.
     */
    public static final String LANGUAGE = "lang";

    /**
     * Name of the attribute that represents a {@code User} object.
     * Object contains information about the authorised user.
     */
    public static final String USER = "user";

    /**
     * Name of the attribute that represents a {@code Dish} object.
     * Object contains information about the dish from menu that should be edited.
     */
    public static final String SELECTED_DISH = "selected_dish";

    /**
     * Name of the attribute that represents a {@code PromoCode} object.
     * Object contains information about the promo code that should be edited.
     */
    public static final String SELECTED_PROMO_CODE = "selected_promo_code";

    /**
     * Name of the attribute that represents a {@code Review} object.
     * Object contains information about the review that status should be changed.
     */
    public static final String SELECTED_REVIEW = "selected_review";

    /**
     * Name of the attribute that represents a map of strings.
     * Key is a name of the field from address form.
     * Value is a user's input.
     */
    public static final String ADDRESS_FIELDS = "address_fields";

    /**
     * Name of the attribute that represents an error message that can appear because of checkout error.
     */
    public static final String CHECKOUT_ERROR_MESSAGE = "checkout_error_message";

    /**
     * Name of the attribute that represents a promo code name.
     */
    public static final String PROMO_CODE = "promo_code";

    /**
     * Name of the attribute that represents a list of {@code User} objects.
     */
    public static final String USERS_LIST = "users_list";

    /**
     * Name of the attribute that represents a list of {@code Address} objects.
     * This is the addresses where cafes are located.
     */
    public static final String CAFE_ADDRESSES = "cafe_addresses";

    /**
     * Name of the attribute that represents a list of {@code Order} objects.
     * This is all orders of the certain user.
     */
    public static final String USER_ORDERS = "user_orders";

    /**
     * Name of the attribute that represents the {@code Address} object.
     * This is an address of the certain user.
     */
    public static final String USER_ADDRESS = "user_address";

    /**
     * Name of the attribute that represents a list of {@code Dish} objects.
     * This is the dishes from the favorites list of the certain user.
     */
    public static final String USER_FAVORITES = "user_favorites";

    /**
     * Name of the attribute that represents the {@code Address} object.
     * This is an address of the certain order.
     */
    public static final String ORDER_ADDRESS = "order_address";

    /**
     * Name of the attribute that represents a path of the current page.
     */
    public static final String CURRENT_PAGE = "current_page";

    /**
     * Name of the attribute that represents the {@code Address} object.
     * This is an address of the certain user that should be edited.
     */
    public static final String EDIT_ADDRESS = "edit_address";

    /**
     * Name of the attribute that represents an error message that can appear because of login error.
     */
    public static final String LOGIN_ERROR_MESSAGE = "login_error_message";

    /**
     * Name of the attribute that represents an error message that can appear because of register error.
     */
    public static final String REGISTER_ERROR_MESSAGE = "register_error_message";


    public static final String ADD_DISH_TO_CART = "add_dish_to_cart";

    /**
     * Name of the attribute that represents the {@code Order} object.
     * This is an order that user wants to repeat.
     */
    public static final String REPEATED_ORDER = "repeated_order";

    /**
     * Name of the attribute that represents an error message that can appear because of edit error.
     */
    public static final String EDIT_ERROR_MESSAGE = "edit_error_message";

    /**
     * Name of the attribute that represents a map of strings.
     * Key is a name of the field from the promo code add and edit form.
     * Value is a user's input.
     */
    public static final String PROMO_CODE_FIELDS = "promo_code_fields";

    /**
     * Name of the attribute that represents a map of strings.
     * Key is a name of the field from the review add form.
     * Value is a user's input.
     */
    public static final String REVIEW_FIELDS = "review_fields";

    /**
     * Name of the attribute that represents a map of strings.
     * Key is a name of the field from the dish add and edit form.
     * Value is a user's input.
     */
    public static final String DISH_FIELDS = "dish_fields";

    /**
     * Name of the attribute that represents a map of strings.
     * Key is a name of the field from the account edit form.
     * Value is a user's input.
     */
    public static final String USER_FIELDS = "user_fields";
    public static final String REGISTER_FIELDS = "register_fields";
    public static final String LOGIN_FIELDS = "login_fields";

    /**
     * Name of the attribute that represents id of the promo code.
     */
    public static final String PROMO_CODE_ID = "promo_code_id";

    /**
     * Name of the attribute that represents id of the address.
     */
    public static final String ADDRESS_ID = "address_id";

    /**
     * Name of the attribute that represents id of the dish.
     */
    public static final String DISH_ID = "dish_id";

    /**
     * Name of the attribute that represents filepath of the dish picture.
     */
    public static final String DISH_PICTURE = "dish_picture";

    private AttributeName() {
    }
}
