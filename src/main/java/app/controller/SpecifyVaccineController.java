package app.controller;

import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.Vaccine.*;
import app.domain.model.VaccineType.VaccineTypeDTO;
import app.domain.model.VaccineType.VaccineTypeStore;

import java.util.ArrayList;

public class SpecifyVaccineController{
    private App app;
    private VaccineStore storeV;

    /**
     * Gets the "VaccineStore" first to after use it to create the object
     */

    public SpecifyVaccineController() {
        this.app = App.getInstance();
        this.storeV = app.getCompany().getVaccineStore();
    }

    /**
     * Method that checks if the object Vaccine Type exist, for the new Vaccine, in VaccineTypeStore.
     * Gets the "VaccineTypeStore" first and then uses it to verify the condition
     */

    public ArrayList<VaccineTypeDTO> verifyConditionVaccineTypeExist() throws ListIsEmptyException {
        VaccineTypeStore vaccineTypeStore = app.getCompany().getVaccineTypeStore();
        return vaccineTypeStore.verifyConditionVaccineTypeExist();
    }

    /**
     * Method that checks if the parameter "numberOfDoses" of the new Vaccine is correctly defined in SpecifyVacinneUI
     * @param numberOfDoses int created
     * @return true/false boolean
     */

    public boolean validateInputNumberOfDoses (int numberOfDoses){
        return storeV.validateInputNumberOfDoses(numberOfDoses);
    }

    /**
     * Method that checks if the parameter "ageGroup" of the new Vaccine is correctly defined in SpecifyVacinneUI
     * @param ageGroup int created
     * @return true/false boolean
     */

    public boolean validateInputAgeGroup(int ageGroup){
        return storeV.validateInputAgeGroup(ageGroup);
    }

    /**
     * Method that checks if the parameter "numDifAgeGroup" of the new Vaccine is correctly defined in SpecifyVacinneUI
     * @param numDifAgeGroup int created
     * @return true/false boolean
     */

    public boolean validateInputNumDifAgeGroup(int numDifAgeGroup){
        return storeV.validateInputNumDifAgeGroup(numDifAgeGroup);
    }

    /**
     * Checks if the parameter "intervalBetweenDoses" of the new Vaccine is correctly defined in SpecifyVacinneUI
     * @param intervalBetweenDose int created
     * @return true/false boolean
     */

    public boolean validateInputIntervalBetweenDose(int intervalBetweenDose){
        return storeV.validateInputIntervalBetweenDoses(intervalBetweenDose);
    }

    /**
     * Checks if the parameter "dosage" of the new Vaccine is correctly defined in SpecifyVacinneUI
     * @param dosage int created
     * @return true/false boolean
     */


    public boolean validateInputDosage(double dosage){
        return storeV.validateInputDosage(dosage);
    }

    /**
     * Method that creates the Vaccine object.
     * @param vaccineType object VaccineType "Vaccine Type" selected by the user and obtained by the Class VaccineTypeStore
     * @param brand String "Brand" inputted by the user
     * @param numDifAgeGroup int "Number of Different Age Groups" inputted by the user
     * @param ageGroup ArrayList<Integer> "Age Groups List" inputted by the user
     * @param dosageList ArrayList<Double> "Dosages List" inputted by the user
     * @param numberOfDoses int "Number Of Doses" inputted by the user
     * @param intervalBetweenDosesList ArrayList<Integer> "List of Intervals Between Doses" inputted by the user
     */

    public Vaccine createNewVaccine (VaccineTypeDTO vaccineType ,String brand, int numDifAgeGroup ,ArrayList<Integer> ageGroup, ArrayList<Double> dosageList, int numberOfDoses, ArrayList<Integer> intervalBetweenDosesList){
        return storeV.createNewVaccine(vaccineType, brand, numDifAgeGroup,ageGroup, dosageList, numberOfDoses,intervalBetweenDosesList);

    }

    /**
     * Saves the new Vaccine in VaccineStore
     */

    public void saveVaccine(Vaccine nv){
        storeV.saveVaccine(nv);
    }

}
