package by.epam.learn.bahlei.finaltask.command.factory;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.error.ShowErrorPageCommand;
import by.epam.learn.bahlei.finaltask.command.locale.ChangeLocaleCommand;
import by.epam.learn.bahlei.finaltask.command.order.*;
import by.epam.learn.bahlei.finaltask.command.service.ShowServicePageCommand;
import by.epam.learn.bahlei.finaltask.command.shoppingcart.AddServiceToCartCommand;
import by.epam.learn.bahlei.finaltask.command.shoppingcart.RemoveServiceFromShoppingCartCommand;
import by.epam.learn.bahlei.finaltask.command.shoppingcart.ShowShoppingCartCommand;
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
            this.command = new AddServiceToCartCommand();
        }
    },

    SHOPPING_CART {
        {
            this.command = new ShowShoppingCartCommand();
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

    REMOVE_FROM_SHOPPING_CART {
        {
            this.command = new RemoveServiceFromShoppingCartCommand();
        }
    },

    PAY {
        {
            this.command = new PayCommand();
        }
    },

    CREATE_ORDER {
        {
            this.command = new CreateOrderCommand();
        }
    },

    ERROR {
        {
            this.command = new ShowErrorPageCommand();
        }
    },
    ORDERS {
        {
            this.command = new ShowOrdersCommand();
        }
    },

    ORDER_DETAILS {
        {
            this.command = new OrderDetailsCommand();
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

