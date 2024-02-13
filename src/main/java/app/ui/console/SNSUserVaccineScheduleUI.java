package app.ui.console;

import app.controller.SNSUserVaccineScheduleController;
import app.domain.model.Exceptions.*;
import app.domain.model.VaccinationCenter.VaccinationCenterDTO;
import app.domain.model.VaccineType.VaccineTypeDTO;
import app.ui.console.utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class SNSUserVaccineScheduleUI implements Runnable{

    public SNSUserVaccineScheduleUI() {
    }

    @Override
    public void run() {
        boolean flagTime;
        SNSUserVaccineScheduleController controller = new SNSUserVaccineScheduleController();

        controller.getUserSNSNumber();

        try {
            ArrayList<VaccineTypeDTO> vaccineTypeListDTO = controller.getVaccineTypeList();
            System.out.println("Choose a Vaccine Type.");
            System.out.println("(Choose the option number)");
            int listIndex = 1;
            for (VaccineTypeDTO vtDTo : vaccineTypeListDTO){
                System.out.printf("%d. %s\n",listIndex,vtDTo.toString());
                listIndex++;
            }

            int vaccineTypeOption = Utils.readIntegerFromConsole("Option:");

            while(vaccineTypeOption < 1 || vaccineTypeOption > vaccineTypeListDTO.size()){
                System.out.println("This option does not exist. Choose the correct option.");
                vaccineTypeOption = Utils.readIntegerFromConsole("Option:");
            }


            controller.obtainVaccineType(vaccineTypeOption-1);


            ArrayList<VaccinationCenterDTO> vaccinationCenterListDTO= controller.getVaccinationCenterList();
            System.out.println("Choose a Vaccination Center (Name,Address,Opening hours, Opening hours).");
            System.out.println("(Choose the option number)");
            listIndex = 1;
            for (VaccinationCenterDTO vcDTo : vaccinationCenterListDTO){
                System.out.printf("%d. %s\n",listIndex,vcDTo.toString());
                listIndex++;
            }
            int vaccinationCenterOption = Utils.readIntegerFromConsole("Option:");

            while(vaccinationCenterOption < 1 || vaccinationCenterOption > vaccinationCenterListDTO.size()){
                System.out.println("This option does not exist. Choose the correct option.");
                vaccinationCenterOption = Utils.readIntegerFromConsole("Option:");
            }
            controller.obtainVaccinationCenter(vaccinationCenterOption-1);
            boolean flagDateTime = false;
            do {
                String time = "";
                Date date = Utils.readDateFromConsole("Type the date of the appointment (DD-MM-YYYY).");
                do {
                    try {
                        flagTime = false;
                        time = Utils.readLineFromConsole("Type the time of the appointment.");
                        while (time == null) {
                            System.out.println("Time isn't valid.");
                            time = Utils.readLineFromConsole("Type the time of the appointment.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("You need to insert a time");
                        flagTime = true;
                    }
                }while (flagTime);

                try {
                    controller.createVaccineSchedule(date, time);
                    controller.validateVaccineSchedule();
                    flagDateTime = false;

                    if (Utils.confirm("Saving vaccine schedule :\n"+controller.getScheduleInfo()+"\nConfirm? (s/n)")) {
                        controller.saveVaccineSchedule();
                        System.out.println("The vaccine schedule was saved with success.");
                        if (Utils.confirm("Do you want to receive a SMS notification with all the data? (s/n)")) {

                            try {
                                File file = new File("SMS.txt");
                                FileWriter fw = new FileWriter(file,true);
                                fw.append(controller.getScheduleInfo());
                                fw.close();
                                System.out.println("The sms notification was sent.");
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                } catch (DuplicateScheduleException e) {
                    System.out.println(e.getMessage());
                } catch (TimeNotPredefinedException | ScheduleForThisTimeIsFullException | TimeOutOfBoundsException | ScheduleAtTheSameTimeException e) {
                    System.out.println(e.getMessage());
                    flagDateTime = true;
                }

            }while (flagDateTime);
        } catch (ListIsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }
}
