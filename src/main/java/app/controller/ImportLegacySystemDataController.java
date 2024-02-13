package app.controller;

import app.domain.model.Exceptions.*;
import app.domain.model.SortingAlgorithms.SortingAlgorithms;
import app.domain.model.Time;
import app.domain.model.User.User;
import app.domain.model.User.UserStore;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.Vaccine;
import app.domain.model.Vaccine.VaccineStore;
import app.domain.model.VaccineAdministration.VaccineAdministration;
import app.domain.model.VaccineAdministration.VaccineAdministrationDTO;
import app.domain.model.VaccineAdministration.VaccineAdministrationMapper;
import app.domain.shared.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;



public class ImportLegacySystemDataController {
    private App app;
    private ArrayList<VaccineAdministration> vaccineAdministrationListToSort = new ArrayList<>();
    public ImportLegacySystemDataController() {
        app = App.getInstance();
    }

    /**
     * Registers the vaccine administrations from the file in the system
     * @param vaccineAdministrationStringList List with all info from the file in string format
     * @return ArrayList with lines that couldn't be imported
     */
    public ArrayList<Integer>[] importVaccineAdministrations(List<String> vaccineAdministrationStringList){
        ArrayList<Integer>[] arrayErrors = new ArrayList[2];
        ArrayList<Integer> errorLines = new ArrayList<>();
        ArrayList<Integer> duplicateAdministrationsLines = new ArrayList<>();
        arrayErrors[0] = errorLines;
        arrayErrors[1] = duplicateAdministrationsLines;
        UserStore userStore = app.getCompany().getUserStore();
        VaccineStore vaccineStore = app.getCompany().getVaccineStore();
        int i;
        for (i = 0; i < vaccineAdministrationStringList.size(); i++) {
            VaccineAdministration vaccineAdministration = null;
            try{
                String [] vaccineAdministrationInfo = vaccineAdministrationStringList.get(i).split(";");
                VaccinationCenter actorCenter = app.getCompany().getActorCenter();
                User user = userStore.getUserBySnsNumber(Integer.parseInt(vaccineAdministrationInfo[0]));
                Vaccine vaccine = vaccineStore.getVaccineByName(vaccineAdministrationInfo[1]);
                String doseString = vaccineAdministrationInfo[2];
                int doseNumber = getIntFromDoseString(doseString);
                String lotNumber = vaccineAdministrationInfo[3];
                VaccineAdministrationDTO vaccineAdministrationDTO = new VaccineAdministrationDTO(actorCenter,vaccine,user,doseNumber,lotNumber,
                        getDateFromDateTimeString(vaccineAdministrationInfo[4]),getTimeFromDateTimeString(vaccineAdministrationInfo[4]),
                        getDateFromDateTimeString(vaccineAdministrationInfo[5]),getTimeFromDateTimeString(vaccineAdministrationInfo[5]),
                        getDateFromDateTimeString(vaccineAdministrationInfo[6]),getTimeFromDateTimeString(vaccineAdministrationInfo[6]),
                        getDateFromDateTimeString(vaccineAdministrationInfo[7]),getTimeFromDateTimeString(vaccineAdministrationInfo[7]));

                vaccineAdministration = user.createNewVaccineAdministration(vaccineAdministrationDTO);
                user.checkForDuplicateAdministration(vaccineAdministration);
                user.saveNewVaccineAdministration(vaccineAdministration);
                vaccineAdministrationListToSort.add(vaccineAdministration);
            } catch (MissingVaccineException | ParseException | MissingUserException | IndexOutOfBoundsException e) {
                errorLines.add(i+1);
            }catch (DuplicateVaccineAdministrationException e) {
                duplicateAdministrationsLines.add(i+1);
                if (vaccineAdministration != null){
                    vaccineAdministrationListToSort.add(vaccineAdministration);
                }
            }
        }
        return arrayErrors;
    }

    /**
     * Transforms info in file to int dose number
     * @param doseString String with number dose
     * @return int dose number
     * @throws IllegalArgumentException when string dose is invalid (doesn't represent any number)
     */
    private int getIntFromDoseString(String doseString) throws IllegalArgumentException {
        if(doseString.equalsIgnoreCase("Primeira")){
            return 1;
        }else if(doseString.equalsIgnoreCase("Segunda")){
            return 2;
        }else if(doseString.equalsIgnoreCase("Terceira")) {
            return 3;
        }else if(doseString.equalsIgnoreCase("Quarta")) {
            return 4;
        }else if(doseString.equalsIgnoreCase("Quinta")) {
            return 5;
        }
        throw new IllegalArgumentException();
    }

    /**
     * Extracts date from string with date and time
     * @param str string with date and time
     * @return date
     * @throws ParseException when date isn't in a valid format
     */
    private Date getDateFromDateTimeString(String str) throws ParseException {
        String [] dateTime = str.split(" ");
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.parse(dateTime[0]);
    }

    /**
     * Extracts time from string with date and time
     * @param str string with date and time
     * @return time
     */
    private Time getTimeFromDateTimeString(String str){
        String [] dateTime = str.split(" ");
        return new Time(dateTime[1]);
    }

    /**
     * Sorts the list imported by criteria and order, using the sorting algorithm defined in the config file
     * @param criteria what the array should be sorted by (in this case, by arrival or leaving time)
     * @param order in what order, ascending or descending, should the array be sorted by
     * @return list ordered and with vaccineAdministrationDTO
     * @throws InstantiationException class can´t be instantiated
     * @throws IllegalAccessException class isn't accessible
     * @throws ClassNotFoundException sorting algorithm defined doesn't exist
     */
    public ArrayList<VaccineAdministrationDTO> sortVaccineAdministration(String criteria, String order) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        VaccineAdministrationMapper mapper = new VaccineAdministrationMapper();
        Properties props = new Properties();
        try
        {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex)
        {

        }
        String className = props.getProperty(Constants.PARAMS_SORT_METHOD_DESIGNATION);
        if (className != null ) {
            Class<?> oClass = Class.forName("app.domain.model.SortingAlgorithms." + className);
            SortingAlgorithms sortingAlgorithm = (SortingAlgorithms) oClass.newInstance();


            VaccineAdministration[] list = new VaccineAdministration[vaccineAdministrationListToSort.size()];
            for (int i = 0; i < vaccineAdministrationListToSort.size(); i++) {
                list[i] = vaccineAdministrationListToSort.get(i);
            }

            vaccineAdministrationListToSort = sortingAlgorithm.sortList(criteria,order,list);

        }
        return mapper.toDTO(vaccineAdministrationListToSort);


    }

    /**
     * Clears the vaccine administration list
     */
    public void clearList() {
        vaccineAdministrationListToSort.clear();
    }
}
