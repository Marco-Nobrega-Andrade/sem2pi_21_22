package app.domain.model.Exceptions;

public class NoScheduleAppointmentForSnsNumberException extends Exception{
    public NoScheduleAppointmentForSnsNumberException(){
        super("Today there isn´t an appointment with that SNS Number");
    }
    public NoScheduleAppointmentForSnsNumberException(String msg){
        super(msg);
    }
}
