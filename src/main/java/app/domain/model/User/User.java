
package app.domain.model.User;

import app.domain.model.Exceptions.*;
import app.domain.model.Time;
import app.domain.model.Vaccine.VaccineDTO;
import app.domain.model.VaccineAdministration.VaccineAdministration;
import app.domain.model.VaccineAdministration.VaccineAdministrationStore;
import app.domain.model.VaccineAdministration.VaccineAdministrationDTO;
import app.domain.model.VaccineType.VaccineTypeDTO;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {

    private VaccineAdministrationStore vaccineAdministrationStore = new VaccineAdministrationStore();


    private int id;
    private String name;
    private String sex;
    private Date birthDate;
    private String address;
    private int phoneNumber;
    private String eMail;
    private int snsNumber;
    private int ccNumber;

    public User(int id,String name, String sex, Date birthDate, String address,
                int phoneNumber, String eMail, int snsNumber,
                int ccNumber) {
        checkName(name);
        checkAddress(address);
        checkPhoneNumber(phoneNumber);
        checkCC(ccNumber);
        checkEmail(new Email(eMail));
        checkSex(sex);


        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.eMail = String.valueOf(eMail);
        this.snsNumber = snsNumber;
        this.ccNumber = ccNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getSnsNumber() {
        return snsNumber;
    }

    public void setSnsNumber(int snsNumber) {
        this.snsNumber = snsNumber;
    }

    public int getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(int ccNumber) {
        this.ccNumber = ccNumber;
    }

    public VaccineAdministrationStore getVaccineAdministrationStore() {
        return vaccineAdministrationStore;
    }

    public int getAge(Date birthDate){

        int years;
        int months;

        //create calendar object for birth day
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTimeInMillis(birthDate.getTime());

        //create calendar object for current day
        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(currentTime);

        //Get difference between years
        years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);

        //Get difference between months
        months = (now.get(Calendar.MONTH) + 1) - (birthDay.get(Calendar.MONTH) + 1);

        //if month difference is in negative then reduce years by one
        //and calculate the number of months.
        if (months < 0)
        {
            years--;
        } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
        {
            years--;
        }
        return years;
    }

    public VaccineAdministration createNewVaccineAdministration(VaccineAdministrationDTO vaccineAdministrationDTO){
        return vaccineAdministrationStore.createNewVaccineAdministration(vaccineAdministrationDTO);
    }

    public void saveNewVaccineAdministration (VaccineAdministration vaccineAdministration){
        vaccineAdministrationStore.saveNewVaccineAdministration(vaccineAdministration);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", eMail='" + eMail + '\'' +
                ", snsNumber=" + snsNumber +
                ", ccNumber=" + ccNumber +
                '}';
    }

    /**
     * Checks all acceptance criteria for the Employee parameter "name"
     * @param name String "name" inputted by the user
     */
    private void checkName(String name) {
        if (name.isBlank()){
            throw new IllegalArgumentException("Name cannot be blank.");
        }
    }

    /**
     * Checks all acceptance criteria for the Employee parameter "address"
     * @param address String "address" inputted by the user
     */
    private void checkAddress(String address) {
        if (address.isBlank()){
            throw new IllegalArgumentException("Address cannot be blank.");
        }
    }

    /**
     * Checks all acceptance criteria for the Employee parameter "phoneNumber"
     * @param phoneNumber int "phoneNumber" inputted by the user
     */
    private void checkPhoneNumber(int phoneNumber){
        if((Integer.toString(phoneNumber).length() != 9)){
            throw new IllegalArgumentException("The phone number has to have 9 digits.");
        }
    }

    /**
     * Checks all acceptance criteria for the Employee parameter "citizenCardNumber"
     * @param citizenCardNumber int "citizenCardNumber " inputted by the user
     */
    private void checkCC(int citizenCardNumber){
        if((Integer.toString(citizenCardNumber).length() != 8)){
            throw new IllegalArgumentException("The Citizen Card Number has to have 8 digits.");
        }
    }

    /**
     * Checks all acceptance criteria for the User parameter "Sex"
     * @param sex String "sex " inputted by the user
     */
    private void checkSex(String sex){
        if(sex.compareTo("Male") != 0 && sex.compareTo("Female") != 0 && sex.compareTo("Masculino") != 0 && sex.compareTo("Feminino") != 0 &&sex.compareTo("") != 0){
            throw new IllegalArgumentException("The sex is incorrect.");
        }
    }

    /**
     * Checks all acceptance criteria for the User parameter "e-mail"
     * @param eMail String "eMail " inputted by the user
     */
    private void checkEmail(Email eMail){
        if((eMail.getEmail().isBlank())){
            throw new IllegalArgumentException("The email cannot be blank.");
        }
    }


    public VaccineDTO getVaccine(VaccineTypeDTO vaccineTypeDTO) throws NotVaccineForThatVaccineTypeException {
        return vaccineAdministrationStore.getVaccineByType(vaccineTypeDTO);
    }

    public int getNextDose(VaccineTypeDTO vaccineTypeDTO) throws NotPreviousDoseForThatVaccineException {
        return vaccineAdministrationStore.getNextDose(vaccineTypeDTO);
    }

    public double getNextDosage(int age, int dose , VaccineTypeDTO vaccineType, VaccineDTO vaccineDTO) throws NotDosageWasFoundedForThatVaccineException {
        return vaccineAdministrationStore.getNextDosage(age, dose ,vaccineType, vaccineDTO);
    }

    public boolean verifyIfExistVaccinationRecord(VaccineTypeDTO vaccineTypeDTO){
        return vaccineAdministrationStore.verifyIfExistVaccinationRecord(vaccineTypeDTO);
    }



    public boolean verifyLotNumber (String lotNumber) throws MissingCorrectFormatToLotNumber {
        return vaccineAdministrationStore.verifyLotNumber(lotNumber);
    }

    /**
     * Checks if the user arrived at the center in the right time interval
     * @param date the date of the desired performance analysis
     * @param time starting time of the time interval
     * @param timeIntervals is the length of the sections of time divided by the entire working day of the vaccination center
     * @param vaccinationCenterName name of the vaccination center that is being checked
     * @return returns a boolean, is positive if the user arrived that center at that time interval and date
     */

    public boolean checkIfUserArrivedAtCenter(Date date, Time time, int timeIntervals, String vaccinationCenterName) {
        return vaccineAdministrationStore.checkIfUserArrivedAtCenter(date, time, timeIntervals, vaccinationCenterName);
    }
    /**
     * Checks if the user left the center in the right time interval
     * @param date the date of the desired performance analysis
     * @param time starting time of the time interval
     * @param timeIntervals is the length of the sections of time divided by the entire working day of the vaccination center
     * @param vaccinationCenterName name of the vaccination center that is being checked
     * @return returns a boolean, is positive if the user left the center at that time interval and date
     */

    public boolean checkIfUserLeftTheCenter(Date date, Time time, int timeIntervals, String vaccinationCenterName) {
        return vaccineAdministrationStore.checkIfUserLeftTheCenter(date, time, timeIntervals, vaccinationCenterName);
    }

    public void checkForDuplicateAdministration(VaccineAdministration vaccineAdministration) throws DuplicateVaccineAdministrationException, DuplicateVaccineAdministrationException {
        vaccineAdministrationStore.checkForDuplicateAdministration(vaccineAdministration);
    }

}
