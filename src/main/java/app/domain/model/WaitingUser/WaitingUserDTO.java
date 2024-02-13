package app.domain.model.WaitingUser;

import app.domain.model.Time;
import app.domain.model.User.User;

public class WaitingUserDTO {
    private User user;
    private Time arrivalTime;

    public WaitingUserDTO(Time time, User SNSnumber){
        this.user = SNSnumber;
        this.arrivalTime = time;
    }

    @Override
    public String toString() {
        return "SNS user:\n"+"User= {name="+user.getName()+", "+"sex="+user.getSex()+", "+"Birth Date="+user.getBirthDate()+", "+"SNS number="+user.getSnsNumber()+", "+"Phone Number="+user.getPhoneNumber()+"}"+
                "\nArrival Time= " + arrivalTime;
    }
}
