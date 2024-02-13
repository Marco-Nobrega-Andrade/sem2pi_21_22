package app.domain.model.VaccinationCenter;

import app.domain.model.Exceptions.DuplicateScheduleException;
import app.domain.model.VaccineSchedule.VaccineSchedule;
import app.domain.model.VaccineSchedule.VaccineScheduleStore;

import java.io.Serializable;
import java.util.ArrayList;

public class VaccinationCenterStore implements Serializable {
    private ArrayList<VaccinationCenter> vaccinationCenterList = new ArrayList<>();

    public VaccinationCenter registerVaccinationCenter(String name, String address, String phoneNumber, String email, String faxNumber, String websiteAddress, String openingHours, String closingHours, String slotDuration, String maximumNumVaccinesSlot, boolean isHealthCareCenter) {
        return (new VaccinationCenter(name, address, phoneNumber, email, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maximumNumVaccinesSlot, isHealthCareCenter));
    }

    /**
     * Validates the vaccination center and after that adds it ot the vaccination center list
     * @param vaccinationCenter
     */
    public void saveVaccinationCenter(VaccinationCenter vaccinationCenter) {
        validateVaccinationCenter(vaccinationCenter);
        addVaccinationCenter(vaccinationCenter);
    }

    /**
     * Adds the vaccination center to the list of vaccination centers
     * @param vaccinationCenter
     */

    public void addVaccinationCenter(VaccinationCenter vaccinationCenter) {
        vaccinationCenterList.add(vaccinationCenter);
    }

    /**
     * Checks for all acceptance criteria regarding the vaccination center
     * @throws IllegalArgumentException there already exists a name, address, phone number, e-mail, fax number or website address for other vaccination centers
     */

    public void validateVaccinationCenter(VaccinationCenter vaccinationCenter) {
        for (VaccinationCenter vacCen : vaccinationCenterList) {
            if ((vaccinationCenter.getName().equals(vacCen.getName()))) {
                throw new IllegalArgumentException("Couldn't save. This name already exists.");
            } else if (vaccinationCenter.getAddress().equals(vacCen.getAddress())) {
                throw new IllegalArgumentException("Couldn't save. This address already exists.");
            } else if (vaccinationCenter.getPhoneNumber().equals(vacCen.getPhoneNumber())) {
                throw new IllegalArgumentException("Couldn't save. This phone number already exists.");
            } else if (vaccinationCenter.getEmail().equals(vacCen.getEmail())) {
                throw new IllegalArgumentException("Couldn't save. This email already exists.");
            } else if (vaccinationCenter.getFaxNumber().equals(vacCen.getFaxNumber())) {
                throw new IllegalArgumentException("Couldn't save. This Fax Number already exists.");
            } else if (vaccinationCenter.getWebsiteAddress().equals(vacCen.getWebsiteAddress())) {
                throw new IllegalArgumentException("Couldn't save. This website address already exists.");
            }
        }
    }

    /**
     * Creates a list with all Healthcare centers
     * @return healthcareCentersList
     */

    public ArrayList<VaccinationCenter> getHealthcareCentersList() {
        ArrayList<VaccinationCenter> healthcareCentersList = new ArrayList<>();
        for (VaccinationCenter vc : vaccinationCenterList){
            if (vc.isHealthcareCenter()){
                healthcareCentersList.add(vc);
            }
        }
        return healthcareCentersList;
    }

    /**
     * Returns a list with all vaccinations centers
     * @return healthcareCentersList
     */

    public ArrayList<VaccinationCenter> getAllVaccinationCenters(){
        return new ArrayList<>(vaccinationCenterList);
    }


    /**
     * Checks if an actor already scheduled a vaccination for that vaccine type
    * @param newVaccineSchedule
     * @throws DuplicateScheduleException Cannot schedule two vaccine types vaccinations
     */

    public void checkForDuplicateSchedules(VaccineSchedule newVaccineSchedule) throws DuplicateScheduleException {
        for(VaccinationCenter vc: vaccinationCenterList){
            VaccineScheduleStore store = vc.getVaccineScheduleStore();
            for(VaccineSchedule vaccineSchedule: store.getVaccineSchedulesList()){
                if (vaccineSchedule.getSnsNumber() == newVaccineSchedule.getSnsNumber() && vaccineSchedule.getVaccineType().equals(newVaccineSchedule.getVaccineType())){
                    throw new DuplicateScheduleException();
                }
            }
        }
    }

    /**
     * Returns the chosen vaccination center
    @param index is the position of the Vaccination center on the list
     @param flag indicates if the actor chose a vaccine type that is an outbreak
     */

    public VaccinationCenter getVaccinationCenterFromStore(int index, boolean flag) {
        if (!flag){
            return getHealthcareCentersList().get(index);
        }else{
            return vaccinationCenterList.get(index);
        }
    }


    public VaccinationCenter getVaccinationCenterFromStore(String phoneNumber){
        for (VaccinationCenter vc : vaccinationCenterList){
            if (vc.getPhoneNumber().compareTo(phoneNumber) == 0){
                return vc;
            }
        }
        return null;
    }
}
