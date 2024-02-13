package app.domain.model.Vaccine;

import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.Exceptions.MissingVaccineException;
import app.domain.model.VaccineType.VaccineType;
import app.domain.model.VaccineType.VaccineTypeDTO;

import java.io.Serializable;
import java.util.ArrayList;

public class VaccineStore implements Serializable {
    private ArrayList<Vaccine> vaccineList = new ArrayList<>();

    /**
     * Checks if the parameter "numberOfDoses" of the new Vaccine is correctly defined in SpecifyVacinneUI
     * @param numberOfDoses int created
     * @return true/false boolean
     */

    public boolean validateInputNumberOfDoses (int numberOfDoses){
        return numberOfDoses < 1;
    }

    /**
     * Checks if the parameter "ageGroup" of the new Vaccine is correctly defined in SpecifyVacinneUI
     * @param ageGroup int created
     * @return true/false boolean
     */

    public boolean validateInputAgeGroup(int ageGroup){
        return ageGroup<1||ageGroup>120;
    }

    /**
     * Checks if the parameter "numDifAgeGroup" of the new Vaccine is correctly defined in SpecifyVacinneUI
     * @param numDifAgeGroup int created
     * @return true/false boolean
     */

    public boolean validateInputNumDifAgeGroup(int numDifAgeGroup){
        return numDifAgeGroup<1;
    }

    /**
     * Checks if the parameter "intervalBetweenDoses" of the new Vaccine is correctly defined in SpecifyVacinneUI
     * @param intervalBetweenDoses int created
     *@return true/false boolean
     */

    public boolean validateInputIntervalBetweenDoses(int intervalBetweenDoses){
        return intervalBetweenDoses<0;
    }

    /**
     * Checks if the parameter "dosage" of the new Vaccine is correctly defined in SpecifyVacinneUI
     * @param dosage int created
     * @return true/false boolean
     */

    public boolean validateInputDosage(double dosage){
        return dosage<0;
    }


    /**
     * Creates the object "Vaccine", verifying all the acceptance criteria.
     * @param vaccineType object VaccineType "Vaccine Type" selected by the user and obtained by the Class VaccineTypeStore
     * @param brand String "Brand" inputted by the user
     * @param numDifAgeGroup int "Number of Different Age Groups" inputted by the user
     * @param ageGroup ArrayList<Integer> "Age Groups List" inputted by the user
     * @param dosageList ArrayList<Double> "Dosages List" inputted by the user
     * @param numberOfDoses int "Number Of Doses" inputted by the user
     * @param intervalBetweenDosesList ArrayList<Integer> "List of Intervals Between Doses" inputted by the user
     * @return Vaccine object
     */

    public Vaccine createNewVaccine (VaccineTypeDTO vaccineType, String brand, int numDifAgeGroup, ArrayList<Integer> ageGroup, ArrayList <Double> dosageList, int numberOfDoses, ArrayList <Integer> intervalBetweenDosesList){
        return new Vaccine(vaccineType,brand,numDifAgeGroup,ageGroup,dosageList,numberOfDoses,intervalBetweenDosesList);
    }

    /**
     * if validated, saves the Vaccine in the vaccineList
     * @param nv object Vaccine created
     */

    public void saveVaccine(Vaccine nv){
        validateVaccine(nv);
        addVaccine(nv);
    }


    /**
     * add the object Vaccine into the vaccineList
     * @param nv object Vaccine created
     */

    private void addVaccine(Vaccine nv) {
        this.vaccineList.add(nv);
    }

    /**
     * Checks if the object Vaccine "nv" of the new Vaccine already exists in vaccineTypeList
     * using the method "equals" to compare with all the Vaccine objects saved in vaccineTypeList
     * @param nv object Vaccine created
     */

    private void validateVaccine(Vaccine nv) {
        for (Vaccine nvCopy : vaccineList) {
            if (nvCopy.equals(nv)){
                throw new IllegalArgumentException("Couldn't save. This vaccine already exists");
            }
        }
    }

    public ArrayList<VaccineDTO> getVaccineByTypeAndAge(VaccineType vaccineType, int age){

        ArrayList<Vaccine> vaccinesList = new ArrayList<>();
        VaccineTypeDTO vaccineTypeDTO = new VaccineTypeDTO(vaccineType.getCode(), vaccineType.getDescription(), vaccineType.getTech());

        for (int i=0; i<vaccineList.size(); i++){

            Vaccine vaccine = vaccineList.get(i);
            VaccineTypeDTO vaccineTypeDTO1 = vaccine.getVaccineType();

            if (vaccineTypeDTO.compareTo(vaccineTypeDTO1)==0){

                ArrayList<Integer> ageGroup = vaccine.getAgeGroup();

                for (int j=0 ; j<ageGroup.size(); j++) {

                    int firstAge = ageGroup.get(j);
                    int lastAge = ageGroup.get(j+1);

                    if (age>=firstAge && age<=lastAge) {
                        vaccinesList.add(vaccine);
                    }

                    j++;
                }
            }

        }

        VaccineMapper vaccineMapper = new VaccineMapper();
        return vaccineMapper.toDTO(vaccinesList);

    }

    public Vaccine getVaccineByName(String vaccineName) throws MissingVaccineException {
        for (Vaccine vaccine : vaccineList){
            if (vaccine.getBrand().equals(vaccineName))
                return vaccine;
        }
        throw new MissingVaccineException();
    }

    public ArrayList<VaccineDTO> getVaccine() {
        VaccineMapper mapper = new VaccineMapper();
        return mapper.toDTO(vaccineList);
    }
}
