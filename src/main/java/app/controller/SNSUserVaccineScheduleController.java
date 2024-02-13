package app.controller;

import app.domain.model.Exceptions.*;
import app.domain.model.User.UserStore;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenterDTO;
import app.domain.model.VaccinationCenter.VaccinationCenterMapper;
import app.domain.model.VaccinationCenter.VaccinationCenterStore;
import app.domain.model.VaccineSchedule.VaccineSchedule;
import app.domain.model.VaccineSchedule.VaccineScheduleDTO;
import app.domain.model.VaccineSchedule.VaccineScheduleStore;
import app.domain.model.VaccineType.VaccineType;
import app.domain.model.VaccineType.VaccineTypeDTO;
import app.domain.model.VaccineType.VaccineTypeMapper;
import app.domain.model.VaccineType.VaccineTypeStore;
import pt.isep.lei.esoft.auth.UserSession;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.Date;

public class SNSUserVaccineScheduleController {
    private App app;
    private int snsNumber;
    private VaccineType vaccineType;
    private VaccineTypeStore vaccineTypeStore;
    private VaccinationCenter vaccinationCenter;
    private VaccinationCenterStore vaccinationCenterStore;
    private VaccineSchedule vaccineSchedule;
    private VaccineScheduleStore vaccineScheduleStore;

    /**
     * Instantiates the controller, getting the instances of App, VaccineTypeStore and VaccinationCenterStore classes already in the system.
     */
    public SNSUserVaccineScheduleController(){
        this.app = App.getInstance();
        vaccineTypeStore = app.getCompany().getVaccineTypeStore();
        vaccinationCenterStore = app.getCompany().getVaccinationCenterStore();
    }

    /**
     * Gets the SNS user number of the user that is already logged in the system.
     */
    public void getUserSNSNumber() {
        UserSession userSession = app.getCurrentUserSession();
        Email userEmail = userSession.getUserId();
        UserStore userStore = app.getCompany().getUserStore();
        this.snsNumber = userStore.getUserSNSNumberWithEmail(userEmail);
    }

    /**
     * Gets the Vaccine Type List from vaccineTypeStore and creates a Vaccine Type DTO list
     * @return the list of vaccine type DTO's
     * @throws ListIsEmptyException if there aren't any vaccine types registered in the system
     */
    public ArrayList<VaccineTypeDTO> getVaccineTypeList () throws ListIsEmptyException {
        ArrayList<VaccineType> vaccineTypeList = vaccineTypeStore.getVaccineTypeList();
        if(vaccineTypeList.isEmpty()){
            throw new ListIsEmptyException("There is no vaccine type registered on the system.");
        }
        VaccineTypeMapper vaccineTypeMapper = new VaccineTypeMapper();
        return vaccineTypeMapper.toDTO(vaccineTypeList);
    }

    /**
     * Obtains the vaccine type from the vaccine type list
     * @param index option inputted by the user
     */
    public void obtainVaccineType(int index){
        vaccineType = vaccineTypeStore.getVaccineTypeFromStore(index);
    }

    /**
     * Gets the  Vaccination Center List from vaccinationCenterStore and creates a Vaccination Center DTO List
     * @return the list of vaccination center DTO's
     * @throws ListIsEmptyException if there aren't any vaccination centers that administer the vaccine type chosen by the user registered in the system
     */
    public ArrayList<VaccinationCenterDTO> getVaccinationCenterList () throws ListIsEmptyException {
        ArrayList<VaccinationCenter> vaccinationCenterList = new ArrayList<>();
        if (!vaccineTypeStore.checkIfOngoingOutbreakIsTheChosenVaccineType(vaccineType)){
            vaccinationCenterList = vaccinationCenterStore.getHealthcareCentersList();
        }else{
            vaccinationCenterList = vaccinationCenterStore.getAllVaccinationCenters();
        }
        if(vaccinationCenterList.isEmpty()){
            throw new ListIsEmptyException("There is no vaccination centre registered on the system.");
        }
        VaccinationCenterMapper vaccinationCenterMapper = new VaccinationCenterMapper();
        return vaccinationCenterMapper.toDTO(vaccinationCenterList);
    }

    /**
     * Obtains the vaccination center from the vaccination center list
     * @param index option inputted by the user
     */
    public void obtainVaccinationCenter(int index) {
        vaccinationCenter = vaccinationCenterStore.getVaccinationCenterFromStore(index, vaccineTypeStore.checkIfOngoingOutbreakIsTheChosenVaccineType(vaccineType));
    }

    /**
     * creates vaccine schedule based on all inputs given by the user
     * @param date date of the schedule inputted by the user
     * @param time time of the schedule inputted by the user
     */
    public void createVaccineSchedule (Date date, String time){
        VaccineScheduleDTO scheduleDTO = new VaccineScheduleDTO(snsNumber, vaccineType,date,time);
        vaccineScheduleStore = vaccinationCenter.getVaccineScheduleStore();
        vaccineSchedule = vaccineScheduleStore.createVaccineSchedule(scheduleDTO);

    }

    /**
     * Checks for all acceptance criteria regarding the schedule
     * @throws DuplicateScheduleException there already exists a schedule for the same person and vaccine type in any center
     * @throws ScheduleForThisTimeIsFullException the center capacity is already maxed out
     * @throws TimeOutOfBoundsException the time inputted by there user is before that current time, is before the opening hours or after the closing hours
     * @throws ScheduleAtTheSameTimeException there already exists a schedule for another person at that time
     */
    public void validateVaccineSchedule() throws DuplicateScheduleException, ScheduleForThisTimeIsFullException, TimeOutOfBoundsException, ScheduleAtTheSameTimeException, TimeNotPredefinedException {
        vaccinationCenterStore.checkForDuplicateSchedules(vaccineSchedule);
        vaccineScheduleStore.checkForSchedulesBeforeNow(vaccineSchedule);
        vaccineScheduleStore.checkForSchedulesOutOfCenterWorkingHours(vaccineSchedule);
        vaccineScheduleStore.checkForPredefinedHours(vaccineSchedule);
        vaccineScheduleStore.checkForSchedulesPassedMaximumCapacity(vaccineSchedule);
    }

    /**
     * Saves the vaccine schedule in vaccineScheduleStore
     */
    public void saveVaccineSchedule() {
        vaccineScheduleStore.addVaccineSchedule(vaccineSchedule);
    }

    /**
     * Gets all schedule info
     * @return String containing all information
     */
    public String getScheduleInfo(){
        return ("Vaccination Center: " + vaccinationCenter.getName()+"\n"+vaccineSchedule.toString());
    }
}
