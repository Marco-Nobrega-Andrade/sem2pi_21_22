package app.domain.model.Vaccine;

import app.domain.model.VaccineType.VaccineTypeDTO;
import java.util.ArrayList;

public class VaccineMapper {
    public VaccineMapper (){
    }

    public VaccineDTO toDTO(Vaccine vaccine){
        VaccineTypeDTO vaccineType = vaccine.getVaccineType();
        String brand = vaccine.getBrand();
        int numDifAgeGroup = vaccine.getNumDifAgeGroup();
        ArrayList<Integer> ageGroup = vaccine.getAgeGroup();
        ArrayList<Double> dosageList = vaccine.getDosageList();
        int numberOfDoses = vaccine.getNumberOfDoses();
        ArrayList<Integer> intervalBetweenDosesList = vaccine.getIntervalBetweenDosesList();
        return new VaccineDTO(vaccineType, brand,numDifAgeGroup,ageGroup,dosageList,numberOfDoses,intervalBetweenDosesList);
    }

    public ArrayList <VaccineDTO> toDTO(ArrayList<Vaccine> vaccineList){
        ArrayList<VaccineDTO> vaccineListDTO = new ArrayList<>();
        for (int i=0; i< vaccineList.size(); i++){
            Vaccine vaccine = vaccineList.get(i);
            vaccineListDTO.add(toDTO(vaccine));
        }
        return vaccineListDTO;
    }

    public Vaccine toModel(VaccineDTO vaccine){
        VaccineTypeDTO vaccineType = vaccine.getVaccineType();
        String brand = vaccine.getBrand();
        int numDifAgeGroup = vaccine.getNumDifAgeGroup();
        ArrayList<Integer> ageGroup = vaccine.getAgeGroup();
        ArrayList<Double> dosageList = vaccine.getDosageList();
        int numberOfDoses = vaccine.getNumberOfDoses();
        ArrayList<Integer> intervalBetweenDosesList = vaccine.getIntervalBetweenDosesList();
        return new Vaccine(vaccineType, brand,numDifAgeGroup,ageGroup,dosageList,numberOfDoses,intervalBetweenDosesList);
    }

}
