package by.epam.learn.bahlei.finaltask.dto.user;

public enum RoleType {
    ADMINISTRATOR(1),
    CUSTOMER(2);

    private int typeId;

    RoleType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }
}
