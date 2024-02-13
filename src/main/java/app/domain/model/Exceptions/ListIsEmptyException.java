package app.domain.model.Exceptions;

public class ListIsEmptyException extends Exception{
    public ListIsEmptyException(){
        super("The list is empty");
    }
    public ListIsEmptyException(String msg){
        super(msg);
    }
}
