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
    TO_PAYMENT{
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
    DELETE_FROM_GUEST_CART{
        {
            this.command = new DeleteFromGuestCartCommand();
        }
    },
    SORT_BY_PRICE{
        {
            this.command = new SortByPriceCommand();
        }
    },
    SHOW_DISCOUNTS{
        {
            this.command = new ShowDiscountsCommand();
        }
    },
    GIVE_FEEDBACK{
        {
            this.command = new GiveFeedbackCommand();
        }
    },
    CLEAN_CART{
        {
            this.command = new CleanCartCommand();
        }
    },
    CHECKOUT_COMMAND{
        {
            this.command = new CheckoutCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
