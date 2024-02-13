package app.controller;

import app.domain.model.Exceptions.AgeIsNotMatchingWithAgeGroupException;
import app.domain.model.Exceptions.MissingCorrectFormatToLotNumber;
import app.domain.model.Time;
import app.domain.model.User.SNSUserMapper;
import app.domain.model.User.User;
import app.domain.model.User.UserStore;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.Vaccine;
import app.domain.model.Vaccine.VaccineMapper;
import app.domain.model.Vaccine.VaccineStore;
import app.domain.model.VaccineAdministration.VaccineAdministration;
import app.domain.model.VaccineAdministration.VaccineAdministrationDTO;
import app.domain.model.VaccineAdministration.VaccineAdministrationStore;
import app.domain.model.VaccineType.VaccineType;
import app.domain.model.VaccineType.VaccineTypeDTO;
import app.domain.model.VaccineType.VaccineTypeMapper;
import app.domain.model.VaccineType.VaccineTypeStore;
import app.domain.model.WaitingUser.WaitingUser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VaccineAdministrationControllerTest {

    @Test
    void verifyAgeInAgeGroup() throws AgeIsNotMatchingWithAgeGroupException {

        VaccineAdministrationController vaccineAdministrationController = new VaccineAdministrationController();

        VaccineTypeStore vaccineTypeStore = new VaccineTypeStore();
        VaccineType vaccineType = vaccineTypeStore.createVaccineType("55332", "doenças", "mrna");
        VaccineTypeMapper vaccineTypeMapper = new VaccineTypeMapper();
        VaccineTypeDTO vaccineTypeDTO = vaccineTypeMapper.toDTO(vaccineType);

        ArrayList<Integer> agegroup = new ArrayList<>(Arrays.asList(1, 40, 41, 120));
        ArrayList<Double> dosage = new ArrayList<>(Arrays.asList(1.1, 2.2, 3.3, 4.4));
        ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(14));

        VaccineStore vaccineStore = new VaccineStore();
        Vaccine vaccine = vaccineStore.createNewVaccine(vaccineTypeDTO, "BOM BOM BOM", 2, agegroup, dosage, 2, interval);
        VaccineMapper vaccineMapper = new VaccineMapper();

        int age = 10;

        int agePositionObtained = vaccineAdministrationController.verifyAgeInAgeGroup(age,vaccineMapper.toDTO(vaccine));
        int agePositionExpected = 1;
        assertEquals(agePositionExpected,agePositionExpected);

    }

    @Test
    void saveNewVaccineAdministration() throws ParseException, IOException {

        VaccineAdministrationController vaccineAdministrationController = new VaccineAdministrationController();
        VaccineAdministrationStore vaccineAdministrationStore = new VaccineAdministrationStore();
        VaccinationCenter vaccinationCenter = new VaccinationCenter("Amapa", "Rua do colégio Santa Bartolomea Capitanha", "962101030", "bartolomea@bartolomea.com.br", "962101030", "bartolomea.com.br", "08:00", "22:00", "5", "500", true);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strScheduleDate = "10-10-2022";
        String strBirthDate = "10-10-2003";
        Date dateSchedule = df.parse(strScheduleDate);
        Date dateBirth = df.parse(strBirthDate);

        UserStore store = new UserStore();
        User user = store.createUser("Personita", "Male", dateBirth, "Rua dos piolhos", 932214333, "paulinho@gmail.com", 124356789, 31236213);

        Time time = new Time(14, 20);
        WaitingUser waitingUser = new WaitingUser(user, time);

        VaccineTypeStore vaccineTypeStore = new VaccineTypeStore();
        VaccineType vaccineType = vaccineTypeStore.createVaccineType("55332", "doenças", "mrna");
        VaccineTypeMapper vaccineTypeMapper = new VaccineTypeMapper();
        VaccineTypeDTO vaccineTypeDTO = vaccineTypeMapper.toDTO(vaccineType);

        ArrayList<Integer> agegroup = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Double> dosage = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0));
        ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(14));

        VaccineStore vaccineStore = new VaccineStore();
        Vaccine vaccine = vaccineStore.createNewVaccine(vaccineTypeDTO, "BOM BOM BOM", 2, agegroup, dosage, 2, interval);

        Time timeSchedule = new Time(14, 10);

        String strAdmDate = "10-10-2003";
        Date dateAdm = df.parse(strAdmDate);

        Time timeAdm = new Time(14, 40);
        int recovery = vaccineAdministrationController.getRecoveryPeriod();

        Time timeLeave = timeAdm.addMinutes(recovery);

        VaccineAdministration vaccineAdministrationExpected = new VaccineAdministration(vaccinationCenter, vaccine, 124356789, 1, "22AAA-22", "Personita", dateSchedule, timeSchedule, dateSchedule, time, dateAdm, timeAdm, dateAdm, timeLeave);
        VaccineAdministrationDTO vaccineAdministrationDTO = new VaccineAdministrationDTO(vaccinationCenter, vaccine, 124356789, 1, "22AAA-22", "Personita", dateSchedule, timeSchedule, dateSchedule, time, dateAdm, timeAdm, dateAdm, timeLeave);

        VaccineAdministration vaccineAdministration = vaccineAdministrationStore.createNewVaccineAdministration(vaccineAdministrationDTO);

        assertEquals(vaccineAdministrationExpected, vaccineAdministration);

    }

    @Test
    void verifyLotNumber() throws ParseException, MissingCorrectFormatToLotNumber {

        VaccineAdministrationController vaccineAdministrationController = new VaccineAdministrationController();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strBirthDate = "10-11-2003";
        Date dateBirth = df.parse(strBirthDate);

        UserStore userStore = new UserStore();
        User user = userStore.createUser("Personita2", "Male", dateBirth, "Rua dos piolhos", 932214333, "paulinho@gmail.com", 124356789, 35636213);

        SNSUserMapper userMapper = new SNSUserMapper();

        boolean validate = vaccineAdministrationController.verifyLotNumber(userMapper.toDTO(user), "22CCC-22");
        boolean validateExpectation = true;

        assertEquals(validateExpectation, validate);

    }

    @Test
    void verifyIfExistVaccinationRecord() throws ParseException {

        VaccineAdministrationController vaccineAdministrationController = new VaccineAdministrationController();

        VaccineTypeStore vaccineTypeStore = new VaccineTypeStore();
        VaccineType vaccineType = vaccineTypeStore.createVaccineType("54332", "doentes", "mrna");

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strBirthDate = "15-11-2003";
        Date dateBirth = df.parse(strBirthDate);

        UserStore store = new UserStore();
        User user = store.createUser("Personita", "Male", dateBirth, "Rua dos piolhos", 932214333, "paulinho@gmail.com", 124356789, 31236213);
        SNSUserMapper userMapper = new SNSUserMapper();

        boolean validate = vaccineAdministrationController.verifyIfExistVaccinationRecord(vaccineType, userMapper.toDTO(user));
        boolean validateExpectation = false;
        assertEquals(validateExpectation, validate);
    }
}