package app.domain.model.Exceptions;

public class NoArrivalTimeForSnsNumberException extends Exception{
    public NoArrivalTimeForSnsNumberException(){
        super("there isn´t an Arrival Time with that SNS Number");
    }
    public NoArrivalTimeForSnsNumberException(String msg){
        super(msg);
    }
}
