package app.domain.model.Exceptions;

public class AgeIsNotMatchingWithAgeGroupException extends Exception{

    public AgeIsNotMatchingWithAgeGroupException(){
        super("User doesn't match with this Age Group ");
    }

    public AgeIsNotMatchingWithAgeGroupException(String msg){
        super(msg);
    }

}
