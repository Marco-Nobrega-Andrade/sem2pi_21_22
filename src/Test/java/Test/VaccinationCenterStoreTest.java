package Test;

import app.domain.model.Employee.Employee;
import app.domain.model.Employee.EmployeeStore;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenterStore;
import app.domain.model.VaccineType.VaccineTypeStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VaccinationCenterStoreTest {

    @Test
    public void ensureAllBlankNotAllowed() {
        try {
            VaccinationCenterStore store = new VaccinationCenterStore();
            store.registerVaccinationCenter("", "", "", "", "", "", "", "", "", "", false);

        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    public void ensureCanNotSaveDuplicateVaccinationCentres() {
        try{
            VaccinationCenterStore store = new VaccinationCenterStore();
            store.saveVaccinationCenter(store.registerVaccinationCenter("nome","address","123456789", "alguem@isep.pt", "1234567", "website.com", "20:00", "21:00", "20", "20", true));
            store.saveVaccinationCenter(store.registerVaccinationCenter("nome","address","123456789", "alguem@isep.pt", "1234567", "website.com", "20:00", "21:00", "20", "20", true));
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    public void ensureEmailisIncorretNotAllowed() {
        try {
            VaccinationCenterStore store = new VaccinationCenterStore();
            store.registerVaccinationCenter("name", "address", "123456789", "alguem@gmailcom", "1234567", "alguem.com", "18:00", "20:00", "20", "20", true);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    public void ensureWebsiteAddressIncorrectNotAllowed(){
        try {
            VaccinationCenterStore store = new VaccinationCenterStore();
            store.registerVaccinationCenter("name", "address", "123456789", "alguem@gmail.com", "1234567", "alguemcom", "18:00", "20:00", "20", "20", true);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    public void ensurePhoneNumberIncorrectNotAllowed(){
        try {
            VaccinationCenterStore store = new VaccinationCenterStore();
            store.registerVaccinationCenter("name", "address", "123", "alguem@gmail.com", "1234567", "alguem.com", "18:00", "20:00", "20", "20", true);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    public void ensureFaxNumberIncorrectNotAllowed(){
        try {
            VaccinationCenterStore store = new VaccinationCenterStore();
            store.registerVaccinationCenter("name", "address", "123456789", "alguem@gmail.com", "123", "alguem.com", "18:00", "20:00", "20", "20", true);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    public void ensureOpeningHoursAndClosingHoursIncorrectNotAllowed(){
        try {
            VaccinationCenterStore store = new VaccinationCenterStore();
            store.registerVaccinationCenter("name", "address", "123456789", "alguem@gmail.com", "123", "alguem.com", "25:00", "2000", "20", "20", true);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    public void ensureSlotDurationIncorrectNotAllowed(){
        try {
            VaccinationCenterStore store = new VaccinationCenterStore();
            store.registerVaccinationCenter("name", "address", "123456789", "alguem@gmail.com", "123", "alguem.com", "18:00", "20:00", "61", "20", true);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    public void ensureMaximumNumVaccinesSlotIncorrectNotAllowed(){
        try {
            VaccinationCenterStore store = new VaccinationCenterStore();
            store.registerVaccinationCenter("name", "address", "123456789", "alguem@gmail.com", "123", "alguem.com", "18:00", "20:00", "20", "-1", true);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }


    @Test
    public void validateIfSomeCharacteristicsAreTheSame(){
        ArrayList<VaccinationCenter> vaccinationCenterList = new ArrayList<>();
        vaccinationCenterList.add(new VaccinationCenter("name", "address", "123456789", "as@gmail.com", "123456789", "algo.com","20:00", "21:00", "20", "20", true));
        VaccinationCenter vc = new VaccinationCenter("a", "a", "987654321", "asa@gmail.com", "123456789", "algos.com","19:00", "18:00", "19", "18", true);
        try {
            for (VaccinationCenter vacCen : vaccinationCenterList) {
                if ((vc.getName().equals(vacCen.getName()))) {
                    throw new IllegalArgumentException("Couldn't save. This name already exists.");
                } else if (vc.getAddress().equals(vacCen.getAddress())) {
                    throw new IllegalArgumentException("Couldn't save. This address already exists.");
                } else if (vc.getPhoneNumber().equals(vacCen.getPhoneNumber())) {
                    throw new IllegalArgumentException("Couldn't save. This phone number already exists.");
                } else if (vc.getEmail().equals(vacCen.getEmail())) {
                    throw new IllegalArgumentException("Couldn't save. This email already exists.");
                } else if (vc.getFaxNumber().equals(vacCen.getFaxNumber())) {
                    throw new IllegalArgumentException("Couldn't save. This Fax Number already exists.");
                } else if (vc.getWebsiteAddress().equals(vacCen.getWebsiteAddress())) {
                    throw new IllegalArgumentException("Couldn't save. This website address already exists.");
                }
            }
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }
}