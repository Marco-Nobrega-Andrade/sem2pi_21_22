package app.domain.model.VaccinationCenter;

import app.domain.model.Time;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;

public class VaccinationCenterMapper {
    public VaccinationCenterMapper() {
    }

    /**
     * Returns a list of VaccinationCenters DTOs
     * @param vaccinationCenterList
     * @return VaccinationCenterDTO
     */

    public ArrayList<VaccinationCenterDTO> toDTO(ArrayList<VaccinationCenter> vaccinationCenterList){
        ArrayList<VaccinationCenterDTO> vaccinationCenterListDTO = new ArrayList<>();
        for (int i = 0; i < vaccinationCenterList.size(); i++) {
            VaccinationCenter vaccinationCenter = vaccinationCenterList.get(i);
            VaccinationCenterDTO vaccinationCenterDTO = toDTO(vaccinationCenter);
            vaccinationCenterListDTO.add(vaccinationCenterDTO);
        }
        return  vaccinationCenterListDTO;
    }

    /**
     * Returns a DTO with all characteristics of a vaccination center
     * @param vaccinationCenter
     * @return VaccinationCenterDTO
     */
    private VaccinationCenterDTO toDTO(VaccinationCenter vaccinationCenter) {
        String name = vaccinationCenter.getName();
        String address = vaccinationCenter.getAddress();
        String phoneNumber = vaccinationCenter.getPhoneNumber();
        Email email = new Email (vaccinationCenter.getEmail());
        String faxNumber = vaccinationCenter.getFaxNumber();
        String websiteAddress = vaccinationCenter.getWebsiteAddress();
        Time openingHours = vaccinationCenter.getOpeningHours();
        Time closingHours = vaccinationCenter.getClosingHours();
        String slotDuration = vaccinationCenter.getSlotDuration();
        String maximumNumVaccinesSlot = vaccinationCenter.getMaximumNumVaccinesSlot();
        boolean isHealthcareCenter = vaccinationCenter.isHealthcareCenter();
        return new VaccinationCenterDTO(name, address, phoneNumber,email,faxNumber,websiteAddress,openingHours,closingHours,slotDuration,maximumNumVaccinesSlot,isHealthcareCenter);
    }
}
