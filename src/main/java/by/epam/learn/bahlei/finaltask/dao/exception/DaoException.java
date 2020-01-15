package by.epam.learn.bahlei.finaltask.dao.exception;

public class DaoException extends Exception {

    private static final long serialVersionUID = 8219814090840591363L;

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
