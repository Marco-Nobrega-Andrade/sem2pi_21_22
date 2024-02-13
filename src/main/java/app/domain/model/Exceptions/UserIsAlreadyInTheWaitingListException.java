package app.domain.model.Exceptions;

public class UserIsAlreadyInTheWaitingListException extends Exception {

    public UserIsAlreadyInTheWaitingListException(){
        super("The User is already in the waiting list");
    }
    public UserIsAlreadyInTheWaitingListException(String msg){
        super(msg);
    }
}
