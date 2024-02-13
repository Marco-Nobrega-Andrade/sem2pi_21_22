package app.domain.model.Exceptions;

public class BlankInputException extends Exception{
    public BlankInputException(){
        super("Input cannot be blank.");
    }
    public BlankInputException(String msg){
        super(msg);
    }
}
