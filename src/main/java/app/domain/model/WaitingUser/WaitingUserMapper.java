package app.domain.model.WaitingUser;
import app.domain.model.Time;
import app.domain.model.User.User;
import java.util.ArrayList;

public class WaitingUserMapper {

    /**
     * This method transform the Array List of Waiting User objects into Array List of Waiting Users DTO objects
     * @param  waitingUsersList
     * @return  waitingUsersListDTO
     */

    public  ArrayList<WaitingUserDTO> toDTO(ArrayList<WaitingUser> waitingUsersList) {
        ArrayList<WaitingUserDTO> waitingUsersListDTO = new ArrayList<>();
            for (int i=0; i<waitingUsersList.size(); i++){
                WaitingUser wl = waitingUsersList.get(i);
                WaitingUserDTO wlDTO= toDTO(wl);
                waitingUsersListDTO.add(wlDTO);
            }
            return waitingUsersListDTO;

    }

    /**
     * This method transform the Waiting User object on a Waiting User DTO object
     * @param wl
     * @return WaitingUserDTO
     */

    private WaitingUserDTO toDTO(WaitingUser wl){
        Time time= wl.getArrivalTime();
        User user= wl.getUser();
        return new WaitingUserDTO(time,user);
    }

}
