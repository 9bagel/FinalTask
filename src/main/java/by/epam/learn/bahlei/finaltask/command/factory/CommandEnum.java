package by.epam.learn.bahlei.finaltask.command.factory;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.ShowSuccessPageCommand;
import by.epam.learn.bahlei.finaltask.command.error.ShowErrorPageCommand;
import by.epam.learn.bahlei.finaltask.command.locale.ChangeLocaleCommand;
import by.epam.learn.bahlei.finaltask.command.order.*;
import by.epam.learn.bahlei.finaltask.command.service.AddServiceCommand;
import by.epam.learn.bahlei.finaltask.command.service.DeleteServiceCommand;
import by.epam.learn.bahlei.finaltask.command.service.ShowAddServicePageCommand;
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

    PAY_ORDER {
        {
            this.command = new PayOrderCommand();
        }
    },

    CREATE_ORDER {
        {
            this.command = new CreateOrderCommand();
        }
    },
    CANCEL_ORDER {
        {
            this.command = new CancelOrderCommand();
        }
    },

    ERROR {
        {
            this.command = new ShowErrorPageCommand();
        }
    },

    SHOW_ORDERS {
        {
            this.command = new ShowOrdersCommand();
        }
    },

    ORDER_DETAILS {
        {
            this.command = new OrderDetailsCommand();
        }
    },
    MANAGE_ORDERS {
        {
            this.command = new ManageOrdersCommand();
        }
    },

    UPDATE_ORDER {
        {
            this.command = new UpdateOrderCommand();
        }
    },

    MANAGE_USERS {
        {
            this.command = new ManageUsersCommand();
        }
    },

    UPDATE_USER {
        {
            this.command = new UpdateUserCommand();
        }
    },

    SUCCESS {
        {
            this.command = new ShowSuccessPageCommand();
        }
    },

    ADD_SERVICE {
        {
            this.command = new AddServiceCommand();
        }
    },

    DELETE_SERVICE {
        {
            this.command = new DeleteServiceCommand();
        }
    },

    ADD_SERVICE_PAGE {
        {
            this.command = new ShowAddServicePageCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}

