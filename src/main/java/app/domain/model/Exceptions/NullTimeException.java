package app.domain.model.Exceptions;

public class NullTimeException extends Exception {

    public NullTimeException() {
        super("Time cannot be null");
    }

    public NullTimeException(String msg) {
        super(msg);
    }

}