package app.domain.model.Exceptions;

public class DuplicateScheduleException extends Exception{
    public DuplicateScheduleException() {
        super("Vaccine schedule with same vaccine type already exists");
    }

    public DuplicateScheduleException(String message) {
        super(message);
    }
}
