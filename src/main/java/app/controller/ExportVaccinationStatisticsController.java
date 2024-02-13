package app.controller;

import app.domain.model.Company;
import app.domain.model.User.UserStore;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccineAdministration.VaccineAdministrationStore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ExportVaccinationStatisticsController {
    private App app;
    private UserStore userStore;
    private VaccinationCenter actorCenter;


    public ExportVaccinationStatisticsController(){
        this.app = App.getInstance();
        this.actorCenter = app.getCompany().getActorCenter();
    }


    public List<String> getUsersFullyVaccinatedOnIntervalAndCenter(Date start, Date end){
        Company cmp = app.getCompany();
        userStore = cmp.getUserStore();
        int vaccinatedCount = 0;

        List<String> returnList = null;

        List<Date> usersFullyVaccinated = userStore.findUserFullyVaccinatedOnIntervalAndCenter(start,end,actorCenter);

        if(usersFullyVaccinated.isEmpty()){
            return null;
        }

        List<Date> sortedDateList = usersFullyVaccinated.stream()
                                        .sorted(Comparator.comparingLong(Date :: getTime))
                                        .collect(Collectors.toList());

        Date comparator = usersFullyVaccinated.get(0);

        for (Date date : sortedDateList) {
            if (date.compareTo(comparator) == 0) {
                vaccinatedCount++;
            } else {
                returnList.add("Date: " + date
                        + "Number of fully vaccinated users: " + vaccinatedCount);
                vaccinatedCount = 0;
                comparator = date;
                vaccinatedCount++;
            }
        }

        return returnList;
    }
}
