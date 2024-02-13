package app.domain.model.Exceptions;

public class NumberFormatError extends Exception {

    public NumberFormatError() {
        super("Incorrect number format");
    }

    public NumberFormatError(String msg) {
        super(msg);
    }

}
