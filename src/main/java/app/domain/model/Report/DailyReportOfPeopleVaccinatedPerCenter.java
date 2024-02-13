package app.domain.model.Report;

import app.domain.model.User.User;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccineAdministration.VaccineAdministrationStore;

import java.util.ArrayList;
import java.util.Calendar;

public class DailyReportOfPeopleVaccinatedPerCenter implements Report{

    /**
     * check if user was vaccinated and in which center
     * @param centerList List of all vaccination centers in the system
     * @param userList  List of all users in the system
     * @param today Day in which this class run
     * @return ArrayList of String with information
     */
    @Override
    public ArrayList<String> checkNumberOfPeopleVaccinated(ArrayList<VaccinationCenter> centerList, ArrayList<User> userList, Calendar today) {
        ArrayList<String> listOfInformation = new ArrayList<>();
        int total;
        int contador;
        listOfInformation.add(today.get(Calendar.DAY_OF_MONTH) + "-" + (today.get(Calendar.MONTH)+1) +"-" + today.get(Calendar.YEAR));
        for( VaccinationCenter center: centerList){
            total=0;
            for(User user: userList){
                VaccineAdministrationStore vaccineAdministrationStore = user.getVaccineAdministrationStore();
                contador = vaccineAdministrationStore.checkIfUserWasVaccinatedToday(center, today);
                total= total +contador;
            }
            listOfInformation.add(createStringWithInformation(center,total));
        }
        return listOfInformation;
    }

    public String createStringWithInformation(VaccinationCenter center, int total) {
        return center.getName() +" = " + total;
    }
}
