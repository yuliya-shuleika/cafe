package com.yuliana.cafe.command;

import com.yuliana.cafe.command.impl.*;

public enum CommandEnum {
    TO_LOGIN {
        {
            this.command = new ToLoginCommand();
        }
    },
    TO_HOME {
        {
            this.command = new ToHomeCommand();
        }
    },
    TO_MENU {
        {
            this.command = new ToMenuCommand();
        }
    },
    TO_REGISTER {
        {
            this.command = new ToRegisterCommand();
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
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
