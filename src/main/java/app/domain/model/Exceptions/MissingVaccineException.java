package app.domain.model.Exceptions;

public class MissingVaccineException extends Exception{
    public MissingVaccineException() {
        super("Vaccine isn´t registered in the system");
    }

    public MissingVaccineException(String message) {
        super(message);
    }
}
