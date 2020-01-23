package by.epam.learn.bahlei.finaltask.entity.order;

public enum OrderStatus {
    NEW(1),
    IN_PROGRESS(2),
    COMPLETED(3);

    private int id;

    OrderStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
