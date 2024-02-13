package app.controller;

import app.domain.model.Exceptions.*;
import app.domain.model.Time;
import app.domain.model.User.SNSUserMapper;

import app.domain.model.User.User;
import app.domain.model.User.UserDTO;
import app.domain.model.User.UserStore;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.VaccineDTO;
import app.domain.model.Vaccine.VaccineMapper;
import app.domain.model.Vaccine.VaccineStore;
import app.domain.model.VaccineAdministration.VaccineAdministration;
import app.domain.model.VaccineAdministration.VaccineAdministrationDTO;
import app.domain.model.VaccineSchedule.VaccineScheduleDTO;
import app.domain.model.VaccineSchedule.VaccineScheduleStore;
import app.domain.model.VaccineType.VaccineType;
import app.domain.model.VaccineType.VaccineTypeMapper;
import app.domain.model.WaitingUser.WaitingUserStore;
import app.domain.shared.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import static java.lang.System.getProperties;

public class VaccineAdministrationController {

    private App app;
    private VaccinationCenter center;

    public VaccineAdministrationController(){
        this.app=  App.getInstance();
        this.center = app.getCompany().getActorCenter();
    }

    public VaccinationCenter getCenter(){
        return center;
    }

    public ArrayList<UserDTO> getUserFromWaitingUserList() throws ListIsEmptyException {
        WaitingUserStore waitingUserStore = center.getWaitingUserStore();
        return waitingUserStore.getUserFromWaitingUserList();
    }

    public VaccineScheduleDTO getVaccineSchedule(int snsNumber){
        VaccineScheduleStore vaccineScheduleStore = center.getVaccineScheduleStore();
        return vaccineScheduleStore.checkIfThereIsAnAppointment(snsNumber);
    }

    public Time getArrivalTime(int snsNumber) throws NoArrivalTimeForSnsNumberException {
        WaitingUserStore waitingUserStore = center.getWaitingUserStore();
        return  waitingUserStore.checkArrivalTimeBySnsNumber(snsNumber);
    }


    public ArrayList<VaccineDTO> getVaccineByTypeAndAge(VaccineType vaccineType, int age){
        VaccineStore vaccineStore = app.getCompany().getVaccineStore();
        return vaccineStore.getVaccineByTypeAndAge(vaccineType, age);
    }

    public int verifyAgeInAgeGroup(int age, VaccineDTO vaccine) throws AgeIsNotMatchingWithAgeGroupException {
        VaccineMapper vaccineMapper = new VaccineMapper();
        return vaccineMapper.toModel(vaccine).verifyAgeInAgeGroup(age);
    }

    public void saveNewVaccineAdministration(VaccineAdministrationDTO vaccineAdministrationDTO, UserDTO userDTO){

        SNSUserMapper snsUserMapper = new SNSUserMapper();
        User user = snsUserMapper.toModel(userDTO);
        VaccineAdministration vaccineAdministration = user.createNewVaccineAdministration(vaccineAdministrationDTO);
        user.saveNewVaccineAdministration(vaccineAdministration);
        
        UserStore userStore = app.getCompany().getUserStore();
        userStore.updateUser(user);

        WaitingUserStore waitingUserStore = center.getWaitingUserStore();
        waitingUserStore.removeFromWaitingList(user);

    }

    public boolean verifyLotNumber(UserDTO userDTO, String lotNumber) throws MissingCorrectFormatToLotNumber {
        SNSUserMapper userMapper = new SNSUserMapper();
        return userMapper.toModel(userDTO).verifyLotNumber(lotNumber);
    }

    public boolean verifyIfExistVaccinationRecord(VaccineType vaccineType, UserDTO userDTO){
        SNSUserMapper userMapper = new SNSUserMapper();
        VaccineTypeMapper vaccineTypeMapper = new VaccineTypeMapper();
        return userMapper.toModel(userDTO).verifyIfExistVaccinationRecord(vaccineTypeMapper.toDTO(vaccineType));
    }

    public VaccineDTO getVaccine(VaccineType vaccineType, UserDTO userDTO) throws NotVaccineForThatVaccineTypeException {
        SNSUserMapper userMapper = new SNSUserMapper();
        VaccineTypeMapper vaccineTypeMapper = new VaccineTypeMapper();
        return userMapper.toModel(userDTO).getVaccine(vaccineTypeMapper.toDTO(vaccineType));
    }
    public int getNextDose(VaccineType vaccineType, UserDTO userDTO) throws NotPreviousDoseForThatVaccineException {
        SNSUserMapper userMapper = new SNSUserMapper();
        VaccineTypeMapper vaccineTypeMapper = new VaccineTypeMapper();
        return userMapper.toModel(userDTO).getNextDose(vaccineTypeMapper.toDTO(vaccineType));
    }
    public double getNextDosage(int position, int dose, UserDTO userDTO,VaccineType vaccineType, VaccineDTO vaccineDTO) throws NotDosageWasFoundedForThatVaccineException {
        SNSUserMapper userMapper = new SNSUserMapper();
        VaccineTypeMapper vaccineTypeMapper = new VaccineTypeMapper();
        return userMapper.toModel(userDTO).getNextDosage(position,dose, vaccineTypeMapper.toDTO(vaccineType),vaccineDTO);
    }

    public int getAge (UserDTO userDTO){
        SNSUserMapper userMapper = new SNSUserMapper();
        return userMapper.toModel(userDTO).getAge(userMapper.toModel(userDTO).getBirthDate());
    }

    public VaccineAdministrationDTO getDTO (VaccineScheduleDTO vaccineSchedule, Time arrivalTime, UserDTO user, VaccineDTO vaccineDTO, int dose, Date administrationDate, Time administrationTime, Date leavingDate, Time leavingTime, String lotNumber){
         VaccineMapper vaccineMapper = new VaccineMapper();
         VaccinationCenter vaccinationCenter = center;
        return new VaccineAdministrationDTO(vaccinationCenter, vaccineSchedule, arrivalTime, user, vaccineMapper.toModel(vaccineDTO), dose, administrationDate, administrationTime, leavingDate, leavingTime, lotNumber);
    }

    public int getRecoveryPeriod() throws IOException {
        Properties props = getProperties();
        try {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();

            String recoveryPeriodString = props.getProperty(Constants.PARAMS_RECOVERY_PERIOD);
            return Integer.parseInt(recoveryPeriodString);
        } catch(IOException ex) {ex.printStackTrace();}
        throw new IOException();
    }

    public UserDTO getUserByNumber(ArrayList<UserDTO> userList, int select) throws MissingUserException {

        for(int i=0 ; i<userList.size(); i++){
            if(userList.get(i).getSnsNumber() == select){
                UserDTO user= userList.get(i);
                return user;
            }
        }
        throw new MissingUserException();
    }

    public VaccineDTO getVaccineByName(String select, ArrayList<VaccineDTO> vaccineList) throws NameNotFoundException {
        for(int i = 0 ; i<vaccineList.size(); i++){
            if (vaccineList.get(i).getBrand()==select){
                return vaccineList.get(i);
            }
        }
        throw new NameNotFoundException();
    }
}

