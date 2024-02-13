package app.domain.model.Exceptions;

public class NotPreviousDoseForThatVaccineException extends Exception{

    public NotPreviousDoseForThatVaccineException(){
        super("Doesn't exist previous dose for that Vaccine ");
    }

    public NotPreviousDoseForThatVaccineException(String msg){
        super(msg);
    }

}
