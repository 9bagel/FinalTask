package by.epam.learn.bahlei.finaltask.command.factory;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.ShowMainPageCommand;
import by.epam.learn.bahlei.finaltask.command.ShowSuccessPageCommand;
import by.epam.learn.bahlei.finaltask.command.error.ShowErrorPageCommand;
import by.epam.learn.bahlei.finaltask.command.locale.ChangeLocaleCommand;
import by.epam.learn.bahlei.finaltask.command.order.*;
import by.epam.learn.bahlei.finaltask.command.review.LeaveReviewCommand;
import by.epam.learn.bahlei.finaltask.command.service.*;
import by.epam.learn.bahlei.finaltask.command.shoppingcart.AddServiceToCartCommand;
import by.epam.learn.bahlei.finaltask.command.shoppingcart.RemoveServiceFromShoppingCartCommand;
import by.epam.learn.bahlei.finaltask.command.shoppingcart.ShowShoppingCartCommand;
import by.epam.learn.bahlei.finaltask.command.user.*;
import by.epam.learn.bahlei.finaltask.entity.user.UserRole;

import java.util.Arrays;
import java.util.EnumSet;

public enum CommandEnum {
    MAIN(new ShowMainPageCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },
    LOGIN(new ShowLoginPageCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },

    LOGIN_ACTION(new LoginCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },

    REGISTRATION_ACTION(new RegistrationCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },

    REGISTRATION(new ShowRegistrationPageCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },
    LEAVE_REVIEW(new LeaveReviewCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },

    SEARCH(new SearchServiceCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },

    SERVICE_LIST(new ShowServiceTypePageCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },

    CHANGE_LOCALE(new ChangeLocaleCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },
    LOGOUT(new LogoutCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },
    ADD_TO_CART(new AddServiceToCartCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },

    SHOPPING_CART(new ShowShoppingCartCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },

    BALANCE(new ShowBalancePageCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },
    MAKE_DEPOSIT(new MakeDepositCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },

    REMOVE_FROM_SHOPPING_CART(new RemoveServiceFromShoppingCartCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },

    PAY_ORDER(new PayOrderCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },

    CREATE_ORDER(new CreateOrderCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },
    CANCEL_ORDER(new CancelOrderCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },

    ERROR(new ShowErrorPageCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },

    SHOW_ORDERS(new ShowOrdersCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },

    SUCCESS(new ShowSuccessPageCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },
    ORDER_DETAILS(new OrderDetailsCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN, UserRole.USER);
        }
    },
    MANAGE_ORDERS(new ManageOrdersCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN);
        }
    },

    UPDATE_ORDER(new UpdateOrderCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN);
        }
    },

    MANAGE_USERS(new ManageUsersCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN);
        }
    },

    UPDATE_USER(new UpdateUserCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN);
        }
    },


    ADD_SERVICE(new AddServiceCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN);
        }
    },

    DELETE_SERVICE(new DeleteServiceCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN);
        }
    },

    EDIT_SERVICE_PAGE(new ShowUpdateServicePageCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN);
        }
    },

    UPDATE_SERVICE(new UpdateServiceCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN);
        }
    },


    ADD_SERVICE_PAGE(new ShowAddServicePageCommand()) {
        {
            setAllowedUserRoles(UserRole.ADMIN);
        }
    };

    ActionCommand command;
    EnumSet<UserRole> allowedUserRoles = EnumSet.noneOf(UserRole.class);

    public ActionCommand getCurrentCommand() {
        return command;
    }

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public void setAllowedUserRoles(UserRole... userRoles) {
        this.allowedUserRoles.addAll(Arrays.asList(userRoles));
    }

    public boolean isRoleAllowed(UserRole userRole) {
        return allowedUserRoles.contains(userRole);
    }
}