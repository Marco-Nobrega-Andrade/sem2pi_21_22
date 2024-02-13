package app.domain.model.Exceptions;

public class ScheduleAtTheSameTimeException extends Exception{
    public ScheduleAtTheSameTimeException() {
        super("A schedule already exists at that time");
    }

    public ScheduleAtTheSameTimeException(String message) {
        super(message);
    }
}
