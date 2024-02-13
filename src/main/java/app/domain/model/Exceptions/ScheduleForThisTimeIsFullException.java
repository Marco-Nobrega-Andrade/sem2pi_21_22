package app.domain.model.Exceptions;

public class ScheduleForThisTimeIsFullException extends Exception{
    public ScheduleForThisTimeIsFullException() {
        super("There are no more schedules available for this time.");
    }

    public ScheduleForThisTimeIsFullException(String message) {
        super(message);
    }
}
