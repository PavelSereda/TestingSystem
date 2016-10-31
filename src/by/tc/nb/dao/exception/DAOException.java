package by.tc.nb.dao.exception;


public class DAOException extends Exception {
    public DAOException(Exception message) {
        super(message);
    }

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
    }


}
