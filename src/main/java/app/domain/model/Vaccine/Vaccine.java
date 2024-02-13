package app.domain.model.Vaccine;

import app.domain.model.Exceptions.AgeIsNotMatchingWithAgeGroupException;
import app.domain.model.VaccineType.VaccineTypeDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Vaccine implements Serializable {
    private VaccineTypeDTO vaccineType;
    private String brand;
    private int numDifAgeGroup;
    private ArrayList<String> ageGroup = new ArrayList<>();
    private int numberOfDoses;
    private ArrayList<String> dosageList = new ArrayList<>();
    private ArrayList<String> intervalBetweenDosesList = new ArrayList<>();

    public Vaccine(VaccineTypeDTO vaccineType, String brand, int numDifAgeGroup, ArrayList<Integer> ageGroup, ArrayList<Double> dosageList, int numberOfDoses, ArrayList<Integer> intervalBetweenDosesList) {

        checkVaccineType(vaccineType);
        checkBrand(brand);
        checkNumDifAgeGroup(numDifAgeGroup);
        checkAgeGroup(ageGroup, numDifAgeGroup);
        checkDosageList(dosageList, numberOfDoses, numDifAgeGroup);
        checkNumberOfDoses(numberOfDoses);
        checkIntervalBetweenDosesList(intervalBetweenDosesList, numberOfDoses);

        this.vaccineType = vaccineType;
        this.brand = brand;
        this.numDifAgeGroup = numDifAgeGroup;
        this.numberOfDoses = numberOfDoses;

        for (Integer age : ageGroup) {
            this.ageGroup.add(age.toString());
        }
        for (Double dosage : dosageList) {
            this.dosageList.add(dosage.toString());
        }
        if(intervalBetweenDosesList!=null)
        for (Integer intervalBetweenDoses : intervalBetweenDosesList) {
            this.intervalBetweenDosesList.add(intervalBetweenDoses.toString());
        }


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
        ArrayList<Integer> integer = new ArrayList<>();
        for (String age : ageGroup) {
            integer.add(Integer.valueOf(age));
        }
        return integer;
    }

    public void setAgeGroup(ArrayList<String> ageGroup) {
        this.ageGroup = ageGroup;
    }

    public int getNumberOfDoses() {
        return numberOfDoses;
    }

    public void setNumberOfDoses(int numberOfDoses) {
        this.numberOfDoses = numberOfDoses;
    }

    public ArrayList<Double> getDosageList() {
        ArrayList<Double> doubl = new ArrayList<>();
        for (String dosage : dosageList) {
            doubl.add(Double.valueOf(dosage));
        }
        return doubl;
    }

    public void setDosageList(ArrayList<String> dosageList) {
        this.dosageList = dosageList;
    }

    public ArrayList<Integer> getIntervalBetweenDosesList() {
        ArrayList<Integer> integer = new ArrayList<>();
        for (String age : intervalBetweenDosesList) {
            integer.add(Integer.valueOf(age));
        }
        return integer;
    }

    public void setIntervalBetweenDosesList(ArrayList<String> intervalBetweenDosesList) {
        this.intervalBetweenDosesList = intervalBetweenDosesList;
    }

    /**
     * Method that checks vaccineType Exceptions
     *
     * @param vaccineType VaccineTypeDTO "Vaccine Type" inputted by the VaccineTypeStore
     */
    private void checkVaccineType(VaccineTypeDTO vaccineType) {
        if (vaccineType == null) {
            throw new IllegalArgumentException("Vaccine Type cannot be null.");
        }
    }

    /**
     * Method that checks all acceptance criteria for the vaccine type parameter "brand"
     *
     * @param brand String "brand" inputted by the user
     */
    private void checkBrand(String brand) {
        if (brand.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be blank");
        }
    }

    /**
     * Method that checks all acceptance criteria for the vaccine type parameter "numDifAgeGroup"
     *
     * @param numDifAgeGroup int "Number of Different Age Groups" inputted by the user
     */
    private void checkNumDifAgeGroup(int numDifAgeGroup) {
        if (numDifAgeGroup < 1) {
            throw new IllegalArgumentException("Number cannot be smaller than one");
        }
    }

    /**
     * Method that checks all acceptance criteria for the vaccine type parameter "ageGroup"
     *
     * @param ageGroup       ArrayList<Integer> "Age Groups" inputted by the user
     * @param numDifAgeGroup int "Number of Different Age Groups" inputted by the user
     */
    private void checkAgeGroup(ArrayList<Integer> ageGroup, int numDifAgeGroup) {
        if (ageGroup == null) {
            throw new IllegalArgumentException("Age Group cannot be empty");
        }
        if (!(ageGroup.size() == numDifAgeGroup * 2)) {
            throw new IllegalArgumentException("Age interval is not correctly defined");
        }
        for (Integer integer : ageGroup) {
            if (integer < 1 || integer > 120) {
                throw new IllegalArgumentException("Invalid Age, age must be between 1-120");
            }
        }
    }

    /**
     * Method that checks all acceptance criteria for the vaccine type parameter "numberOfDoses"
     *
     * @param numberOfDoses int "Number Of Doses" inputted by the user
     */
    private void checkNumberOfDoses(int numberOfDoses) {
        if (numberOfDoses < 1) {
            throw new IllegalArgumentException("Number cannot be smaller than one");
        }
    }

    /**
     * Method that checks all acceptance criteria for the vaccine type parameter "dosageList"
     *
     * @param dosageList     ArrayList<Double> "Dosage List" inputted by the user
     * @param numberOfDoses  int "Number Of Doses" inputted by the user
     * @param numDifAgeGroup int "Number of Different Age Groups" inputted by the user
     */
    private void checkDosageList(ArrayList<Double> dosageList, int numberOfDoses, int numDifAgeGroup) {
        if (dosageList == null) {
            throw new IllegalArgumentException("Dosage cannot be null");
        }
        for (Double d : dosageList) {
            if (d < 1) {
                throw new IllegalArgumentException("Number cannot be smaller than one");
            }
        }
        if (!(dosageList.size() == numDifAgeGroup * numberOfDoses)) {
            throw new IllegalArgumentException("Number of dose or number of age groups doesn't match with the dosage list");
        }
    }

    /**
     * Method that checks all acceptance criteria for the vaccine type parameter "intervalBetweenDosesList"
     *
     * @param intervalBetweenDosesList ArrayList<Integer> "Interval Between Doses List" inputted by the user
     * @param numberOfDoses            int "Number Of Doses" inputted by the user
     */
    private void checkIntervalBetweenDosesList(ArrayList<Integer> intervalBetweenDosesList, int numberOfDoses) {

        if (intervalBetweenDosesList != null) {
            for (Integer integer : intervalBetweenDosesList) {
                if (integer < 0) {
                    throw new IllegalArgumentException("Number cannot be negative");
                }
            }
            if (!(intervalBetweenDosesList.size() == numberOfDoses - 1)) {
                throw new IllegalArgumentException("Number of dose doesn't match with number intervals between dose");
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vaccine)) return false;
        Vaccine vaccine = (Vaccine) o;
        return getNumDifAgeGroup() == vaccine.getNumDifAgeGroup() && getNumberOfDoses() == vaccine.getNumberOfDoses() && getVaccineType().equals(vaccine.getVaccineType()) && getBrand().equals(vaccine.getBrand()) && getAgeGroup().equals(vaccine.getAgeGroup()) && getDosageList().equals(vaccine.getDosageList()) && Objects.equals(getIntervalBetweenDosesList(), vaccine.getIntervalBetweenDosesList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVaccineType(), getBrand(), getNumDifAgeGroup(), getAgeGroup(), getNumberOfDoses(), getDosageList(), getIntervalBetweenDosesList());
    }

    @Override
    public String toString() {
        return ("Saving new vaccine:\nVaccine Type: " + vaccineType.toString() + "\nBrand: " + brand + "\nNumber of Different Age Groups: " + numDifAgeGroup + "\nAge Group: " + ageGroup.toString() + "\nDifferent Dosage: " + dosageList.toString() + "\nConfirm?(s/n)");
    }

    public int verifyAgeInAgeGroup(int age) throws AgeIsNotMatchingWithAgeGroupException {

        ArrayList<Integer> ageGroup = getAgeGroup();

        int position = -1;
        int cont=1;

        for (int j = 0; j < ageGroup.size(); j++) {

            int firstAge = ageGroup.get(j);
            int lastAge = ageGroup.get(j + 1);
            cont++;

            if (age >= firstAge && age <= lastAge) {
                position = position+cont;
            }

            j++;
        }

        if (position == -1) {
            throw new AgeIsNotMatchingWithAgeGroupException();
        } else {
            return position;
        }

    }
}