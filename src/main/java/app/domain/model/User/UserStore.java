package app.domain.model.User;

import app.domain.model.Exceptions.MissingUserException;
import app.domain.model.PwdGenerator;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccineAdministration.VaccineAdministrationStore;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserStore implements Serializable {
    private ArrayList<User> usersList = new ArrayList<>();
    private ArrayList<String> userCredentials = new ArrayList<>();
    private final int MINUTES_OF_WORK = 720;
    private int id;

    public User createUser(String name, String sex, Date birthDate, String address,
                           int phoneNumber, String eMail, int snsNumber,
                           int ccNumber){
        this.id = usersList.size()+1;
        return (new User(this.id,name,sex,birthDate,address,phoneNumber,eMail,snsNumber,ccNumber));
    }


    /**
     * Checks if there is an employee with the same email/phoneNumber/CitizenCardNumber
     * @param u Employee created by the user
     */
    public void validateNewUser(User u){
        for (User user : usersList) {
            if (u.getEmail().equals(user.getEmail())){
                throw new IllegalArgumentException("Couldn't save. This email already exists");
            }else if (u.getPhoneNumber()==user.getPhoneNumber()){
                throw new IllegalArgumentException("Couldn't save. This phone number already exists");
            }else if (u.getCcNumber()==user.getCcNumber()){
                throw new IllegalArgumentException("Couldn't save. This Citizen Card Number already exists");
            }else if (u.getSnsNumber() == user.getSnsNumber()){
                throw new IllegalArgumentException("Couldn't save. This Sns Number already exists");
            }
        }
    }

    public void addUser(User u, AuthFacade authFacade){
        validateNewUser(u);
        String pwd = PwdGenerator.generatePassword();
        authFacade.addUserWithRole(u.getName(), u.getEmail(), pwd, Constants.ROLE_SNSUSER);
        usersList.add(u);

        userCredentials.add(u.getName()+","+u.getEmail() + "," + pwd);
    }

    public void addUserWithCredentials(AuthFacade authFacade){
        for(String us: userCredentials){
            String [] userAtri =  us.split(",");
            authFacade.addUserWithRole(userAtri[0], userAtri[1], userAtri[2], Constants.ROLE_SNSUSER);
        }
    }

    public List<String> getUsersCredentials(){
        return userCredentials;
    }

    /**
     * Checks if there is a user registered on the system
     * @param snsNumber
     * @throws MissingUserException There is no user registered on the system
     */
    public void checkForUser(int snsNumber){
        boolean flag = false;
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getSnsNumber() == snsNumber) {
                flag = true;
            }
        }
            if (!flag){
                throw new MissingUserException();
            }

    }

    public User getUserBySnsNumber(int snsNumber){
        User user = null;
        boolean flag = false;
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getSnsNumber() == snsNumber) {
                flag = true;
                user = usersList.get(i);
            }
        }
        if (!flag){
            throw new MissingUserException();
        }
        return user;
    }

    public int getUserSNSNumberWithEmail(Email userEmail) {
        for (User user : usersList){
            if (user.getEmail().equals(userEmail)){
                return user.getSnsNumber();
            }
        }
        return 0;
    }

    public void updateUser(User user) {
        boolean flag = false;
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getSnsNumber() == user.getSnsNumber()) {
                flag = true;
                usersList.set(i,user);
            }
        }
        if (!flag) {
            throw new MissingUserException();
        }
    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }


    public List<Date> findUserFullyVaccinatedOnIntervalAndCenter(Date start, Date end, VaccinationCenter center){
        List<Date> returnList = new ArrayList<>();
        for(User u :  usersList){
            VaccineAdministrationStore vaccineAdministrationStore = u.getVaccineAdministrationStore();
            Date date = vaccineAdministrationStore.verifyIfIsFullyVaccinatedOnInterval(start,end,center);
            if(date != null){
                returnList.add(date);
            }
        }
        return returnList;
    }
}
