package app.ui.console;

import app.controller.ReceptionistVaccineScheduleController;
import app.domain.model.Exceptions.*;
import app.domain.model.VaccinationCenter.VaccinationCenterDTO;
import app.domain.model.VaccineType.VaccineTypeDTO;
import app.ui.console.utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class ReceptionistVaccineScheduleUI implements Runnable{
    public ReceptionistVaccineScheduleUI() {
    }
    @Override
    public void run() {
        boolean flag;
        String questionToLeave;
        int snsNumber = -1;
        boolean flagSNSNumber;
        boolean flagDateTime = false;
        boolean flagTime;
        do{
            questionToLeave = "n";
            do {
                try {
                    flagSNSNumber = false;
                    snsNumber = Integer.parseInt(Utils.readLineFromConsole("Type the SNS number."));
                } catch (NumberFormatException e) {
                    flagSNSNumber = true;
                    System.out.println("You need to type numbers");
                }
            }while (flagSNSNumber);

            if (snsNumber != 0) {
                ReceptionistVaccineScheduleController controller = new ReceptionistVaccineScheduleController();
                try {
                    controller.checkForUser(snsNumber);
                    ArrayList<VaccineTypeDTO> vaccineTypeListDto;
                    try {
                        vaccineTypeListDto = controller.getVaccineTypeList();
                        System.out.println("Choose a Vaccine Type.");
                        System.out.println("(Choose the option number)");
                        for (int i = 0; i < vaccineTypeListDto.size(); i++) {
                            System.out.print(i + 1 + ". ");
                            System.out.println(vaccineTypeListDto.get(i).toString());
                        }
                        int indexVaccineType = Utils.readIntegerFromConsole("Option") - 1;

                        while (indexVaccineType < 0|| indexVaccineType > vaccineTypeListDto.size()-1) {
                            System.out.println("Option not valid.");
                            indexVaccineType = Utils.readIntegerFromConsole("Option") - 1;
                        }

                        controller.obtainVaccineType(indexVaccineType);


                        ArrayList<VaccinationCenterDTO> vaccinationCenterListDto = controller.getVaccinationCenterList();
                        System.out.println("Choose a Vaccination Center (name, address, Opening Hours, Closing Hours)");
                        System.out.println("(Choose the option number)");
                        for (int i = 0; i < vaccinationCenterListDto.size(); i++) {
                            System.out.print(i + 1 + ". ");
                            System.out.println(vaccinationCenterListDto.get(i).toString());
                        }

                        int indexVaccinationCenter = Utils.readIntegerFromConsole("Option") - 1;
                        while (indexVaccinationCenter < 0 || indexVaccinationCenter > vaccinationCenterListDto.size()-1) {
                            System.out.println("Option not valid.");
                            indexVaccinationCenter = Utils.readIntegerFromConsole("Option") - 1;
                        }
                        controller.obtainVaccinationCenter(indexVaccinationCenter);

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
                                controller.createVaccineSchedule(snsNumber, date, time);
                                flagDateTime = false;
                                controller.validateVaccineSchedule();

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

                } catch (MissingUserException e) {
                    System.out.println(e.getMessage());
                        do {
                            questionToLeave = Utils.readLineFromConsole("Do you want to keep scheduling a vaccine? (s/n)");
                            if(!(questionToLeave.equals("s") || questionToLeave.equals("n"))) {
                                System.out.println("You need to type s (yes), or n (no)");
                            }
                        } while (!(questionToLeave.equals("s") || questionToLeave.equals("n")));
                    }
                }
        }while (!questionToLeave.equals("n") );
    }
}
