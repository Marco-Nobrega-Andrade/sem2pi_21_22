package app.domain.model.VaccineAdministration;

import app.domain.model.Time;
import app.domain.model.User.User;
import app.domain.model.User.UserDTO;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.Vaccine;
import app.domain.model.VaccineSchedule.VaccineScheduleDTO;


import java.util.Calendar;
import java.util.Date;

public class VaccineAdministrationDTO {

    private VaccinationCenter vaccinationCenter;
    private Vaccine vaccine;
    private int snsNumber;
    private int doseNumber;
    private String lotNumber;
    private String userName;
    private Date vaccineScheduleDate;
    private Time vaccineScheduleTime;
    private Date arrivalDate;
    private Time arrivalTime;
    private Date administrationDate;
    private Time administrationTime;
    private Date leavingDate;
    private Time leavingTime;

    public VaccineAdministrationDTO(VaccinationCenter vaccinationCenter, Vaccine vaccine, int snsNumber, int doseNumber, String lotNumber, String userName, Date vaccineScheduleDate, Time vaccineScheduleTime, Date arrivalDate, Time arrivalTime, Date administrationDate, Time administrationTime, Date leavingDate, Time leavingTime) {
        this.vaccinationCenter = vaccinationCenter;
        this.vaccine = vaccine;
        this.snsNumber = snsNumber;
        this.doseNumber = doseNumber;
        this.lotNumber = lotNumber;
        this.userName = userName;
        this.vaccineScheduleDate = vaccineScheduleDate;
        this.vaccineScheduleTime = vaccineScheduleTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.administrationDate = administrationDate;
        this.administrationTime = administrationTime;
        this.leavingDate = leavingDate;
        this.leavingTime = leavingTime;
    }

    public VaccineAdministrationDTO(VaccinationCenter vaccinationCenter, Vaccine vaccine, User user, int doseNumber, String lotNumber, Date vaccineScheduleDate, Time vaccineScheduleTime, Date arrivalDate, Time arrivalTime, Date administrationDate, Time administrationTime, Date leavingDate, Time leavingTime) {
        this.vaccinationCenter = vaccinationCenter;
        this.vaccine = vaccine;
        this.snsNumber = user.getSnsNumber();
        this.doseNumber = doseNumber;
        this.lotNumber = lotNumber;
        this.userName = user.getName();
        this.vaccineScheduleDate = vaccineScheduleDate;
        this.vaccineScheduleTime = vaccineScheduleTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.administrationDate = administrationDate;
        this.administrationTime = administrationTime;
        this.leavingDate = leavingDate;
        this.leavingTime = leavingTime;
    }

    public VaccineAdministrationDTO(VaccinationCenter vaccinationCenter, VaccineScheduleDTO vaccineSchedule, Time arrivalTime, UserDTO user, Vaccine vaccine, int doseNumber, Date administrationDate, Time administrationTime, Date leavingDate, Time leavingTime, String lotNumber){
        this.vaccinationCenter = vaccinationCenter;
        this.vaccine= vaccine;
        this.snsNumber= user.getSnsNumber();
        this.doseNumber = doseNumber;
        this.lotNumber = lotNumber;
        this.userName= user.getName();
        this.vaccineScheduleDate = vaccineSchedule.getDate();
        this.vaccineScheduleTime = vaccineSchedule.getTime();
        this.arrivalDate = vaccineSchedule.getDate();
        this.arrivalTime = arrivalTime;
        this.administrationDate= administrationDate;
        this.administrationTime = administrationTime;
        this.leavingDate = leavingDate;
        this.leavingTime= leavingTime;
    }

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public int getSnsNumber() {
        return snsNumber;
    }

    public void setSnsNumber(int snsNumber) {
        this.snsNumber = snsNumber;
    }

    public int getDoseNumber() {
        return doseNumber;
    }

    public void setDoseNumber(int doseNumber) {
        this.doseNumber = doseNumber;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getVaccineScheduleDate() {
        return vaccineScheduleDate;
    }

    public void setVaccineScheduleDate(Date vaccineScheduleDate) {
        this.vaccineScheduleDate = vaccineScheduleDate;
    }

    public Time getVaccineScheduleTime() {
        return vaccineScheduleTime;
    }

    public void setVaccineScheduleTime(Time vaccineScheduleTime) {
        this.vaccineScheduleTime = vaccineScheduleTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getAdministrationDate() {
        return administrationDate;
    }

    public void setAdministrationDate(Date administrationDate) {
        this.administrationDate = administrationDate;
    }

    public Time getAdministrationTime() {
        return administrationTime;
    }

    public void setAdministrationTime(Time administrationTime) {
        this.administrationTime = administrationTime;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    public Time getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(Time leavingTime) {
        this.leavingTime = leavingTime;
    }

    public String toStringByCriteria(String criteria){
        Calendar calendar1 = Calendar.getInstance();
        if (criteria.equalsIgnoreCase("Arrival time")){
            calendar1.setTime(arrivalDate);
            return String.format("%d, %s, %d, %d-%d-%d %s",snsNumber,vaccine.getBrand(),doseNumber,calendar1.get(Calendar.DAY_OF_MONTH),calendar1.get(Calendar.MONTH)+1,calendar1.get(Calendar.YEAR),arrivalTime);
        }else{
            calendar1.setTime(leavingDate);
            return String.format("%d, %s, %d, %d-%d-%d %s",snsNumber,vaccine.getBrand(),doseNumber,calendar1.get(Calendar.DAY_OF_MONTH),calendar1.get(Calendar.MONTH)+1,calendar1.get(Calendar.YEAR),leavingTime);
        }
    }
}
