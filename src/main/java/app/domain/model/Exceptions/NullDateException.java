package app.domain.model.Exceptions;

public class NullDateException extends Exception {

    public NullDateException() {
        super("Date cannot be null");
    }

    public NullDateException(String msg) {
        super(msg);
    }

}