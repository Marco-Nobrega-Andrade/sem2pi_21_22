package app.domain.model.VaccinationCenter;

import app.domain.model.AnalyzePerformance.Performance;
import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.Time;
import app.domain.model.User.User;
import app.domain.model.VaccineSchedule.VaccineScheduleStore;
import app.domain.model.WaitingUser.WaitingUserStore;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class VaccinationCenter implements Serializable {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String faxNumber;
    private String websiteAddress;
    private Time openingHours;
    private Time closingHours;
    private String slotDuration;
    private String maximumNumVaccinesSlot;
    private boolean isHealthcareCenter;
    private VaccineScheduleStore vaccineScheduleStore= new VaccineScheduleStore();
    private WaitingUserStore waitingUserStore = new WaitingUserStore();
    private Performance performance = new Performance();


    public VaccinationCenter(String name, String address, String phoneNumber, String email, String faxNumber, String websiteAddress, String openingHours, String closingHours, String slotDuration, String maximumNumVaccinesSlot, boolean isHealthcareCenter) {
        checkName(name);
        checkAddress(address);
        checkPhoneNumber(phoneNumber);
        checkFaxNumber(faxNumber);
        checkWebsiteAddress(websiteAddress);
        checkOpeningHours(openingHours);
        checkClosingHours(closingHours);
        checkSlotDuration(slotDuration);
        checkMaxNumVaccinesSlot(maximumNumVaccinesSlot);
        Email email1 = new Email(email);

        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = String.valueOf(email1);
        this.faxNumber = faxNumber;
        this.websiteAddress = websiteAddress;
        this.openingHours = new Time(openingHours);
        this.closingHours = new Time(closingHours);
        this.slotDuration = slotDuration;
        this.maximumNumVaccinesSlot = maximumNumVaccinesSlot;
        this.isHealthcareCenter = isHealthcareCenter;

        this.vaccineScheduleStore.setOpeningHours(this.openingHours);
        this.vaccineScheduleStore.setClosingHours(this.closingHours);
        this.vaccineScheduleStore.setSlotDuration(Integer.parseInt(slotDuration));
        this.vaccineScheduleStore.setMaximumNumVaccinesSlot(Integer.parseInt(maximumNumVaccinesSlot));
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public Time getOpeningHours() {
        return openingHours;
    }

    public Time getClosingHours() {
        return closingHours;
    }

    public String getSlotDuration() {
        return slotDuration;
    }

    public String getMaximumNumVaccinesSlot() {
        return maximumNumVaccinesSlot;
    }

    public boolean isHealthcareCenter() {
        return isHealthcareCenter;
    }

    public VaccineScheduleStore getVaccineScheduleStore() {
        return vaccineScheduleStore;
    }

    public WaitingUserStore getWaitingUserStore() {
        return waitingUserStore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = new Time(openingHours);
    }

    public void setClosingHours(String closingHours) {
        this.closingHours = new Time(closingHours);
    }

    public void setSlotDuration(String slotDuration) {
        this.slotDuration = slotDuration;
    }

    public void setMaximumNumVaccinesSlot(String maximumNumVaccinesSlot) {
        this.maximumNumVaccinesSlot = maximumNumVaccinesSlot;
    }

    public void setHealthcareCenter(boolean healthcareCenter) {
        isHealthcareCenter = healthcareCenter;
    }

    @Override
    public String toString() {
        return "VaccinationCenter{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", faxNumber=" + faxNumber +
                ", websiteAddress='" + websiteAddress + '\'' +
                ", openingHours='" + openingHours + '\'' +
                ", closingHours='" + closingHours + '\'' +
                ", slotDuration='" + slotDuration + '\'' +
                ", maximumNumVaccinesSlot=" + maximumNumVaccinesSlot +
                ", isHealthCareCenter=" + isHealthcareCenter +"}";
    }

    /**
     * Checks all acceptance criteria for the Vaccination Centre parameter "name"
     * @param name String "name" inputted by the user
     */

    private void checkName(String name) {
        if (name.isBlank()){
            throw new IllegalArgumentException("The name cannot be blank.");
        }
    }

    /**
     * Checks all acceptance criteria for the Vaccination Centre parameter "address"
     * @param address String "address" inputted by the user
     */

    private void checkAddress(String address) {
        if (address.isBlank()){
            throw new IllegalArgumentException("The address cannot be blank.");
        }
    }

    /**
     * Checks all acceptance criteria for the Vaccination Centre parameter "phoneNumber"
     * @param phoneNumber String "phoneNumber" inputted by the user
     */

    private void checkPhoneNumber(String phoneNumber){
        if(phoneNumber.isBlank()) {
            throw new IllegalArgumentException("The phone number cannot be blank.");
        }
        if(phoneNumber.length() != 9){
            throw new IllegalArgumentException("The phone number has to have 9 digits.");
        }
    }

    /**
     * Checks all acceptance criteria for the Vaccination Centre parameter "faxNumber"
     * @param faxNumber String "faxNumber" inputted by the user
     */

    private void checkFaxNumber(String faxNumber){
        if (faxNumber.isBlank()){
            throw new IllegalArgumentException("The Fax Number cannot be blank.");
        }
        if((faxNumber.length() != 9)){
            throw new IllegalArgumentException("The fax number has to have 9 digits.");
        }
    }


    /**
     * Checks all acceptance criteria for the Vaccination Centre parameter "websiteAddress"
     * @param websiteAddress String "websiteAddress" inputted by the user
     */

    private void checkWebsiteAddress(String websiteAddress){
        String[] ArrayWebsite = websiteAddress.split("\\.");
        if(ArrayWebsite.length < 1){
            throw new IllegalArgumentException("The website is incorrect.");
        }
        if (websiteAddress.isBlank()){
            throw new IllegalArgumentException("The website address cannot be blank.");
        }
    }


    /**
     * Checks all acceptance criteria for the Vaccination Centre parameter "openingHours"
     * @param openingHours String "openingHours" inputted by the user
     */


    private void checkOpeningHours(String openingHours){
        String [] ArrayOpeningAndClosingHours = (openingHours.split(":"));
        if (openingHours.isBlank()){
            throw new IllegalArgumentException("The opening hours cannot be blank.");
        }

        if(Integer.parseInt(ArrayOpeningAndClosingHours[0]) >=24 || Integer.parseInt(ArrayOpeningAndClosingHours[0]) < 0 || Integer.parseInt(ArrayOpeningAndClosingHours[1]) > 59 || Integer.parseInt(ArrayOpeningAndClosingHours[1]) < 0 ) {
            throw new IllegalArgumentException("The opening hours should be between 00:00 and 23:59");
        }
    }

    /**
     * Checks all acceptance criteria for the Vaccination Centre parameter "closingHours"
     * @param closingHours String "closingHours" inputted by the user
     */

    private void checkClosingHours(String closingHours){
        String [] ArrayOpeningAndClosingHours = (closingHours.split(":"));

        if (closingHours.isBlank()){
            throw new IllegalArgumentException("The closing hours cannot be blank.");
        }

        if(Integer.parseInt(ArrayOpeningAndClosingHours[0]) >=24 || Integer.parseInt(ArrayOpeningAndClosingHours[0]) < 0 || Integer.parseInt(ArrayOpeningAndClosingHours[1]) > 59 || Integer.parseInt(ArrayOpeningAndClosingHours[1]) < 0 ) {
            throw new IllegalArgumentException("The closing hours should be between 00:00 and 23:59");
        }
    }


    /**
     * Checks all acceptance criteria for the Vaccination Centre parameter "slotDuration"
     * @param slotDuration String "slotDuration" inputted by the user
     */

    private void checkSlotDuration(String slotDuration){
        if (slotDuration.isBlank()){
            throw new IllegalArgumentException("The slot duration cannot be blank.");
        }

        if(Integer.parseInt(slotDuration) >59 || Integer.parseInt(slotDuration) <= 0){
            throw new IllegalArgumentException("The slot duration should be between 1-59 min");
        }
    }

    /**
     * Checks all acceptance criteria for the Vaccination Centre parameter "maximumNumVaccinesSlot"
     * @param maximumNumVaccinesSlot String "maxNumVaccinesSlot" inputted by the user
     */


    private void checkMaxNumVaccinesSlot(String maximumNumVaccinesSlot){
        if (maximumNumVaccinesSlot.isBlank()){
            throw new IllegalArgumentException("The maximum number of vaccines per slot cannot be blank.");
        }
        if(maximumNumVaccinesSlot.length() < 1){
            throw new IllegalArgumentException("The maximum number of vaccines per slot can't be below 1");
        }
    }


    /**
     * Analyzes the performance of a vaccination center and saves its analysis.
     * @param date the date of the desired performance analysis
     * @param timeIntervals is the length of the sections of time divided by the entire working day of the vaccination center
     * @param algorithmUsed name of the algorithm used to analyze the performance of a center
     * @param usersList list with all the users in the system
     */
    public void analyzePerformance(Date date, int timeIntervals, String algorithmUsed, ArrayList<User> usersList) throws ListIsEmptyException {
        performance.analyzePerformance(date, timeIntervals, algorithmUsed, usersList, name);
    }

    /**
     * Gets all performance analysis info
     * @return String containing all information
     */

    public String getPerformanceInfo(){
        return performance.getPerformanceInfo();
    }

    public boolean equalsVaccinationCenters(VaccinationCenter vc){
        return this.email.equals(vc.getEmail());
    }
}