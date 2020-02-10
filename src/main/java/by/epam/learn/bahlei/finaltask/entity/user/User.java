package by.epam.learn.bahlei.finaltask.entity.user;

import by.epam.learn.bahlei.finaltask.entity.Entity;

import java.math.BigDecimal;
import java.util.Objects;

public class User implements Entity {
    private String login;
    private String hashedPassword;
    private String email;
    private int id;
    private UserRole userRole;
    private BigDecimal balance;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(hashedPassword, user.hashedPassword) &&
                Objects.equals(email, user.email) &&
                userRole == user.userRole &&
                Objects.equals(balance, user.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, hashedPassword, email, id, userRole, balance);
    }
}