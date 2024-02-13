package app.controller;

import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.User.User;
import app.domain.model.User.UserStore;
import app.domain.model.VaccinationCenter.VaccinationCenter;

import java.util.ArrayList;
import java.util.Date;

public class AnalyzePerformanceController {


    public AnalyzePerformanceController() {
    }

    /**
     * Analyzes the performance of a vaccination center and saves its analysis.
     *
     * @param date          the date of the desired performance analysis
     * @param timeIntervals is the length of the sections of time divided by the entire working day of the vaccination center
     * @param algorithmUsed name of the algorithm used to analyze the performance of a center
     */

    public void analyzePerformance(Date date, int timeIntervals, String algorithmUsed) throws ListIsEmptyException {
        App app = App.getInstance();
        VaccinationCenter vaccinationCenter = app.getCompany().getActorCenter();
        UserStore userStore = app.getCompany().getUserStore();

        ArrayList<User> usersList = userStore.getUsersList();
        checkIfUsersListIsEmpty(usersList);

        vaccinationCenter.analyzePerformance(date, timeIntervals, algorithmUsed, usersList);
    }

    /**
     * Gets all performance analysis info
     *
     * @return String containing all information
     */

    public String getPerformanceInfo() {
        App app = App.getInstance();
        VaccinationCenter vaccinationCenter = app.getCompany().getActorCenter();

        return vaccinationCenter.getPerformanceInfo();
    }

    public void checkIfUsersListIsEmpty(ArrayList<User> usersList) throws ListIsEmptyException {
        if (usersList.isEmpty()) {
            throw new ListIsEmptyException("UsersList");
        }
    }
}
