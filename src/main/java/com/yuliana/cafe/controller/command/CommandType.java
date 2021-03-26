package com.yuliana.cafe.controller.command;

import com.yuliana.cafe.controller.command.impl.*;

public enum CommandType {
    TO_HOME {
        {
            this.command = new ToHomeCommand();
        }
    },
    TO_REVIEWS {
        {
            this.command = new ToReviewsCommand();
        }
    },
    TO_MENU {
        {
            this.command = new ToMenuCommand();
        }
    },
    TO_MANAGEMENT {
        {
            this.command = new ToManagementCommand();
        }
    },
    TO_USERS_LIST {
        {
            this.command = new ToUsersListCommand();
        }
    },
    TO_DISHES_LIST {
        {
            this.command = new ToDishesListCommand();
        }
    },
    TO_ORDERS_LIST {
        {
            this.command = new ToOrdersListCommand();
        }
    },
    TO_REVIEWS_LIST {
        {
            this.command = new ToReviewsListCommand();
        }
    },
    TO_PAYMENT {
        {
            this.command = new ToPaymentCommand();
        }
    },
    ADD_TO_GUEST_CART {
        {
            this.command = new AddToGuestCartCommand();
        }
    },
    CHOOSE_CATEGORY {
        {
            this.command = new ChooseCategoryCommand();
        }
    },
    CHANGE_LOCALE {
        {
            this.command = new ChangeLocaleCommand();
        }
    },
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    SEARCH_DISH {
        {
            this.command = new SearchDishCommand();
        }
    },
    DELETE_FROM_GUEST_CART {
        {
            this.command = new DeleteFromGuestCartCommand();
        }
    },
    SORT_BY_PRICE {
        {
            this.command = new SortDishesByPriceCommand();
        }
    },
    SHOW_DISCOUNTS {
        {
            this.command = new ShowDiscountsCommand();
        }
    },
    GIVE_FEEDBACK {
        {
            this.command = new GiveFeedbackCommand();
        }
    },
    CLEAN_CART {
        {
            this.command = new CleanCartCommand();
        }
    },
    CHECKOUT {
        {
            this.command = new CheckoutCommand();
        }
    },
    ADD_DISH {

    },
    EDIT_DISH {

    },
    DELETE_DISH_FROM_MENU {
        {
            this.command = new DeleteDishFromMenuCommand();
        }
    },
    SEARCH_USER_BY_EMAIL {
        {
            this.command = new SearchUserByEmailCommand();
        }
    },
    SEARCH_REVIEW_BY_HEADER {
        {
            this.command = new SearchReviewByHeaderCommand();
        }
    },
    SORT_DISHES_BY_NAME {
        {
            this.command = new SortDishesByNameCommand();
        }
    }
    ;
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
