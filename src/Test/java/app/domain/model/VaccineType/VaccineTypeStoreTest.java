package app.domain.model.VaccineType;

import app.domain.model.VaccineType.VaccineType;
import app.domain.model.VaccineType.VaccineTypeStore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTypeStoreTest {

    @Test
    void ensureCodeIsCorrectLength() {
        try{
            VaccineTypeStore store = new VaccineTypeStore();
            store.createVaccineType("12","description","mrna");
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    void ensureCodeIsNotBlank() {
        try{
            VaccineTypeStore store = new VaccineTypeStore();
            store.createVaccineType("     ","description","mrna");
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    void ensureCodeIsComposedOnlyByAlphanumericChars() {
        try{
            VaccineTypeStore store = new VaccineTypeStore();
            store.createVaccineType("@@@@@","description","mrna");
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    void ensureDescriptionIsNotBlank() {
        try{
            VaccineTypeStore store = new VaccineTypeStore();
            store.createVaccineType("12345","     ","mrna");
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    void ensureTechIsNotBlank() {
        try{
            VaccineTypeStore store = new VaccineTypeStore();
            store.createVaccineType("12345","description","    ");
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }
    @Test
    void ensureTechIsOneOfTheOptions() {
        try{
            VaccineTypeStore store = new VaccineTypeStore();
            store.createVaccineType("12345","description","tech");
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }
    @Test
    void ensureCanNotSaveDuplicateVaccineType() {
        try{
            VaccineTypeStore store = new VaccineTypeStore();
            store.saveVaccineType(store.createVaccineType("12345","description","mrna"));
            store.saveVaccineType(store.createVaccineType("12345","description","mrna"));
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    void checkIfOngoingOutbreakIsTheChosenVaccineType() {
        VaccineTypeStore store = new VaccineTypeStore();

        VaccineType vaccineType = new VaccineType("12345", "covid", "mrna");
        store.saveVaccineType(vaccineType);

        assertFalse(store.checkIfOngoingOutbreakIsTheChosenVaccineType(vaccineType));

        store.setVaccineTypeOngoingOutbreak(0);
        assertTrue(store.checkIfOngoingOutbreakIsTheChosenVaccineType(vaccineType));

        VaccineType nonOutbreakVaccineType = new VaccineType("54321", "gripe", "mrna");
        assertFalse(store.checkIfOngoingOutbreakIsTheChosenVaccineType(nonOutbreakVaccineType));
    }


}