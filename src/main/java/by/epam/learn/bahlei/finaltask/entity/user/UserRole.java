package by.epam.learn.bahlei.finaltask.entity.user;

import by.epam.learn.bahlei.finaltask.util.Constants;

import java.util.HashMap;
import java.util.Map;

public enum UserRole {
    ADMINISTRATOR(1, Constants.USER_ROLE_ADMINISTRATOR),
    CUSTOMER(2, Constants.USER_ROLE_CUSTOMER);

    private static final Map<Integer, UserRole> map;
    private int id;
    private String name;

    static {
        map = new HashMap<>();
        for (UserRole userRole : UserRole.values()) {
            map.put(userRole.id, userRole);
        }
    }

    UserRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static UserRole getUserRoleById(int roleId) {
        return map.get(roleId);
    }
}
