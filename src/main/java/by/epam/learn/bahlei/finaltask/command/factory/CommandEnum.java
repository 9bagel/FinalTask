package by.epam.learn.bahlei.finaltask.command.factory;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.locale.ChangeLocaleCommand;
import by.epam.learn.bahlei.finaltask.command.order.RemoveFromBasketCommand;
import by.epam.learn.bahlei.finaltask.command.order.ShowBasketPageCommand;
import by.epam.learn.bahlei.finaltask.command.order.ShowSuccessPageCommand;
import by.epam.learn.bahlei.finaltask.command.order.AddToBasketCommand;
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
    },
    ADD_TO_CART {
        {
            this.command = new AddToBasketCommand();
        }
    },

    BASKET {
        {
            this.command = new ShowBasketPageCommand();
        }
    },

    BALANCE {
        {
            this.command = new ShowBalancePageCommand();
        }
    },
    MAKE_DEPOSIT {
        {
            this.command = new MakeDepositCommand();
        }
    },

    REMOVE_FROM_BASKET {
        {
            this.command = new RemoveFromBasketCommand();
        }
    },

    SUCCESS {
        {
            this.command = new ShowSuccessPageCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}

