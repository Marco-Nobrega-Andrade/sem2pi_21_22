package app.domain.model.Exceptions;

public class MissingCorrectFormatToLotNumber extends Exception {

    public MissingCorrectFormatToLotNumber(){
        super("Incorrect String Format for Lot Number: \n you must have 5 alphanumeric characters - 2 Numeric characters ");
    }

    public MissingCorrectFormatToLotNumber(String msg){
        super(msg);
    }

}
