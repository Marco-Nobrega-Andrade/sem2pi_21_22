package app.domain.model.Exceptions;

public class MissingUserException extends IllegalArgumentException{
    public MissingUserException(){
        super("The user isn't registered on the system");
    }
    public MissingUserException(String msg) {
        super(msg);
    }
}
