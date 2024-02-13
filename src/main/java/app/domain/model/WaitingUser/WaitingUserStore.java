package app.domain.model.WaitingUser;


import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.Exceptions.MissingUserException;
import app.domain.model.Exceptions.NoArrivalTimeForSnsNumberException;
import app.domain.model.Exceptions.UserIsAlreadyInTheWaitingListException;
import app.domain.model.Time;
import app.domain.model.User.SNSUserMapper;
import app.domain.model.User.User;
import app.domain.model.User.UserDTO;
import app.domain.model.User.UserStore;
import pt.isep.lei.esoft.auth.mappers.UserMapper;

import java.io.Serializable;
import java.util.ArrayList;

public class WaitingUserStore implements Serializable {

    private ArrayList<WaitingUser> waitingUsersList = new ArrayList<>();

    /**
     * This method can get a user by is snsNumber and create a WaitingUser : waitingUser with the user and the current time.
     * Also validates if the waitingUser is already in the waitingList and if that isn`t the case add him to the list
     * @param snsNumber
     * @param userStore Place where are saved all users in the sistem
     * @throws MissingUserException
     * @throws UserIsAlreadyInTheWaitingListException
     */
    public void createWaitingUser(int snsNumber, UserStore userStore) throws MissingUserException, UserIsAlreadyInTheWaitingListException {
        User user = userStore.getUserBySnsNumber(snsNumber);
        if(user == null){
            throw new MissingUserException();
        }
        WaitingUser waitingUser = new WaitingUser(user,Time.currentTime());

        validateWaitingUser(waitingUser);

        addtoWaitingUserList(waitingUser);
    }

    /**
     * Validades if the waitingUser is already registered in the system and if he is throws an error
     * @param waitingUser
     * @throws UserIsAlreadyInTheWaitingListException
     */
    public void validateWaitingUser(WaitingUser waitingUser) throws UserIsAlreadyInTheWaitingListException {
        if(waitingUsersList.size()!=0){
            for(WaitingUser i : waitingUsersList){
                if (i.isEquals(waitingUser)){
                    throw new UserIsAlreadyInTheWaitingListException();
                }
            }
        }
    }

    /**
     * Add the waitingUser to the waitingUsersList
     * @param waitingUser
     */
    public void addtoWaitingUserList(WaitingUser waitingUser){
        waitingUsersList.add(waitingUser);
    }


    /**
     * This method checks for the existence of a Waiting User List, if it exists it returns the list from a DTO
     * @return  waitingUsersListDTO
     * @throws ListIsEmptyException
     */

    public ArrayList<WaitingUserDTO> checkWaitingUserList() throws ListIsEmptyException {
        if (!waitingUsersList.isEmpty()) {
            WaitingUserMapper Mapper = new WaitingUserMapper();
            return Mapper.toDTO(waitingUsersList);
        }
        else{
            throw new ListIsEmptyException("There is no waiting list registered on the system.");
        }
    }

    public ArrayList<UserDTO> getUserFromWaitingUserList() throws ListIsEmptyException {
        if (!waitingUsersList.isEmpty()) {
            ArrayList<User> List = new ArrayList<>();
            for (WaitingUser waitingUser : waitingUsersList) {
                User user = waitingUser.getUser();
                List.add(user);
            }
            SNSUserMapper userMapper = new SNSUserMapper();
            return userMapper.toDTO(List);
        }
        else{
            throw new ListIsEmptyException();
        }
    }

    public Time checkArrivalTimeBySnsNumber(int snsNumber) throws NoArrivalTimeForSnsNumberException {
        for(int i=0; i<waitingUsersList.size(); i++){
            WaitingUser waitingUser = waitingUsersList.get(i);
            User user = waitingUser.getUser();
            if (user.getSnsNumber()==snsNumber){
                WaitingUser trueUser = waitingUser;
                return trueUser.getArrivalTime();
            }
        }
        throw new NoArrivalTimeForSnsNumberException();
    }

    public void removeFromWaitingList(User user){
        for(int i=0; i<waitingUsersList.size(); i++){
            WaitingUser waitingUser = waitingUsersList.get(i);
            User user1 = waitingUser.getUser();
            if (user1.getSnsNumber()==user.getSnsNumber()){
               waitingUsersList.remove(i);
            }
        }
    }

}
