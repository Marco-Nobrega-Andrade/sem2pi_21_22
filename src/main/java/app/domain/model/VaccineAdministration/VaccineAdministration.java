package app.domain.model.VaccineAdministration;

import app.domain.model.Exceptions.*;
import app.domain.model.Time;
import app.domain.model.User.User;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.Vaccine;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class VaccineAdministration implements Serializable {

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

    /**
     * Constructor of the class VaccineAdministration
     *
     * @param vaccinationCenter
     * @param vaccine
     * @param snsNumber
     * @param doseNumber
     * @param lotNumber
     * @param userName
     * @param vaccineScheduleDate
     * @param vaccineScheduleTime
     * @param arrivalDate
     * @param arrivalTime
     * @param administrationDate
     * @param administrationTime
     * @param leavingDate
     * @param leavingTime
     */

    public VaccineAdministration(VaccinationCenter vaccinationCenter, Vaccine vaccine, int snsNumber, int doseNumber, String lotNumber, String userName, Date vaccineScheduleDate, Time vaccineScheduleTime, Date arrivalDate, Time arrivalTime, Date administrationDate, Time administrationTime, Date leavingDate, Time leavingTime) {

        try {
            checkVaccinationCenter(vaccinationCenter);
            checkVaccine(vaccine);
            checkSNSNumber(snsNumber);
            checkDoseNumber(doseNumber);
            checkLotNumber(lotNumber);
            checkUserName(userName);
            checkVaccineScheduleDate(vaccineScheduleDate);
            checkVaccineScheduleTime(vaccineScheduleTime);
            checkArrivalDate(arrivalDate);
            checkArrivalTime(arrivalTime);
            checkLeavingDate(leavingDate);
            checkLeavingTime(leavingTime);
        } catch (MissingVaccinationCenter | BlankInputException | MissingVaccineException | MissingCorrectFormatToLotNumber | ParseException | NullDateException | NumberFormatError | NullTimeException e) {
            e.printStackTrace();
        }

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

    /**
     * Method that return a vaccination center where was administered the vaccine
     * @return VaccinationCenter
     */

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    /**
     * Method that sets the vaccination center where was administered the vaccine
     * @param vaccinationCenter VaccinationCenter where the user was vaccinated
     */

    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }

    /**
     * Method that return the date when was scheduled the vaccine administration
     * @return Date
     */

    public Date getVaccineScheduleDate() {
        return vaccineScheduleDate;
    }

    /**
     * Method that sets the date when was scheduled the vaccine administration
     * @param vaccineScheduleDate Date when the user scheduled the vaccine administration in the center
     */

    public void setVaccineScheduleDate(Date vaccineScheduleDate) {
        this.vaccineScheduleDate = vaccineScheduleDate;
    }

    /**
     * Method that return the time when was scheduled the vaccine administration
     * @return Time
     */

    public Time getVaccineScheduleTime() {
        return vaccineScheduleTime;
    }

    /**
     * Method that set the time when was scheduled the vaccine administration
     * @param vaccineScheduleTime Time when the user scheduled the vaccine administration in the center
     */

    public void setVaccineScheduleTime(Time vaccineScheduleTime) {
        this.vaccineScheduleTime = vaccineScheduleTime;
    }

    /**
     * Method that return the date when the user arrived in the center
     * @return Date
     */

    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Method that sets the date when the user arrived in the center
     * @param arrivalDate Date when the user arrived in the center
     */

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * Method that return the time when the user arrived in the center
     * @return Time
     */

    public Time getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Method that sets the time when the user arrived in the center
     * @param arrivalTime Time when the user arrived in the center
     */

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Method that returns the name of the user who have been administered with the vaccine
     * @return String
     */

    public String getUserName() {
        return userName;
    }

    /**
     * Method that sets the name of the user who have been administered with the vaccine
     * @param userName String of user's name
     */

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Method that returns the SNS number of the user who have been administered with the vaccine
     * @return int
     */

    public int getSnsNumber() {
        return snsNumber;
    }

    /**
     * Method that sets the SNS number of the user who have been administered with the vaccine
     * @param snsNumber int with 9 digits that represent the user's SNS number
     */

    public void setSnsNumber(int snsNumber) {
        this.snsNumber = snsNumber;
    }

    /**
     * Method that returns the vaccine that was administered
     * @return Vaccine
     */

    public Vaccine getVaccine() {
        return vaccine;
    }

    /**
     * Method that sets the vaccine that was administered on the user
     * @param vaccine Vaccine that was administered on the user
     */

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    /**
     * Method that return the dose that was administered on the user
     * @return int
     */

    public int getDoseNumber() {
        return doseNumber;
    }

    /**
     * Method that sets the dose that was administered on the user
     * @param doseNumber int of dose number that was administered on the user (cannot be smaller than 1)
     */

    public void setDoseNumber(int doseNumber) {
        this.doseNumber = doseNumber;
    }

    /**
     * Method that return the date when was administered the vaccine on the user
     * @return Date
     */

    public Date getAdministrationDate() {
        return administrationDate;
    }

    /**
     * Method that sets the date when was administered the vaccine on the user
     * @param administrationDate Date when was administered the vaccine on the user
     */

    public void setAdministrationDate(Date administrationDate) {
        this.administrationDate = administrationDate;
    }

    /**
     * Method that return the time when was administered the vaccine on the user
     * @return Time
     */

    public Time getAdministrationTime() {
        return administrationTime;
    }

    /**
     * Method that sets the time when was administered the vaccine on the user
     * @param administrationTime Time when was administered the vaccine on the user
     */

    public void setAdministrationTime(Time administrationTime) {
        this.administrationTime = administrationTime;
    }

    /**
     * Method that returns the date when the user leaves the vaccination center, after the recovery period
     * @return Date
     */

    public Date getLeavingDate() {
        return leavingDate;
    }

    /**
     * Method that sets the date when the user leaves the vaccination center, after the recovery period
     * @param leavingDate Date when the user leaves the vaccination center
     */

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    /**
     * Method that return the time when the user leaves the vaccination center, after the recovery period
     * @return Time
     */

    public Time getLeavingTime() {
        return leavingTime;
    }

    /**
     * Method that sets the time when the user leaves the vaccination center, after the recovery period
     * @param leavingTime Time when the user leaves the vaccination center
     */

    public void setLeavingTime(Time leavingTime) {
        this.leavingTime = leavingTime;
    }

    /**
     * Method that return the lot number that was used to administer the vaccine
     * @return String
     */

    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * Method that sets the lot number that was used to administer the vaccine
     * @param lotNumber String of the lot number that was used to administer the vaccine
     */

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    /**
     * Method that check all the acceptance criteria of a Vaccination Center
     * @param vaccinationCenter VaccinationCenter
     * @throws MissingVaccinationCenter if the VaccineAdministration Object is created without a Vaccination Center
     */

    private void checkVaccinationCenter (VaccinationCenter vaccinationCenter) throws MissingVaccinationCenter {

        if(vaccinationCenter==null){
            throw new MissingVaccinationCenter();
        }

    }

    /**
     * Method that check all the acceptance criteria of a Vaccine
     * @param vaccine Vaccine
     * @throws MissingVaccineException if the VaccineAdministration Object is created without a Vaccine
     */

    private void checkVaccine (Vaccine vaccine) throws MissingVaccineException {

        if(vaccine==null){
            throw new MissingVaccineException("A vaccine must be chosen");
        }

    }

    /**
     * Method that check all the acceptance criteria of an SNS number
     * @param snsNumber int
     * @throws NumberFormatError if the VaccineAdministration Object is created with an SNS number without 9 digits
     */

    private void checkSNSNumber (int snsNumber) throws NumberFormatError {

        if (snsNumber <= 99999999 || snsNumber >= 1000000000) {
            throw new NumberFormatError("SNS Number must have 9 digits");
        }

    }

    /**
     * Method that check all the acceptance criteria of a Dose Number
     * @param doseNumber int
     * @throws NumberFormatError if the VaccineAdministration Object is created with a dose number with a value smaller than 1
     */

    private void checkDoseNumber (int doseNumber) throws NumberFormatError {

        if (doseNumber<1){
            throw new NumberFormatError("Dose number must be greater than 0");
        }

    }

    /**
     * Method that check all the acceptance criteria of a Lot Number
     * @param lotNumber String
     * @throws MissingCorrectFormatToLotNumber if the VaccineAdministration Object is created with an incorrect format for a lot number.
     * @throws BlankInputException if the VaccineAdministration Object is created with a null or blank String value for a lot number.
     */

    private void checkLotNumber (String lotNumber) throws MissingCorrectFormatToLotNumber, ParseException, BlankInputException {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse("10-10-2022");
        String email = "isep@mail.com";

        User user = new User (1000000000,"test","Male",date,"rua",800000000,email,955771777,55555555);
        user.verifyLotNumber(lotNumber);

        if (lotNumber.isBlank()){
            throw new BlankInputException();
        }

    }

    /**
     * Method that check all the acceptance criteria of a User's Name
     * @param userName String
     * @throws BlankInputException if the VaccineAdministration Object is created with a blank or null String value.
     */

    private void checkUserName (String userName) throws BlankInputException {

        if (userName.isBlank()){
            throw new BlankInputException();
        }

    }

    /**
     * Method that check all the acceptance criteria of a Vaccine Schedule Date
     * @param vaccineScheduleDate Date
     * @throws NullDateException if the VaccineAdministration Object is created with a null Date value.
     */

    private void checkVaccineScheduleDate (Date vaccineScheduleDate) throws NullDateException {
        if (vaccineScheduleDate==null){
            throw new NullDateException();
        }
    }

    /**
     * Method that check all the acceptance criteria of a Vaccine Schedule Time
     * @param vaccineScheduleTime Time
     * @throws NullTimeException if the VaccineAdministration Object is created with a null Time value.
     */

    private void checkVaccineScheduleTime (Time vaccineScheduleTime) throws NullTimeException {
        if (vaccineScheduleTime==null){
            throw new NullTimeException();
        }
    }

    /**
     * Method that check all the acceptance criteria of an Arrival Date
     * @param arrivalDate Date
     * @throws NullDateException if the VaccineAdministration Object is created with a null Date value.
     */

    private void checkArrivalDate (Date arrivalDate) throws NullDateException {
        if (arrivalDate==null){
            throw new NullDateException();
        }
    }

    /**
     * Method that check all the acceptance criteria of an Arrival Time
     * @param arrivalTime Time
     * @throws NullTimeException if the VaccineAdministration Object is created with a null Time value.
     */

    private void checkArrivalTime (Time arrivalTime) throws NullTimeException {
        if (arrivalTime==null){
            throw new NullTimeException();
        }
    }

    /**
     * Method that check all the acceptance criteria of an Administration Date
     * @param administrationDate Date
     * @throws NullDateException if the VaccineAdministration Object is created with a null Date value.
     */

    private void checkAdministrationDate (Date administrationDate) throws NullDateException {
        if (administrationDate==null){
            throw new NullDateException();
        }
    }

    /**
     * Method that check all the acceptance criteria of an Administration Time
     * @param administrationTime Time
     * @throws NullTimeException if the VaccineAdministration Object is created with a null Time value.
     */

    private void checkAdministrationTime (Time administrationTime) throws NullTimeException {
        if (administrationTime==null){
            throw new NullTimeException();
        }
    }

    /**
     * Method that check all the acceptance criteria of a Leaving Date
     * @param leavingDate Date
     * @throws NullDateException if the VaccineAdministration Object is created with a null Date value.
     */

    private void checkLeavingDate (Date leavingDate) throws NullDateException {
        if (leavingDate==null){
            throw new NullDateException();
        }
    }

    /**
     * Method that check all the acceptance criteria of a Leaving Time
     * @param leavingTime Time
     * @throws NullTimeException if the VaccineAdministration Object is created with a null Time value.
     */

    private void checkLeavingTime (Time leavingTime) throws NullTimeException {
        if (leavingTime==null){
            throw new NullTimeException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccineAdministration that = (VaccineAdministration) o;
        return doseNumber == that.doseNumber && vaccine.getBrand().equals(that.getVaccine().getBrand());
    }

}
