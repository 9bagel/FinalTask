package by.epam.learn.bahlei.finaltask.entity.user;

import by.epam.learn.bahlei.finaltask.entity.Entity;

public class User implements Entity {
    private String login;
    private String hashedPassword;
    private String email;
    private long id;
    private int typeId;


    public User() {
    }

    public User(String login, String hashedPassword, String email) {
        this.login = login;
        this.hashedPassword = hashedPassword;
        this.email = email;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}