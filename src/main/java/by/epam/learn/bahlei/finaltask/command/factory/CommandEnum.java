package by.epam.learn.bahlei.finaltask.command.factory;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.locale.ChangeLocaleCommand;
import by.epam.learn.bahlei.finaltask.command.service.ShowServicePageCommand;
import by.epam.learn.bahlei.finaltask.command.user.LoginCommand;
import by.epam.learn.bahlei.finaltask.command.user.RegistrationCommand;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },

    REGISTRATION {
        {
            this.command = new RegistrationCommand();
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
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}

