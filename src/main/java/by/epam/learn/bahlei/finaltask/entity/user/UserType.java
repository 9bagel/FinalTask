package by.epam.learn.bahlei.finaltask.entity.user;

public enum UserType {
    ADMINISTRATOR(1),
    CUSTOMER(2);

    private int id;

    UserType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
