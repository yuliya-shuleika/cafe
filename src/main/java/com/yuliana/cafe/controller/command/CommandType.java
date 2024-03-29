package com.yuliana.cafe.controller.command;

import com.yuliana.cafe.controller.command.impl.*;

/**
 * Command type's enum.
 *
 * @author Yulia Shuleiko
 */
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
    TO_REVIEWS_LIST {
        {
            this.command = new ToReviewsListCommand();
        }
    },
    TO_PROMO_CODES_LIST {
        {
            this.command = new ToPromoCodesListCommand();
        }
    },
    TO_PAYMENT {
        {
            this.command = new ToPaymentCommand();
        }
    },
    TO_ACCOUNT {
        {
            this.command = new ToAccountCommand();
        }
    },
    ADD_TO_CART {
        {
            this.command = new AddToCartCommand();
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
    SEARCH_DISH_BY_NAME {
        {
            this.command = new SearchDishByNameCommand();
        }
    },
    DELETE_FROM_CART {
        {
            this.command = new DeleteFromCartCommand();
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
    CHECKOUT {
        {
            this.command = new CheckoutCommand();
        }
    },
    ADD_DISH_TO_MENU {
        {
            this.command = new AddDishToMenuCommand();
        }
    },
    EDIT_DISH {
        {
            this.command = new EditDishCommand();
        }
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
    },
    BLOCK_USER {
        {
            this.command = new BlockUserCommand();
        }
    },
    EDIT_PROMO_CODE {
        {
            this.command = new EditPromoCodeCommand();
        }
    },
    DELETE_PROMO_CODE {
        {
            this.command = new DeletePromoCodeCommand();
        }
    },
    DELETE_REVIEW {
        {
            this.command = new DeleteReviewCommand();
        }
    },
    SEARCH_PROMO_CODE_BY_NAME_PART {
        {
            this.command = new SearchPromoCodeByNamePartCommand();
        }
    },
    SORT_PROMO_CODES_BY_NAME {
        {
            this.command = new SortPromoCodesByNameCommand();
        }
    },
    SORT_REVIEWS_BY_HEADER {
        {
            this.command = new SortReviewsByHeaderCommand();
        }
    },
    SORT_USERS_BY_EMAIL {
        {
            this.command = new SortUsersByEmailCommand();
        }
    },
    ADD_PROMO_CODE {
        {
            this.command = new AddPromoCodeCommand();
        }
    },
    ADD_DISH_TO_FAVORITES {
        {
            this.command = new AddDishToFavoritesCommand();
        }
    },
    DELETE_FROM_FAVORITES {
        {
            this.command = new DeleteFromFavoritesCommand();
        }
    },
    SHOW_NEW_REVIEWS {
        {
            this.command = new ShowNewReviewsCommand();
        }
    },
    SHOW_DISH_EDIT {
        {
            this.command = new ShowDishEditCommand();
        }
    },
    SHOW_PROMO_CODE_EDIT {
        {
            this.command = new ShowPromoCodeEditCommand();
        }
    },
    UPDATE_REVIEW_STATUS {
        {
            this.command = new UpdateReviewStatusCommand();
        }
    },
    EDIT_ACCOUNT {
        {
            this.command = new EditAccountCommand();
        }
    },
    EDIT_USER_ADDRESS {
        {
            this.command = new EditUserAddressCommand();
        }
    },
    SHOW_USER_ADDRESS_EDIT {
        {
            this.command = new ShowUserAddressEditCommand();
        }
    },
    REPEAT_ORDER {
        {
            this.command = new RepeatOrderCommand();
        }
    },
    UNBLOCK_USER {
        {
            this.command = new UnblockUserCommand();
        }
    },
    CHANGE_USER_ROLE {
        {
            this.command = new ChangeUserRoleCommand();
        }
    },
    SORT_DISHES_BY_POPULARITY{
        {
            this.command = new SortDishesByPopularityCommands();
        }
    };
    ActionCommand command;

    /**
     * Getter method of the command.
     *
     * @return the {@code ActionCommand} object
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }

}

