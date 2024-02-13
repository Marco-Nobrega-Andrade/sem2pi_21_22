package app.domain.model.WaitingUser;

import app.domain.model.Time;
import app.domain.model.User.User;

import java.io.Serializable;

public class WaitingUser implements Serializable {
    private User user;
    private Time arrivalTime;

    public WaitingUser(User user, Time arrivalTime) {
        this.user = user;
        this.arrivalTime = arrivalTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Check if a waitingUser is equals to another by comparing if their snsNumber are the same and if that is the case return true
     * @param wu WaitingUser that we are going to compare
     * @return
     */
    public boolean isEquals(WaitingUser wu){
        if (wu.getUser().getSnsNumber()==this.user.getSnsNumber()){
            return true;
        }else{
            return false;
        }
    }


}
