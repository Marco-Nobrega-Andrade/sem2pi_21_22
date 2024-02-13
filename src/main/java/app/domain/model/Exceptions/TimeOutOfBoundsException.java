package app.domain.model.Exceptions;

public class TimeOutOfBoundsException extends Exception{
    public TimeOutOfBoundsException() {
        super("The time isn't in the range needed");
    }

    public TimeOutOfBoundsException(String message) {
        super(message);
    }
}
