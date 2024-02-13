package app.domain.model.SortingAlgorithms;

import app.domain.model.Time;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.Vaccine;
import app.domain.model.VaccineAdministration.VaccineAdministration;
import app.domain.model.VaccineType.VaccineTypeDTO;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {

    @Test
    void sortListLeaving() throws ParseException {
        VaccinationCenter vc = new VaccinationCenter("fgfgf", "w", "121212121", "w@w.com", "121212121", "w.com", "10:00", "20:00", "5", "10", true);
        ArrayList<Integer> ageGroup = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1));
        ArrayList<Double> dosage = new ArrayList<>(Arrays.asList(1.2,3.4,5.6,6.7));
        Vaccine v = new Vaccine(new VaccineTypeDTO("12345","a","mrna"),"pfizer",2,ageGroup,dosage,2,interval);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse("10-10-2022");
        VaccineAdministration va1 = new VaccineAdministration(vc,v,111111111,1,"12345-12","AAAA",date,new Time("8:00"), date,new Time("8:00"), date, new Time("8:15"), date, new Time("8:45"));
        VaccineAdministration va2 = new VaccineAdministration(vc,v,222222222,1,"12345-12","BBBB",date,new Time("8:00"), date,new Time("8:01"), date, new Time("8:16"), date, new Time("8:46"));
        VaccineAdministration va3 = new VaccineAdministration(vc,v,333333333,1,"12345-12","CCCC",date,new Time("8:00"), date,new Time("8:02"), date, new Time("8:17"), date, new Time("8:47"));
        VaccineAdministration va4 = new VaccineAdministration(vc,v,444444444,1,"12345-12","DDDD",date,new Time("8:00"), date,new Time("8:03"), date, new Time("8:18"), date, new Time("8:48"));

        VaccineAdministration[] list = {va2,va1,va4,va3};
        VaccineAdministration[] expectedArray = {va1, va2, va3, va4};
        ArrayList<VaccineAdministration> expectedList= new ArrayList<>(List.of(expectedArray));
        BubbleSort alg = new BubbleSort();
        ArrayList<VaccineAdministration> obtainedList = alg.sortList("leaving","ascending",list);

        assertEquals(obtainedList,expectedList);
    }

    @Test
    void sortListArrival() throws ParseException {
        VaccinationCenter vc = new VaccinationCenter("fgfgf", "w", "121212121", "w@w.com", "121212121", "w.com", "10:00", "20:00", "5", "10", true);
        ArrayList<Integer> ageGroup = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1));
        ArrayList<Double> dosage = new ArrayList<>(Arrays.asList(1.2,3.4,5.6,6.7));
        Vaccine v = new Vaccine(new VaccineTypeDTO("12345","a","mrna"),"pfizer",2,ageGroup,dosage,2,interval);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse("10-10-2022");
        VaccineAdministration va1 = new VaccineAdministration(vc,v,111111111,1,"12345-12","AAAA",date,new Time("8:00"), date,new Time("8:00"), date, new Time("8:15"), date, new Time("8:45"));
        VaccineAdministration va2 = new VaccineAdministration(vc,v,222222222,1,"12345-12","BBBB",date,new Time("8:00"), date,new Time("8:01"), date, new Time("8:16"), date, new Time("8:46"));
        VaccineAdministration va3 = new VaccineAdministration(vc,v,333333333,1,"12345-12","CCCC",date,new Time("8:00"), date,new Time("8:02"), date, new Time("8:17"), date, new Time("8:47"));
        VaccineAdministration va4 = new VaccineAdministration(vc,v,444444444,1,"12345-12","DDDD",date,new Time("8:00"), date,new Time("8:03"), date, new Time("8:18"), date, new Time("8:48"));

        VaccineAdministration[] list = {va2,va1,va4,va3};
        VaccineAdministration[] expectedArray = {va1, va2, va3, va4};
        ArrayList<VaccineAdministration> expectedList= new ArrayList<>(List.of(expectedArray));
        BubbleSort alg = new BubbleSort();
        ArrayList<VaccineAdministration> obtainedList = alg.sortList("arrival","ascending",list);

        assertEquals(obtainedList,expectedList);
    }

    @Test
    void sortListLeavingDescendingOrder() throws ParseException {
        VaccinationCenter vc = new VaccinationCenter("fgfgf", "w", "121212121", "w@w.com", "121212121", "w.com", "10:00", "20:00", "5", "10", true);
        ArrayList<Integer> ageGroup = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1));
        ArrayList<Double> dosage = new ArrayList<>(Arrays.asList(1.2,3.4,5.6,6.7));
        Vaccine v = new Vaccine(new VaccineTypeDTO("12345","a","mrna"),"pfizer",2,ageGroup,dosage,2,interval);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse("10-10-2022");
        VaccineAdministration va1 = new VaccineAdministration(vc,v,111111111,1,"12345-12","AAAA",date,new Time("8:00"), date,new Time("8:00"), date, new Time("8:15"), date, new Time("8:45"));
        VaccineAdministration va2 = new VaccineAdministration(vc,v,222222222,1,"12345-12","BBBB",date,new Time("8:00"), date,new Time("8:01"), date, new Time("8:16"), date, new Time("8:46"));
        VaccineAdministration va3 = new VaccineAdministration(vc,v,333333333,1,"12345-12","CCCC",date,new Time("8:00"), date,new Time("8:02"), date, new Time("8:17"), date, new Time("8:47"));
        VaccineAdministration va4 = new VaccineAdministration(vc,v,444444444,1,"12345-12","DDDD",date,new Time("8:00"), date,new Time("8:03"), date, new Time("8:18"), date, new Time("8:48"));

        VaccineAdministration[] list = {va2,va1,va4,va3};
        VaccineAdministration[] expectedArray = {va4, va3, va2, va1};
        ArrayList<VaccineAdministration> expectedList= new ArrayList<>(List.of(expectedArray));
        BubbleSort alg = new BubbleSort();
        ArrayList<VaccineAdministration> obtainedList = alg.sortList("leaving","descending",list);

        assertEquals(obtainedList,expectedList);
    }
}