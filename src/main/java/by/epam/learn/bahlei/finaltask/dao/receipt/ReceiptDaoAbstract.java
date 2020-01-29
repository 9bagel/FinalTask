package by.epam.learn.bahlei.finaltask.dao.receipt;

import by.epam.learn.bahlei.finaltask.dao.AbstractEntityDao;
import by.epam.learn.bahlei.finaltask.entity.receipt.Receipt;

public abstract class ReceiptDaoAbstract extends AbstractEntityDao<Receipt> {

    @Override
    protected String getDeleteQuery() {
        return null;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO receipts(status_id, total, order_id, date) VALUES(?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return null;
    }

    @Override
    protected String getSelectAllQuery() {
        return null;
    }

    @Override
    protected String getSelectLimitQuery() {
        return null;
    }
}
