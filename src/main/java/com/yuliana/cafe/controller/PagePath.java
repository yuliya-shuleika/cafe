package com.yuliana.cafe.controller;

/**
 * Class is a storage of page path's names.
 *
 * @author Yulia Shuleiko
 */
public class PagePath {

    private PagePath() {
    }

    /**
     * The path to the home page.
     */
    public static final String HOME_PAGE = "/jsp/home.jsp";

    /**
     * The path of the menu page.
     */
    public static final String MENU_PAGE = "/jsp/menu.jsp";

    /**
     * The path of the page that contains user's reviews.
     */
    public static final String REVIEWS_PAGE = "/jsp/reviews.jsp";

    /**
     * The path of admin page that contains users list and some actions with it.
     */
    public static final String USERS_LIST_PAGE = "/jsp/users-list.jsp";

    /**
     * The path of admin page that contains dishes list and some actions with it.
     */
    public static final String DISHES_LIST_PAGE = "/jsp/dishes-list.jsp";

    /**
     * The path of admin page that contains reviews list and some actions with it.
     */
    public static final String REVIEWS_LIST_PAGE = "/jsp/reviews-list.jsp";

    /**
     * The path of admin page that contains promo codes list and some actions with it.
     */
    public static final String PROMO_CODES_LIST_PAGE = "/jsp/promo-codes-list.jsp";

    /**
     * The path of the user's account page.
     */
    public static final String ACCOUNT_PAGE = "/jsp/account.jsp";

    /**
     * The path of the page with a checkout form.
     */
    public static final String PAYMENT_PAGE = "/jsp/payment.jsp";

    /**
     * The path of the page with order confirmation.
     */
    public static final String ORDER_CONFIRM_PAGE = "/jsp/checkout-confirm.jsp";
    public static final String ABOUT_PAGE = "/jsp/about.jsp";

    /**
     * The path of error 400 page.
     */
    public static final String ERROR_400_PAGE = "/jsp/error/error400.jsp";

    /**
     * The path of error 403 page.
     */
    public static final String ERROR_403_PAGE = "/jsp/error/error403.jsp";

    /**
     * The path of error 404 page.
     */
    public static final String ERROR_404_PAGE = "/jsp/error/error404.jsp";

    /**
     * The path of error 500 page.
     */
    public static final String ERROR_500_PAGE = "/jsp/error/error500.jsp";
}
