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

class ReceptionistVaccineScheduleControllerTest {

    @Test
    void ScheduleBeforeCenterOpeningHoursTest() throws ParseException {
        ReceptionistVaccineScheduleController vaccineScheduleController  = new ReceptionistVaccineScheduleController();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strScheduleDate = "10-10-2022";
        String strBirthDate = "10-10-2003";
        Date dateSchedule = df.parse(strScheduleDate);
        Date dateBirth = df.parse(strBirthDate);

        SpecifyVaccineTypeController specifyVaccineTypeController = new SpecifyVaccineTypeController();
        specifyVaccineTypeController.createVaccineType("12345", "asdas", "mrna");
        specifyVaccineTypeController.saveVaccineType();

        RegisterVaccinationCenterController registerVaccinationCenterController = new RegisterVaccinationCenterController();
        registerVaccinationCenterController.registerVaccinationCenter("asdads", "asdaserr", "123456789", "agua@gmail.com", "123123123", "agua.com", "10:00", "20:00", "5", "10", true);
        registerVaccinationCenterController.saveVaccinationCenter();

        UserStore store = new UserStore();
        User user = store.createUser("Pessoa", "Female", dateBirth,"Rua dos Tordos, 8",932222333 ,"paulo@gmail.com", 123456789, 31231213);
        AuthFacade authFacade = new AuthFacade();
        store.addUser(user, authFacade);

        vaccineScheduleController.obtainVaccineType(0);
        vaccineScheduleController.obtainVaccinationCenter(0);

        vaccineScheduleController.createVaccineSchedule(123456789,dateSchedule,"05:00");


        String expectedMessage = "The vaccination center isn't open at that time.";
        try {
            vaccineScheduleController.validateVaccineSchedule();
        }catch (TimeOutOfBoundsException | DuplicateScheduleException | ScheduleForThisTimeIsFullException | ScheduleAtTheSameTimeException | TimeNotPredefinedException e){
            assertTrue(expectedMessage.contains(e.getMessage()));
        }
    }

    @Test
    void ScheduleRightBeforeTheCenterClosesTest() throws ParseException {

        ReceptionistVaccineScheduleController vaccineScheduleController  = new ReceptionistVaccineScheduleController();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strScheduleDate = "10-10-2022";
        String strBirthDate = "10-10-2003";
        Date dateSchedule = df.parse(strScheduleDate);
        Date dateBirth = df.parse(strBirthDate);

        SpecifyVaccineTypeController specifyVaccineTypeController = new SpecifyVaccineTypeController();
        specifyVaccineTypeController.createVaccineType("11111", "asdas", "mrna");
        specifyVaccineTypeController.saveVaccineType();

        RegisterVaccinationCenterController registerVaccinationCenterController = new RegisterVaccinationCenterController();
        registerVaccinationCenterController.registerVaccinationCenter("name", "adress", "123123123", "vinho@gmail.com", "404404404", "vinho.com", "10:00", "20:00", "5", "10", true);
        registerVaccinationCenterController.saveVaccinationCenter();

        UserStore store = new UserStore();
        User user = store.createUser("Pessoa", "Female", dateBirth,"Rua dos Tordos, 8",932222333 ,"paulo@gmail.com", 987654321, 31231213);
        AuthFacade authFacade = new AuthFacade();
        store.addUser(user, authFacade);

        vaccineScheduleController.obtainVaccineType(0);
        vaccineScheduleController.obtainVaccinationCenter(0);

        vaccineScheduleController.createVaccineSchedule(123456789,dateSchedule,"19:57");

        String expectedMessage = "There is no time to administer the vaccine. The vaccination center will close soon after that.";
        try {
            vaccineScheduleController.validateVaccineSchedule();
        }catch (TimeOutOfBoundsException | DuplicateScheduleException | ScheduleForThisTimeIsFullException | ScheduleAtTheSameTimeException | TimeNotPredefinedException e){
            assertTrue(expectedMessage.contains(e.getMessage()));
        }
    }

    @Test
    void ScheduleBeforeTheCurrentTime() throws ParseException {

        ReceptionistVaccineScheduleController vaccineScheduleController  = new ReceptionistVaccineScheduleController();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strScheduleDate = "10-10-2010";
        String strBirthDate = "10-10-2003";
        Date dateSchedule = df.parse(strScheduleDate);
        Date dateBirth = df.parse(strBirthDate);

        SpecifyVaccineTypeController specifyVaccineTypeController = new SpecifyVaccineTypeController();
        specifyVaccineTypeController.createVaccineType("AGUA1", "asdas", "mrna");
        specifyVaccineTypeController.saveVaccineType();

        RegisterVaccinationCenterController registerVaccinationCenterController = new RegisterVaccinationCenterController();
        registerVaccinationCenterController.registerVaccinationCenter("aaaaaaaaaaaa", "asdkase", "929258172", "madeira@gmail.com", "234234234", "madeira.com", "10:00", "20:00", "5", "10", true);
        registerVaccinationCenterController.saveVaccinationCenter();

        UserStore store = new UserStore();
        User user = store.createUser("Pessoa", "Female", dateBirth,"Rua dos Tordos, 8",932222333 ,"paulo@gmail.com", 22222222, 22222222);
        AuthFacade authFacade = new AuthFacade();
        store.addUser(user, authFacade);

        vaccineScheduleController.obtainVaccineType(0);
        vaccineScheduleController.obtainVaccinationCenter(0);

        vaccineScheduleController.createVaccineSchedule(123456789,dateSchedule,"15:00");

        String expectedMessage = "Cannot schedule a vaccination a day before today.";
        try {
            vaccineScheduleController.validateVaccineSchedule();
        }catch (TimeOutOfBoundsException | DuplicateScheduleException | ScheduleForThisTimeIsFullException | ScheduleAtTheSameTimeException | TimeNotPredefinedException e){
            assertTrue(expectedMessage.contains(e.getMessage()));
        }
    }

