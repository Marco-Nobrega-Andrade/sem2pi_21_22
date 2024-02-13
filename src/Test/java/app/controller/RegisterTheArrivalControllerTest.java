package app.controller;

import app.domain.model.Exceptions.NoScheduleAppointmentForSnsNumberException;
import app.domain.model.Exceptions.UserIsAlreadyInTheWaitingListException;
import app.domain.model.User.User;
import app.domain.model.User.UserStore;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTheArrivalControllerTest {

    /**
     * Test if the program can detect if the user doesn't have an appointment
     * @throws ParseException
     */
    @Test
    void checkWaitingList() throws ParseException {
        RegisterVaccinationCenterController registerVaccinationCenterController =new RegisterVaccinationCenterController();
        registerVaccinationCenterController.registerVaccinationCenter("nome","Morada","888888888", "alguea@isep.pt", "123458789", "websitea.com", "10:00", "21:00", "20", "20", true);
        registerVaccinationCenterController.saveVaccinationCenter();


        ReceptionistVaccineScheduleController vaccineScheduleController  = new ReceptionistVaccineScheduleController();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strScheduleDate = "10-10-2022";
        String strBirthDate = "10-10-2003";
        Date dateSchedule = df.parse(strScheduleDate);
        Date dateBirth = df.parse(strBirthDate);

        SpecifyVaccineTypeController specifyVaccineTypeController = new SpecifyVaccineTypeController();
        specifyVaccineTypeController.createVaccineType("17345", "asdas", "mrna");
        specifyVaccineTypeController.saveVaccineType();

        UserStore store = new UserStore();
        User user = store.createUser("Pessoa", "Female", dateBirth,"Rua dos Tordos, 8",932222333 ,"paulo@gmail.com", 123456789, 31231213);
        AuthFacade authFacade = new AuthFacade();
        store.addUser(user, authFacade);

        vaccineScheduleController.obtainVaccineType(0);
        vaccineScheduleController.obtainVaccinationCenter(0);

        vaccineScheduleController.createVaccineSchedule(123456789,dateSchedule,"16:00");



        ActorCenterController actorCenterController =new ActorCenterController();
        actorCenterController.selectCenter(0);
        RegisterTheArrivalController registerTheArrivalController = new RegisterTheArrivalController();
        try {
            registerTheArrivalController.checkAppointmentList(123133123);
        } catch (NoScheduleAppointmentForSnsNumberException e) {
            assertNotNull(e);
        }
    }

    /**
     * Test if the program can add a WaitingUser and that it can't be duplicate
     * @throws ParseException
     */
    @Test
    void addWaitingList() throws ParseException {
        App app = App.getInstance();
        UserStore userStore = app.getCompany().getUserStore();
        AuthFacade authFacade = app.getCompany().getAuthFacade();

        RegisterVaccinationCenterController registerVaccinationCenterController =new RegisterVaccinationCenterController();
        registerVaccinationCenterController.registerVaccinationCenter("as","address","123456321", "pedro@isep.pt", "123456798", "www.com", "10:00", "21:00", "20", "20", true);
        registerVaccinationCenterController.saveVaccinationCenter();

        ActorCenterController actorCenterController =new ActorCenterController();
        actorCenterController.selectCenter(0);
        RegisterTheArrivalController registerTheArrivalController = new RegisterTheArrivalController();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strBirthDate = "10-10-2003";
        Date dateBirth = df.parse(strBirthDate);
        userStore.addUser(userStore.createUser("Pessoa", "Female", dateBirth,"Rua dos Tordos, 8",932222333 ,"paulo@gmail.com", 123456789, 31231213), authFacade);
        try {
            registerTheArrivalController.addWaitingUser(123456789);
        } catch (UserIsAlreadyInTheWaitingListException e) {
            assertNull(e);
        }
        try {
            registerTheArrivalController.addWaitingUser(123456789);
        } catch (UserIsAlreadyInTheWaitingListException e) {
            assertNotNull(e);
        }

    }
}