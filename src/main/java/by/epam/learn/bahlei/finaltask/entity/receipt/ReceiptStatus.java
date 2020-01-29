package by.epam.learn.bahlei.finaltask.entity.receipt;

public enum ReceiptStatus {
    NOT_PAID(1),
    PAID(2);

    private int id;

    ReceiptStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
