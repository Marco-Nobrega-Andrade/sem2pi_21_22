package app.domain.model.VaccineAdministration;

import app.domain.model.Time;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.Vaccine;

import java.util.ArrayList;
import java.util.Date;

public class VaccineAdministrationMapper {

    public VaccineAdministrationMapper(){}

    public VaccineAdministration toModel(VaccineAdministrationDTO vaccineAdministrationDTO){

        VaccinationCenter vaccinationCenter = vaccineAdministrationDTO.getVaccinationCenter();
        Vaccine vaccine= vaccineAdministrationDTO.getVaccine();
        int snsNumber= vaccineAdministrationDTO.getSnsNumber();
        int doseNumber = vaccineAdministrationDTO.getDoseNumber();
        String lotNumber = vaccineAdministrationDTO.getLotNumber();
        String userName = vaccineAdministrationDTO.getUserName();
        Date vaccineScheduleDate = vaccineAdministrationDTO.getVaccineScheduleDate();
        Time vaccineScheduleTime= vaccineAdministrationDTO.getVaccineScheduleTime();
        Date arrivalDate = vaccineAdministrationDTO.getArrivalDate();
        Time arrivalTime = vaccineAdministrationDTO.getArrivalTime();
        Date administrationDate = vaccineAdministrationDTO.getAdministrationDate();
        Time administrationTime = vaccineAdministrationDTO.getAdministrationTime() ;
        Date leavingDate = vaccineAdministrationDTO.getLeavingDate();
        Time leavingTime= vaccineAdministrationDTO.getLeavingTime();

        return new VaccineAdministration(vaccinationCenter, vaccine, snsNumber, doseNumber, lotNumber, userName, vaccineScheduleDate, vaccineScheduleTime, arrivalDate, arrivalTime, administrationDate, administrationTime, leavingDate, leavingTime);

    }

    public ArrayList<VaccineAdministrationDTO> toDTO(ArrayList<VaccineAdministration> vaccineAdministrationList) {
        ArrayList<VaccineAdministrationDTO> vaccineAdministrationDTOList= new ArrayList<>();
        for (VaccineAdministration vaccineAdministration : vaccineAdministrationList){
            VaccineAdministrationDTO vaccineAdministrationDTO = toDTO(vaccineAdministration);
            vaccineAdministrationDTOList.add(vaccineAdministrationDTO);
        }
        return vaccineAdministrationDTOList;
    }

    private VaccineAdministrationDTO toDTO(VaccineAdministration vaccineAdministration) {
        VaccinationCenter vaccinationCenter = vaccineAdministration.getVaccinationCenter();
        int snsNumber= vaccineAdministration.getSnsNumber();
        String snsUserName = vaccineAdministration.getUserName();
        Vaccine vaccine = vaccineAdministration.getVaccine();
        int doseNumber = vaccineAdministration.getDoseNumber();
        String lotNumber = vaccineAdministration.getLotNumber();
        Date vaccineScheduleDate = vaccineAdministration.getVaccineScheduleDate();
        Time vaccineScheduleTime = vaccineAdministration.getVaccineScheduleTime();
        Date arrivalDate = vaccineAdministration.getArrivalDate();
        Time arrivalTime = vaccineAdministration.getArrivalTime();
        Date administrationDate = vaccineAdministration.getAdministrationDate();
        Time administrationTime = vaccineAdministration.getAdministrationTime();
        Date leavingDate = vaccineAdministration.getLeavingDate();
        Time leavingTime = vaccineAdministration.getLeavingTime();
        return new VaccineAdministrationDTO(vaccinationCenter,vaccine,snsNumber,doseNumber,lotNumber,snsUserName,vaccineScheduleDate,vaccineScheduleTime,arrivalDate,arrivalTime,administrationDate,administrationTime,leavingDate,leavingTime);

    }
}
