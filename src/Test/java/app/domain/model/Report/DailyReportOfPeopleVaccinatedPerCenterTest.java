package app.domain.model.Report;

import app.controller.SpecifyVaccineTypeController;
import app.domain.model.Time;
import app.domain.model.User.User;
import app.domain.model.User.UserStore;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.Vaccine;
import app.domain.model.VaccineAdministration.VaccineAdministration;
import app.domain.model.VaccineType.VaccineTypeDTO;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DailyReportOfPeopleVaccinatedPerCenterTest {

    @Test
    void checkNumberOfPeopleVaccinated() throws ParseException {


        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strBirthDate = "10-10-2003";
        Date dateBirth = df.parse(strBirthDate);

        UserStore store = new UserStore();
        User user1 = store.createUser("Pessoa1", "Female", dateBirth,"Rua dos Tordos, 8",932222333 ,"paulo@gmail.com", 123456789, 31231213);
        User user2 = store.createUser("Pessoa2", "Female", dateBirth,"Rua dos Tordos, 8",932222334 ,"paulo@gmail.com", 123456788, 31231212);
        User user3 = store.createUser("Pessoa3", "Female", dateBirth,"Rua dos Tordos, 8",932222335 ,"paulo@gmail.com", 123456787, 31231211);
        User user4 = store.createUser("Pessoa4", "Female", dateBirth,"Rua dos Tordos, 8",932222336 ,"paulo@gmail.com", 123456786, 31231210);

        ArrayList<Integer> ageGroup = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1));
        ArrayList<Double> dosage = new ArrayList<>(Arrays.asList(1.2,3.4,5.6,6.7));
        Vaccine v = new Vaccine(new VaccineTypeDTO("12345","a","mrna"),"pfizer",2,ageGroup,dosage,2,interval);

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);

        Date date = df.parse("10-10-2022");
        today.setTime(date);
        VaccinationCenter vc1 = new VaccinationCenter("Centro1", "Rua1", "121212121", "w@w.com", "121212121", "w.com", "10:00", "20:00", "5", "10", true);
        VaccinationCenter vc2 = new VaccinationCenter("Centro2","Rua2","123321123", "c1@gmail.com", "123321123", "c1.com","08:00","20:00","5","10",true);
        VaccineAdministration va1 = new VaccineAdministration(vc1,v,111111111,1,"12345-12","AAAA",date,new Time("8:00"), date,new Time("8:00"), date, new Time("8:15"), date, new Time("8:45"));
        VaccineAdministration va2 = new VaccineAdministration(vc1,v,222222222,1,"12345-12","BBBB",date,new Time("8:00"), date,new Time("8:01"), date, new Time("8:16"), date, new Time("8:46"));
        VaccineAdministration va3 = new VaccineAdministration(vc2,v,333333333,1,"12345-12","CCCC",date,new Time("8:00"), date,new Time("8:02"), date, new Time("8:17"), date, new Time("8:47"));
        VaccineAdministration va4 = new VaccineAdministration(vc2,v,444444444,1,"12345-12","DDDD",date,new Time("8:00"), date,new Time("8:03"), date, new Time("8:18"), date, new Time("8:48"));

        user1.saveNewVaccineAdministration(va1);
        user2.saveNewVaccineAdministration(va2);
        user3.saveNewVaccineAdministration(va3);
        user4.saveNewVaccineAdministration(va4);

        ArrayList<User> userArrayList= new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        ArrayList<VaccinationCenter> vaccinationCenterArrayList= new ArrayList<>(Arrays.asList(vc1, vc2));

        DailyReportOfPeopleVaccinatedPerCenter dailyReportOfPeopleVaccinatedPerCenter= new DailyReportOfPeopleVaccinatedPerCenter();
        ArrayList<String> info =dailyReportOfPeopleVaccinatedPerCenter.checkNumberOfPeopleVaccinated(vaccinationCenterArrayList,userArrayList,today);

        String data = "10-10-2022";
        String vc1Data = "Centro1 = 2";
        String vc2Data = "Centro2 = 2";
        assertEquals(info.get(0),data);
        assertEquals(info.get(1),vc1Data);
        assertEquals(info.get(2),vc2Data);

    }
}