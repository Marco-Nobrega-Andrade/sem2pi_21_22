package app.controller;

import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenterStore;

public class RegisterVaccinationCenterController {
    private App app;
    private VaccinationCenterStore store;
    private VaccinationCenter vc;

    public RegisterVaccinationCenterController() {
        this.app = App.getInstance();
    }


    /**
     * Method that creates the Vaccination Centre object.
     * Gets the "Vaccination Centre" first and then uses it to create the object
     * @param name
     * @param address
     * @param phoneNumber
     * @param email
     * @param faxNumber
     * @param websiteAddress
     * @param openingHours
     * @param closingHours
     * @param slotDuration
     * @param maximumNumVaccinesSlot
     */

    public void registerVaccinationCenter(String name, String address, String phoneNumber, String email, String faxNumber, String websiteAddress, String openingHours, String closingHours, String slotDuration, String maximumNumVaccinesSlot, boolean isHealthCareCenter) {
        store = app.getCompany().getVaccinationCenterStore();
        vc = store.registerVaccinationCenter(name, address, phoneNumber, email, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maximumNumVaccinesSlot, isHealthCareCenter);
    }

    public void saveVaccinationCenter() {
        store.saveVaccinationCenter(vc);
    }

}
