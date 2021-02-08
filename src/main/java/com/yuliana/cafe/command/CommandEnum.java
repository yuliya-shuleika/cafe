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
    TRANSLATE{
        {
            this.command = new TranslateCommand();
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
