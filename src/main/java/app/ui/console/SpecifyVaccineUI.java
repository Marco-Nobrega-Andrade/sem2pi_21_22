package app.ui.console;

import app.controller.SpecifyVaccineController;
import app.domain.model.Exceptions.ListIsEmptyException;

import app.domain.model.Vaccine.Vaccine;
import app.domain.model.VaccineType.VaccineTypeDTO;
import app.ui.console.utils.*;

import java.util.ArrayList;

public class SpecifyVaccineUI implements Runnable {

    @Override
    public void run() {
        SpecifyVaccineController controller = new SpecifyVaccineController();
        try {
            ArrayList<VaccineTypeDTO> vtlDTO = controller.verifyConditionVaccineTypeExist();
            int cont = 0;
            for (VaccineTypeDTO vaccineTypeDTO : vtlDTO) {
                cont++;
                System.out.println(cont + "-. " + vaccineTypeDTO.toString());
            }
            int select = Utils.readIntegerFromConsole("Please, select one type of vaccine you want to record");
            while (select > vtlDTO.size() || select < 1) {
                select = Utils.readIntegerFromConsole("\nPlease,choose an existent option\n");
            }
            select--;
            VaccineTypeDTO vaccineTypeDTO = vtlDTO.get(select);
            String brand = Utils.readLineFromConsole("Type the vaccine brand name");
            int numberOfDoses = Utils.readIntegerFromConsole("Type number of doses");
            while (controller.validateInputNumberOfDoses(numberOfDoses)) {
                System.out.println("Number of dose must be greater than 0");
                numberOfDoses = Utils.readIntegerFromConsole("Type number of doses");
            }
            int numDifAgeGroup = Utils.readIntegerFromConsole("Type number of different ageGroups ");
            while (controller.validateInputNumDifAgeGroup(numDifAgeGroup)) {
                System.out.println("Number of different age groups must be greater than 0");
                numDifAgeGroup = Utils.readIntegerFromConsole("Type number of different age groups");
            }
            ArrayList<Integer> ageGroup = new ArrayList<>();
            int ageGroupA;
            int ageGroupB;
            String s= "[";
            StringBuilder ageText = new StringBuilder(s);
            for (int i = 1; i <= numDifAgeGroup; i++) {
                if (i!=1){
                    ageText.append(", ");
                }
                ageGroupA = Utils.readIntegerFromConsole("Type the beginning age of the "+i+"º interval");
                while (controller.validateInputAgeGroup(ageGroupA)) {
                    System.out.println("Invalid age, please try again (only between 1-120)");
                    ageGroupA = Utils.readIntegerFromConsole("Type the beginning age of the "+i+"º interval");
                }
                ageGroup.add(ageGroupA);
                ageText.append(ageGroupA).append("-");
                ageGroupB = Utils.readIntegerFromConsole("Type the end age of the "+i+"º interval");
                while (controller.validateInputAgeGroup(ageGroupB)) {
                    System.out.println("Invalid age, please try again (only between 1-120)");
                    ageGroupB = Utils.readIntegerFromConsole("Type the end age of the "+i+"º interval");
                }
                ageGroup.add(ageGroupB);
                ageText.append(ageGroupB);
            }
            ageText.append("]").append(" Years");
            ArrayList<Double> dosageList = new ArrayList<>();
            double dosage;
            for (int i = 1; i <= numberOfDoses; i++) {
                System.out.println(i+"º DOSE");
                for (int j = 1; j <= numDifAgeGroup; j++) {
                    dosage = Utils.readDoubleFromConsole("Type dosage for the "+j+"º age group");
                    while (controller.validateInputDosage(dosage)) {
                        System.out.println("Invalid dosage, please try again");
                        dosage = Utils.readDoubleFromConsole("Type dosage for the "+j+"º age group");
                    }
                    dosageList.add(dosage);
                }
            }

            if (numberOfDoses > 1) {
                ArrayList<Integer> intervalBetweenDosesList = new ArrayList<>();
                int intervalBetweenDose;
                for (int i = 1; i <= numberOfDoses - 1; i++) {
                    intervalBetweenDose = Utils.readIntegerFromConsole("Type days between "+i+"º dose and the next one");
                    while (controller.validateInputIntervalBetweenDose(intervalBetweenDose)) {
                        System.out.println("Invalid number, please try again");
                        intervalBetweenDose = Utils.readIntegerFromConsole("Type days between " +i+ "º dose and the next one");
                    }
                    intervalBetweenDosesList.add(intervalBetweenDose);
                }
                Vaccine vaccine = controller.createNewVaccine(vaccineTypeDTO, brand, numDifAgeGroup, ageGroup, dosageList, numberOfDoses, intervalBetweenDosesList);
                if (Utils.confirm("Saving new vaccine:\n" + vaccineTypeDTO + "\nBrand: " + brand + "\nNumber of Different Age Groups: " + numDifAgeGroup + "\nAge Group: " + ageText + "\nDifferent Dosage: " + dosageList +" mg"+"\nInterval Between each dosage: "+intervalBetweenDosesList+" Days"+"\nConfirm?(s/n)")) {
                    controller.saveVaccine(vaccine);
                    System.out.println();
                    System.out.println("The vaccine was saved.");
                }
            } else {
                Vaccine vaccine=controller.createNewVaccine(vaccineTypeDTO, brand, numDifAgeGroup, ageGroup, dosageList, numberOfDoses, null);
                if (Utils.confirm("Saving new vaccine:\n" + vaccineTypeDTO + "\nBrand: " + brand + "\nNumber of Different Age Groups: " + numDifAgeGroup + "\nAge Group: " + ageText + "\nDifferent Dosage: " + dosageList +" mg"+"\nConfirm?(s/n)")) {
                    controller.saveVaccine(vaccine);
                    System.out.println();
                    System.out.println("The vaccine was saved.");
                }
            }
        }catch (ListIsEmptyException e){
            System.out.println(e.getMessage());
        }

    }
}