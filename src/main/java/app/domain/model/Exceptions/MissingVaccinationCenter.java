package app.domain.model.Exceptions;

public class MissingVaccinationCenter extends Exception{

    public MissingVaccinationCenter(){
        super("A vaccination center must be chosen");
    }

    public MissingVaccinationCenter(String msg) {
        super(msg);
    }

}
