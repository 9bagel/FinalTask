package by.epam.learn.bahlei.finaltask.command.factory;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.locale.ChangeLocaleCommand;
import by.epam.learn.bahlei.finaltask.command.service.ShowServicePageCommand;
import by.epam.learn.bahlei.finaltask.command.user.*;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new ShowLoginPageCommand();
        }
    },

    LOGIN_ACTION {
        {
            this.command = new LoginCommand();
        }
    },

    REGISTRATION_ACTION {
        {
            this.command = new RegistrationCommand();
        }
    },

    REGISTRATION {
        {
            this.command = new ShowRegistrationPageCommand();
        }
    },

    SERVICE_LIST {
        {
            this.command = new ShowServicePageCommand();
        }
    },

    CHANGE_LOCALE {
        {
            this.command = new ChangeLocaleCommand();
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

