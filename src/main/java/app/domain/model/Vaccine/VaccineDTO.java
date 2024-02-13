package app.domain.model.Vaccine;

import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.VaccineType.VaccineTypeDTO;

import java.util.ArrayList;

public class VaccineDTO {

    private VaccineTypeDTO vaccineType;
    private String brand;
    private int numDifAgeGroup;
    private ArrayList<Integer> ageGroup ;
    private int numberOfDoses;
    private ArrayList<Double> dosageList;
    private ArrayList<Integer> intervalBetweenDosesList;

    public VaccineDTO (VaccineTypeDTO vaccineType, String brand, int numDifAgeGroup, ArrayList<Integer> ageGroup , ArrayList<Double> dosageList, int numberOfDoses, ArrayList<Integer> intervalBetweenDosesList){
        this.vaccineType = vaccineType;
        this.brand = brand;
        this.numDifAgeGroup = numDifAgeGroup;
        this.ageGroup = ageGroup;
        this.dosageList = dosageList;
        this.numberOfDoses = numberOfDoses;
        this.intervalBetweenDosesList = intervalBetweenDosesList;
    }

    public VaccineTypeDTO getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(VaccineTypeDTO vaccineType) {
        this.vaccineType = vaccineType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNumDifAgeGroup() {
        return numDifAgeGroup;
    }

    public void setNumDifAgeGroup(int numDifAgeGroup) {
        this.numDifAgeGroup = numDifAgeGroup;
    }

    public ArrayList<Integer> getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(ArrayList<Integer> ageGroup) {
        this.ageGroup = ageGroup;
    }

    public int getNumberOfDoses() {
        return numberOfDoses;
    }

    public void setNumberOfDoses(int numberOfDoses) {
        this.numberOfDoses = numberOfDoses;
    }

    public ArrayList<Double> getDosageList() {
        return dosageList;
    }

    public void setDosageList(ArrayList<Double> dosageList) {
        this.dosageList = dosageList;
    }

    public ArrayList<Integer> getIntervalBetweenDosesList() {
        return intervalBetweenDosesList;
    }

    public void setIntervalBetweenDosesList(ArrayList<Integer> intervalBetweenDosesList) {
        this.intervalBetweenDosesList = intervalBetweenDosesList;
    }

    @Override
    public String toString() {
        return ("Saving new vaccine:\nVaccine Type: " + vaccineType.toString() + "\nBrand: " + brand + "\nNumber of Different Age Groups: " + numDifAgeGroup + "\nAge Group: " + ageGroup.toString() + "\nDifferent Dosage: " + dosageList.toString() + "\nConfirm?(s/n)");
    }
}