    @Test
    void ScheduleForTheSameTimeAsOtherPersonTest() throws ParseException {

        ReceptionistVaccineScheduleController vaccineScheduleController  = new ReceptionistVaccineScheduleController();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strScheduleDate = "10-10-2027";
        String strBirthDate = "10-10-2003";
        Date dateSchedule = df.parse(strScheduleDate);
        Date dateBirth = df.parse(strBirthDate);

        SpecifyVaccineTypeController specifyVaccineTypeController = new SpecifyVaccineTypeController();
        specifyVaccineTypeController.createVaccineType("AGUA2", "asdas", "mrna");
        specifyVaccineTypeController.saveVaccineType();

        RegisterVaccinationCenterController registerVaccinationCenterController = new RegisterVaccinationCenterController();
        registerVaccinationCenterController.registerVaccinationCenter("bbbbbbbbbb", "bbbbbb", "961262446", "porto@gmail.com", "961262446", "porto.com", "10:00", "20:00", "5", "10", true);
        registerVaccinationCenterController.saveVaccinationCenter();

        UserStore store = new UserStore();
        User user = store.createUser("Pessoa", "Female", dateBirth,"Rua dos Tordos, 8",961262446 ,"madeira@gmail.com", 123456789, 33333333);
        AuthFacade authFacade = new AuthFacade();
        store.addUser(user, authFacade);


        User user2 = store.createUser("OutraPessoa", "Female", dateBirth,"Rua dos Tordos, 8",929258172 ,"funchala@gmail.com", 333333333, 92925817);
        store.addUser(user2, authFacade);

        vaccineScheduleController.obtainVaccineType(0);
        vaccineScheduleController.obtainVaccinationCenter(0);

        vaccineScheduleController.createVaccineSchedule(123456789,dateSchedule,"15:00");
        vaccineScheduleController.saveVaccineSchedule();
        vaccineScheduleController.createVaccineSchedule(333333333, dateSchedule, "15:00");

        String expectedMessage = "A schedule already exists at that time";
        try {
            vaccineScheduleController.validateVaccineSchedule();
        }catch (TimeOutOfBoundsException | DuplicateScheduleException | ScheduleForThisTimeIsFullException | ScheduleAtTheSameTimeException | TimeNotPredefinedException e){
            assertTrue(expectedMessage.contains(e.getMessage()));
        }
    }

    @Test
    void ScheduleVaccineMoreThanTheMaxNumVaccinesSlot() throws ParseException {

        ReceptionistVaccineScheduleController vaccineScheduleController  = new ReceptionistVaccineScheduleController();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strScheduleDate = "10-10-2027";
        String strBirthDate = "10-10-2003";
        Date dateSchedule = df.parse(strScheduleDate);
        Date dateBirth = df.parse(strBirthDate);

        SpecifyVaccineTypeController specifyVaccineTypeController = new SpecifyVaccineTypeController();
        specifyVaccineTypeController.createVaccineType("AGUA3", "asdas", "mrna");
        specifyVaccineTypeController.saveVaccineType();

        RegisterVaccinationCenterController registerVaccinationCenterController = new RegisterVaccinationCenterController();
        registerVaccinationCenterController.registerVaccinationCenter("ccccccccc", "ccccccccc", "666666666", "maritimo@gmail.com", "666666666", "gaia.com", "10:00", "20:00", "5", "1", true);
        registerVaccinationCenterController.saveVaccinationCenter();

        UserStore store = new UserStore();
        User user = store.createUser("Pessoa", "Female", dateBirth,"Rua dos Tordos, 8",555555555 ,"santoantonio@gmail.com", 555555555, 55555555);
        AuthFacade authFacade = new AuthFacade();
        store.addUser(user, authFacade);


        User user2 = store.createUser("OutraPessoa", "Female", dateBirth,"Rua dos Tordos, 8",666666666 ,"portoSanto@gmail.com", 666666666, 66666666);
        store.addUser(user2, authFacade);

        vaccineScheduleController.obtainVaccineType(0);
        vaccineScheduleController.obtainVaccinationCenter(0);

        vaccineScheduleController.createVaccineSchedule(555555555,dateSchedule,"15:00");
        vaccineScheduleController.saveVaccineSchedule();
        vaccineScheduleController.createVaccineSchedule(666666666, dateSchedule, "18:00");

        String expectedMessage = "There are no more vaccines available to administer in this day.";
        try {
            vaccineScheduleController.validateVaccineSchedule();
        }catch (TimeOutOfBoundsException | DuplicateScheduleException | ScheduleForThisTimeIsFullException | ScheduleAtTheSameTimeException | TimeNotPredefinedException e){
            assertTrue(expectedMessage.contains(e.getMessage()));
        }
    }
}