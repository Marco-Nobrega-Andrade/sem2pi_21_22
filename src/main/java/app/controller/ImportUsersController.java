package app.controller;

import app.domain.model.User.User;
import app.domain.model.User.UserStore;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ImportUsersController {


    private App app;

    public ImportUsersController(){
        this.app = App.getInstance();
    }


    public boolean importUsers(List<String> users) throws ParseException {

        if(users.get(0).split(";").length > 1){
            importUsersCsvWithHeader(users);
        }else{
            importUsersCsvNoHeader(users);
        }
        return true;
    }

    public boolean importUsersCsvWithHeader(List<String> users) throws ParseException {
        UserStore userStore = app.getCompany().getUserStore();
        AuthFacade authFacade = app.getCompany().getAuthFacade();

        for(int i = 1; i < users.size(); i++){
            String[] attributes = users.get(i).split(";");
            Date birthDate;
            SimpleDateFormat df;
            if(attributes[2].split("-").length > 1){
                df = new SimpleDateFormat("yyyy-MM-dd");
            }else{
                df = new SimpleDateFormat("dd/MM/yyyy");
            }
            birthDate = df.parse(attributes[2]);
            User newUser = userStore.createUser(attributes[0],attributes[1], birthDate,
                    attributes[3],Integer.parseInt(attributes[4]),attributes[5],Integer.parseInt(attributes[6]),
                    Integer.parseInt(attributes[7]));
            userStore.addUser(newUser,authFacade);

        }
        return true;
    }

    public boolean importUsersCsvNoHeader(List<String> users) throws ParseException {
        UserStore userStore = app.getCompany().getUserStore();
        AuthFacade authFacade = app.getCompany().getAuthFacade();


        for(int i = 0; i < users.size(); i++){
            String[] attributes = users.get(i).split(",");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = df.parse(attributes[2]);

            User newUser = userStore.createUser(attributes[0],attributes[1], birthDate,
                    attributes[3],Integer.parseInt(attributes[4]),attributes[5],Integer.parseInt(attributes[6]),
                    Integer.parseInt(attributes[7]));

            userStore.addUser(newUser,authFacade);

        }
        return true;
    }

    public List<String> getUsersCredentials(){
        UserStore userStore = app.getCompany().getUserStore();
        return userStore.getUsersCredentials();
    }
}
