package Test;

import app.domain.model.Vaccine.*;
import app.domain.model.VaccineType.VaccineType;
import app.domain.model.VaccineType.VaccineTypeDTO;
import app.domain.model.VaccineType.VaccineTypeStore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VaccineStoreTest {


    @Test
    void ensureVaccineTypeIsNotNull() {
        try {
            VaccineStore vaccineStore = new VaccineStore();
            ArrayList<Integer> agegroup = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1));
            ArrayList<Double> dosage = new ArrayList<>(4);
            vaccineStore.createNewVaccine(null,"pfizer",2,agegroup,dosage,2,interval);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    void ensureIntervalIsNotNull() {
        try {
            VaccineStore vaccineStore = new VaccineStore();
            VaccineTypeStore store = new VaccineTypeStore();
            VaccineTypeDTO vaccineType= new VaccineTypeDTO("12345","description","mrna");
            ArrayList<Integer> agegroup = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            ArrayList<Double> dosage = new ArrayList<>(4);
            vaccineStore.createNewVaccine(vaccineType,"pfizer",2,agegroup,dosage,2,null);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    void ensureAgeGroupIsNotNull() {
        try {
            VaccineStore vaccineStore = new VaccineStore();
            VaccineTypeStore store = new VaccineTypeStore();
            VaccineTypeDTO vaccineType= new VaccineTypeDTO("12345","description","mrna");
            ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1));
            ArrayList<Double> dosage = new ArrayList<>(4);
            vaccineStore.createNewVaccine(vaccineType,"pfizer",2,null,dosage,2,interval);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    void ensureDosageIsNotNull() {
        try {
            VaccineStore vaccineStore = new VaccineStore();
            VaccineTypeStore store = new VaccineTypeStore();
            VaccineTypeDTO vaccineType= new VaccineTypeDTO("12345","description","mrna");
            ArrayList<Integer> agegroup = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1));
            vaccineStore.createNewVaccine(vaccineType,"pfizer",2,agegroup,null,2,interval);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    void ensureBrandIsNotBlank(){
        try {
            VaccineStore vaccineStore = new VaccineStore();
            VaccineTypeStore store = new VaccineTypeStore();
            VaccineTypeDTO vaccineType= new VaccineTypeDTO("12345","description","mrna");
            ArrayList<Integer> agegroup = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1));
            ArrayList<Double> dosage = new ArrayList<>(4);
            vaccineStore.createNewVaccine(vaccineType,"     ",2,agegroup,dosage,2,interval);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    void ensureNumberOfDifferentAgeGroupsIsNotSmallerThanOne() {
        try {
            VaccineStore vaccineStore = new VaccineStore();
            VaccineTypeStore store = new VaccineTypeStore();
            VaccineTypeDTO vaccineType= new VaccineTypeDTO("12345","description","mrna");
            ArrayList<Integer> agegroup = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1));
            ArrayList<Double> dosage = new ArrayList<>(2);
            vaccineStore.createNewVaccine(vaccineType,"pfizer",0,agegroup,dosage,2,interval);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    void ensureNumberOfDosesIsNotSmallerThanOne() {
        try {
            VaccineStore vaccineStore = new VaccineStore();
            VaccineTypeStore store = new VaccineTypeStore();
            VaccineTypeDTO vaccineType=  new VaccineTypeDTO("12345","description","mrna");
            ArrayList<Integer> agegroup = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1));
            ArrayList<Double> dosage = new ArrayList<>(2);
            vaccineStore.createNewVaccine(vaccineType,"pfizer",2,agegroup,dosage,0,interval);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    void ensureDosageIsNotSmallerThanOne() {
        try {
        VaccineStore vaccineStore = new VaccineStore();
        VaccineTypeStore store = new VaccineTypeStore();
        VaccineTypeDTO vaccineType=  new VaccineTypeDTO("12345","description","mrna");
        ArrayList<Integer> agegroup = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1));
        ArrayList<Double> dosage = new ArrayList<>(4);
        vaccineStore.createNewVaccine(vaccineType,"pfizer",2,agegroup,dosage,2,interval);
    }
        catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    void ensureDosageIsCorrectlyDefined() {
        try {
            VaccineStore vaccineStore = new VaccineStore();
            VaccineTypeStore store = new VaccineTypeStore();
            VaccineTypeDTO vaccineType = new VaccineTypeDTO("12345","description","mrna");
            ArrayList<Integer> agegroup = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1));
            ArrayList<Double> dosage = new ArrayList<>(3);
            vaccineStore.createNewVaccine(vaccineType,"pfizer",2,agegroup,dosage,2,interval);
        }
        catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    void ensureAgeGroupHasAnExistenceAge() {try {
        VaccineStore vaccineStore = new VaccineStore();
        VaccineTypeStore store = new VaccineTypeStore();
        VaccineTypeDTO vaccineType= new VaccineTypeDTO("12345","description","mrna");
        ArrayList<Integer> agegroup = new ArrayList<>(Arrays.asList(1, 2, 3,400));
        ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1));
        ArrayList<Double> dosage = new ArrayList<>(4);
        vaccineStore.createNewVaccine(vaccineType,"pfizer",2,agegroup,dosage,2,interval);
    }catch (IllegalArgumentException e){
        assertNotNull(e);
    }
    }

    @Test
    void ensureAgeGroupIsCorrectlyDefined() {
        try {
            VaccineStore vaccineStore = new VaccineStore();
            VaccineTypeStore store = new VaccineTypeStore();
            VaccineTypeDTO vaccineType= new VaccineTypeDTO("12345","description","mrna");
            ArrayList<Integer> agegroup = new ArrayList<>(Arrays.asList(1, 2, 3));
            ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1));
            ArrayList<Double> dosage = new ArrayList<>(4);
            vaccineStore.createNewVaccine(vaccineType,"pfizer",2,agegroup,dosage,2,interval);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }


    @Test
    void ensureIntervalIsCorrectlyDefined() {
        try {
            VaccineStore vaccineStore = new VaccineStore();
            VaccineTypeStore store = new VaccineTypeStore();
            VaccineTypeDTO vaccineType= new VaccineTypeDTO("12345","description","mrna");
            ArrayList<Integer> agegroup = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            ArrayList<Integer> interval = new ArrayList<>(Arrays.asList(1,2));
            ArrayList<Double> dosage = new ArrayList<>(4);
            vaccineStore.createNewVaccine(vaccineType,"pfizer",2,agegroup,dosage,2,interval);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

}
