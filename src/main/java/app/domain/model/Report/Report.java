package app.domain.model.Report;

import app.domain.model.User.User;
import app.domain.model.VaccinationCenter.VaccinationCenter;

import java.util.ArrayList;
import java.util.Calendar;

public interface Report {
    ArrayList<String> checkNumberOfPeopleVaccinated(ArrayList<VaccinationCenter> centerList, ArrayList<User> userList, Calendar today);
}
