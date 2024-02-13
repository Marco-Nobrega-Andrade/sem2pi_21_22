package app.domain.model.VaccinationCenter;

import app.domain.model.Time;
import pt.isep.lei.esoft.auth.domain.model.Email;

public class VaccinationCenterDTO {
    private String name;
    private String address;
    private String phoneNumber;
    private Email email;
    private String faxNumber;
    private String websiteAddress;
    private Time openingHours;
    private Time closingHours;
    private String slotDuration;
    private String maximumNumVaccinesSlot;
    private boolean isHealthcareCenter;

    public VaccinationCenterDTO(String name, String address, String phoneNumber, Email email, String faxNumber, String websiteAddress, Time openingHours, Time closingHours, String slotDuration, String maximumNumVaccinesSlot, boolean isHealthcareCenter) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.faxNumber = faxNumber;
        this.websiteAddress = websiteAddress;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.slotDuration = slotDuration;
        this.maximumNumVaccinesSlot = maximumNumVaccinesSlot;
        this.isHealthcareCenter = isHealthcareCenter;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", this.name, this.address, this.openingHours.toString(), this.closingHours.toString());
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
