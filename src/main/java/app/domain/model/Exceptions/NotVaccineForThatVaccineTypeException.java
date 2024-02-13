package app.domain.model.Exceptions;

public class NotVaccineForThatVaccineTypeException extends Exception{

    public NotVaccineForThatVaccineTypeException(){
        super("Doesn't exist Vaccine for that Vaccine Type ");
    }

    public NotVaccineForThatVaccineTypeException(String msg){
        super(msg);
    }

}
