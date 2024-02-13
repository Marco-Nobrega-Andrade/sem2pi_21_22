package app.controller;

import app.domain.model.Company;
import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.Report.DailyReportOfPeopleVaccinatedPerCenter;
import app.domain.model.Report.Report;
import app.domain.model.SortingAlgorithms.SortingAlgorithms;
import app.domain.model.User.User;
import app.domain.model.User.UserStore;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenterStore;
import app.domain.model.VaccineAdministration.VaccineAdministration;
import app.domain.shared.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

public class DailyReportController {

    /**
     * Method to create the report
     * @param today Day the report is generated
     * @return ArrayList of Strings
     * @throws ListIsEmptyException If List of vaccination centers and users is empty
     * @throws ClassNotFoundException If class used to create the report doesn't exist
     * @throws InstantiationException  If class can't be instatiated
     * @throws IllegalAccessException
     */
    public ArrayList<String> createReport(Calendar today) throws ListIsEmptyException, ClassNotFoundException, InstantiationException, IllegalAccessException {
       App app = App.getInstance();
       Company comp = app.getCompany();
        UserStore userStore = comp.getUserStore();
        VaccinationCenterStore vaccinationCenterStore = comp.getVaccinationCenterStore();
        ArrayList<VaccinationCenter> centerList = vaccinationCenterStore.getAllVaccinationCenters();
        ArrayList<User> userList = userStore.getUsersList();

        if(centerList.size()==0 && userList.size()==0){
            throw new ListIsEmptyException("There isn't any Vaccination centers and Users in the system");
        }else if(centerList.size()==0){
            throw new ListIsEmptyException("There isn't any Vaccination centers in the system");
        }else if(userList.size()==0){
            throw new ListIsEmptyException("There isn't any Users in the system");
        }

        Properties props = new Properties();
        try
        {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
            String className = props.getProperty(Constants.PARAMS_REPORT_METHOD_DESIGNATION);
            if (className != null ) {
                Class<?> oClass = Class.forName("app.domain.model.Report." + className);
                Report report = (Report) oClass.newInstance();
                return report.checkNumberOfPeopleVaccinated(centerList, userList, today);
            }
        }
        catch(IOException ex)
        {
        }


        return null;
    }


}
