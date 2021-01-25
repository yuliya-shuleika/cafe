package com.yuliana.cafe.command;

import com.yuliana.cafe.command.impl.LoginCommand;
import com.yuliana.cafe.command.impl.LogoutCommand;
import com.yuliana.cafe.command.impl.OpenRegisterCommand;
import com.yuliana.cafe.command.impl.RegisterCommand;

public enum CommandEnum {
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
    OPENREGISTER {
        {
            this.command = new OpenRegisterCommand();
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
