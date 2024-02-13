package app.domain.model.Exceptions;

public class NotDosageWasFoundedForThatVaccineException extends Exception{

    public NotDosageWasFoundedForThatVaccineException(){
        super("Dosage was not founded for that Vaccine ");
    }

    public NotDosageWasFoundedForThatVaccineException(String msg){
        super(msg);
    }

}
