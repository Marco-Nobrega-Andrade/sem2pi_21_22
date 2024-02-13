package app.controller;

import app.domain.model.Exceptions.*;
import app.domain.model.User.User;
import app.domain.model.User.UserStore;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SNSUserVaccineScheduleControllerTest {

    @Test
    void ScheduleBeforeCenterOpeningHoursTest() throws ParseException {
        SNSUserVaccineScheduleController vaccineScheduleController  = new SNSUserVaccineScheduleController();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strScheduleDate = "10-10-2022";
        String strBirthDate = "10-10-2003";
        Date dateSchedule = df.parse(strScheduleDate);
        Date dateBirth = df.parse(strBirthDate);

        SpecifyVaccineTypeController specifyVaccineTypeController = new SpecifyVaccineTypeController();
        specifyVaccineTypeController.createVaccineType("56789", "asdas", "mrna");
        specifyVaccineTypeController.saveVaccineType();

        RegisterVaccinationCenterController registerVaccinationCenterController = new RegisterVaccinationCenterController();
        registerVaccinationCenterController.registerVaccinationCenter("fgfgf", "w", "121212121", "w@w.com", "121212121", "w.com", "10:00", "20:00", "5", "10", true);
        registerVaccinationCenterController.saveVaccinationCenter();

        UserStore store = new UserStore();
        User user = store.createUser("Pessoa", "Female", dateBirth,"Rua dos Tordos, 8",932222333 ,"paulo@gmail.com", 123456789, 31231213);
        AuthFacade authFacade = new AuthFacade();
        store.addUser(user, authFacade);

        vaccineScheduleController.obtainVaccineType(0);
        vaccineScheduleController.obtainVaccinationCenter(0);

        vaccineScheduleController.createVaccineSchedule(dateSchedule,"05:00");


        String expectedMessage = "The vaccination center isn't open at that time.";
        try {
            vaccineScheduleController.validateVaccineSchedule();
        }catch (TimeOutOfBoundsException | DuplicateScheduleException | ScheduleForThisTimeIsFullException | ScheduleAtTheSameTimeException | TimeNotPredefinedException e){
            assertTrue(expectedMessage.contains(e.getMessage()));
        }
    }

    @Test
    void ScheduleRightBeforeTheCenterClosesTest() throws ParseException {

        SNSUserVaccineScheduleController vaccineScheduleController  = new SNSUserVaccineScheduleController();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strScheduleDate = "10-10-2022";
        String strBirthDate = "10-10-2003";
        Date dateSchedule = df.parse(strScheduleDate);
        Date dateBirth = df.parse(strBirthDate);

        SpecifyVaccineTypeController specifyVaccineTypeController = new SpecifyVaccineTypeController();
        specifyVaccineTypeController.createVaccineType("23232", "asdas", "mrna");
        specifyVaccineTypeController.saveVaccineType();

        RegisterVaccinationCenterController registerVaccinationCenterController = new RegisterVaccinationCenterController();
        registerVaccinationCenterController.registerVaccinationCenter("prib", "ab", "212121212", "s@s.com", "212121212", "s.com", "10:00", "20:00", "5", "10", true);
        registerVaccinationCenterController.saveVaccinationCenter();

        UserStore store = new UserStore();
        User user = store.createUser("Pessoa", "Female", dateBirth,"Rua dos Tordos, 8",932222333 ,"paulo@gmail.com", 987654321, 31231213);
        AuthFacade authFacade = new AuthFacade();
        store.addUser(user, authFacade);

        vaccineScheduleController.obtainVaccineType(0);
        vaccineScheduleController.obtainVaccinationCenter(0);

        vaccineScheduleController.createVaccineSchedule(dateSchedule,"19:57");

        String expectedMessage = "There is no time to administer the vaccine. The vaccination center will close soon after that.";
        try {
            vaccineScheduleController.validateVaccineSchedule();
        }catch (TimeOutOfBoundsException | DuplicateScheduleException | ScheduleForThisTimeIsFullException | ScheduleAtTheSameTimeException | TimeNotPredefinedException e){
            assertTrue(expectedMessage.contains(e.getMessage()));
        }
    }

    @Test
    void ScheduleBeforeTheCurrentTime() throws ParseException {

        SNSUserVaccineScheduleController vaccineScheduleController  = new SNSUserVaccineScheduleController();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strScheduleDate = "10-10-2010";
        String strBirthDate = "10-10-2003";
        Date dateSchedule = df.parse(strScheduleDate);
        Date dateBirth = df.parse(strBirthDate);

        SpecifyVaccineTypeController specifyVaccineTypeController = new SpecifyVaccineTypeController();
        specifyVaccineTypeController.createVaccineType("VinhO", "asdas", "mrna");
        specifyVaccineTypeController.saveVaccineType();

        RegisterVaccinationCenterController registerVaccinationCenterController = new RegisterVaccinationCenterController();
        registerVaccinationCenterController.registerVaccinationCenter("FF", "FF", "323232323", "f@f.com", "323232323", "f.com", "10:00", "20:00", "5", "10", true);
        registerVaccinationCenterController.saveVaccinationCenter();

        UserStore store = new UserStore();
        User user = store.createUser("Pessoa", "Female", dateBirth,"Rua dos Tordos, 8",932222333 ,"paulo@gmail.com", 222222222, 22222222);
        AuthFacade authFacade = new AuthFacade();
        store.addUser(user, authFacade);

        vaccineScheduleController.obtainVaccineType(0);
        vaccineScheduleController.obtainVaccinationCenter(0);

        vaccineScheduleController.createVaccineSchedule(dateSchedule,"15:00");

        String expectedMessage = "Cannot schedule a vaccination a day before today.";
        try {
            vaccineScheduleController.validateVaccineSchedule();
        }catch (TimeOutOfBoundsException | DuplicateScheduleException | ScheduleForThisTimeIsFullException | ScheduleAtTheSameTimeException | TimeNotPredefinedException e){
            assertTrue(expectedMessage.contains(e.getMessage()));
        }
    }


}