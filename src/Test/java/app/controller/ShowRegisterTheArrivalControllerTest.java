package app.controller;

import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.Time;
import app.domain.model.User.User;
import app.domain.model.User.UserStore;
import app.domain.model.VaccinationCenter.VaccinationCenterDTO;
import app.domain.model.WaitingUser.WaitingUserStore;
import app.domain.model.WaitingUser.WaitingUser;
import app.domain.model.WaitingUser.WaitingUserDTO;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ShowRegisterTheArrivalControllerTest {
    @Test
    void checkWaitingUserList() throws ParseException, ListIsEmptyException {
        ShowWaitingListController showWaitingListController = new ShowWaitingListController();

        RegisterVaccinationCenterController registerVaccinationCenterController =new RegisterVaccinationCenterController();
        registerVaccinationCenterController.registerVaccinationCenter("persona2","moradinha","123456349", "isso@isep.pt", "123456789", "isep.pt", "10:00", "21:00", "20", "20", true);
        registerVaccinationCenterController.saveVaccinationCenter();

        ActorCenterController actorCenterController =new ActorCenterController();
        ArrayList<VaccinationCenterDTO> vaccinationCenterDTOS = actorCenterController.getActorCenter();
        actorCenterController.selectCenter(0);

        WaitingUserStore waitingUserStore = new WaitingUserStore();


        UserStore userStore = new UserStore();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        String strBirthDate = "28-05-2003";
        Date dateBirth = df.parse(strBirthDate);

        User user= userStore.createUser("Pessoa","Male",dateBirth,"Rua",934746524,"a@a.pt",756567576,56786867);
        AuthFacade authFacade = new AuthFacade();
        userStore.addUser(user, authFacade);

        Time time = new Time(20,20);

        WaitingUser waitingUser = new WaitingUser(user,time);

        waitingUserStore.addtoWaitingUserList(waitingUser);

        try {
            ArrayList<WaitingUserDTO> waitingUserDTO= showWaitingListController.checkWaitingUserList();
        } catch (ListIsEmptyException e) {
            assertNotNull(e);
        }
    }
}
