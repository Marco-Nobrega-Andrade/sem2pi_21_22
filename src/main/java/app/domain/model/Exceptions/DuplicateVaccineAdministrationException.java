package app.domain.model.Exceptions;

public class DuplicateVaccineAdministrationException extends Exception{
    public DuplicateVaccineAdministrationException() {
        super ("There already exists a vaccine administration for this user with this vaccine and dose number");
    }

    public DuplicateVaccineAdministrationException(String message) {
        super(message);
    }
}
