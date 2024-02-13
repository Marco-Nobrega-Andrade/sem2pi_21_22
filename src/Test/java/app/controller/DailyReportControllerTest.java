package app.controller;

import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.Time;
import app.domain.model.User.UserStore;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenterStore;
import app.domain.model.Vaccine.Vaccine;
import app.domain.model.VaccineAdministration.VaccineAdministration;
import app.domain.model.VaccineType.VaccineTypeDTO;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DailyReportControllerTest {

    @Test
    void createReport() {

        DailyReportController controller = new DailyReportController();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        try {
            controller.createReport(today);
        } catch (ListIsEmptyException e) {
            assertNotNull(e);
        } catch (ClassNotFoundException e) {
            assertNull(e);
        } catch (InstantiationException e) {
            assertNull(e);
        } catch (IllegalAccessException e) {
            assertNull(e);
        }



    }

}