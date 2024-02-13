package app.ui.console;

import app.controller.RegisterVaccinationCenterController;
import app.ui.console.utils.Utils;

public class RegisterVaccinationCenterUI implements Runnable {
    public RegisterVaccinationCenterUI() {
    }

    @Override
    public void run() {
        boolean flag;
        String name = Utils.readLineFromConsole("Type the name of the Vaccination Centre.");
        String address = Utils.readLineFromConsole("Type the address of the Vaccination Centre.");
        String phoneNumber = Utils.readLineFromConsole("Type the phone number of the Vaccination Centre.");
        String email = Utils.readLineFromConsole("Type the e-mail of the Vaccination Centre.");
        String faxNumber = Utils.readLineFromConsole("Type the fax number of the Vaccination Centre.");
        String websiteAddress = Utils.readLineFromConsole("Type the website address of the Vaccination Centre.");
        String openingHours = Utils.readLineFromConsole("Type the opening hours of the Vaccination Centre. (hh:mm)");
        String closingHours = Utils.readLineFromConsole("Type the closing hours of the Vaccination Centre. (hh:mm)");
        String slotDuration = Utils.readLineFromConsole("Type the slot duration of the Vaccination Centre. (min)");
        String maximumNumVaccinesSlot = Utils.readLineFromConsole("Type the maximum number vaccines per slot on this Vaccination Centre.");
        String option;
        boolean isHealthCareCenter = false;
        String typeOfCenter;

        do {
            System.out.println("Choose the type of vaccination center.");
            System.out.println("1. Healthcare Center");
            System.out.println("2. Mass Vaccination Center");
            option = Utils.readLineFromConsole("Type the option number:");

            if(option.equals("1"))
                isHealthCareCenter = true;
            if(!option.equals("1") && !option.equals("2"))
                System.out.println("That is not one of the options \n");
        }while (!option.equals("1") && !option.equals("2"));
        if(isHealthCareCenter){
            typeOfCenter = "Healthcare Center";
        }else{
            typeOfCenter = "Vaccination Center";
        }
        RegisterVaccinationCenterController controller = new RegisterVaccinationCenterController();


        do {
            // Tries to save the inputs, creating a Vaccination Centre object
            try {
                controller.registerVaccinationCenter(name, address, phoneNumber, email, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maximumNumVaccinesSlot, isHealthCareCenter);
                flag = false;
                /* Catch any exception thrown when creating the object.
                  Checks for messages, then prints that message and aks for input again */
            } catch (NumberFormatException e){
                System.out.println("Time is in a invalid format");
                openingHours = Utils.readLineFromConsole("Type the opening hours of the Vaccination Centre. (hh:mm)");
                closingHours = Utils.readLineFromConsole("Type the closing hours of the Vaccination Centre. (hh:mm)");
                flag = true;
            } catch (IllegalArgumentException e) {
                if (e.getMessage().equals("The name cannot be blank.")) {
                    System.out.println(e.getMessage());
                    name = Utils.readLineFromConsole("Type the name of the Vaccination Centre.");
                } else if (e.getMessage().equals("The address cannot be blank.")) {
                    System.out.println(e.getMessage());
                    address = Utils.readLineFromConsole("Type the address of the Vaccination Centre.");
                } else if (e.getMessage().equals("The phone number cannot be blank.")) {
                    System.out.println(e.getMessage());
                    phoneNumber = Utils.readLineFromConsole("Type the phone number of the Vaccination Centre.");
                } else if (e.getMessage().equals("The phone number has to have 9 digits.")) {
                    System.out.println(e.getMessage());
                    phoneNumber = Utils.readLineFromConsole("Type the phone number of the Vaccination Centre.");
                } else if (e.getMessage().equals("Invalid Email Address.")) {
                    System.out.println(e.getMessage());
                    email = Utils.readLineFromConsole("Type the email of the Vaccination Centre.");
                } else if (e.getMessage().equals("The Fax Number cannot be blank.")) {
                    System.out.println(e.getMessage());
                    faxNumber = Utils.readLineFromConsole("Type the fax number of the Vaccination Centre.");
                } else if (e.getMessage().equals("The fax number has to have 9 digits.")) {
                    System.out.println(e.getMessage());
                    faxNumber = Utils.readLineFromConsole("Type the fax number of the Vaccination Centre.");
                } else if (e.getMessage().equals("The website is incorrect.")) {
                    System.out.println(e.getMessage());
                    websiteAddress = Utils.readLineFromConsole("Type the website address of the Vaccination Centre.");
                } else if (e.getMessage().equals("The website address cannot be blank.")) {
                    System.out.println(e.getMessage());
                    websiteAddress = Utils.readLineFromConsole("Type the website address of the Vaccination Centre.");
                } else if (e.getMessage().equals("The opening hours cannot be blank.")) {
                    System.out.println(e.getMessage());
                    openingHours = Utils.readLineFromConsole("Type the opening hours of the Vaccination Centre. (hh:mm)");
                } else if (e.getMessage().equals("The opening hours should be between 00:00 and 23:59")) {
                    System.out.println(e.getMessage());
                    openingHours = Utils.readLineFromConsole("Type the opening hours of the Vaccination Centre. (hh:mm)");
                } else if (e.getMessage().equals("The time is in the wrong format.")) {
                    System.out.println(e.getMessage());
                    openingHours = Utils.readLineFromConsole("Type the opening hours of the Vaccination Centre. (hh:mm)");
                } else if (e.getMessage().equals("The closing hours cannot be blank.")) {
                    System.out.println(e.getMessage());
                    closingHours = Utils.readLineFromConsole("Type the closing hours of the Vaccination Centre. (hh:mm)");
                } else if (e.getMessage().equals("The closing hours should be between 00:00 and 23:59")) {
                    System.out.println(e.getMessage());
                    closingHours = Utils.readLineFromConsole("Type the closing hours of the Vaccination Centre. (hh:mm)");
                } else if (e.getMessage().equals("The time is in the wrong format.")) {
                    System.out.println(e.getMessage());
                    closingHours = Utils.readLineFromConsole("Type the closing hours of the Vaccination Centre. (hh:mm)");
                } else if (e.getMessage().equals("The slot duration cannot be blank.")) {
                    System.out.println(e.getMessage());
                    slotDuration = Utils.readLineFromConsole("Type the slot duration of the Vaccination Centre.");
                } else if (e.getMessage().equals("The slot duration should be between 1-59 min")) {
                    System.out.println(e.getMessage());
                    slotDuration = Utils.readLineFromConsole("Type the slot duration of the Vaccination Centre. (min)");
                } else if (e.getMessage().equals("The maximum number of vaccines per slot can't be below 1")) {
                    System.out.println(e.getMessage());
                    maximumNumVaccinesSlot = Utils.readLineFromConsole("Type the maximum number vaccines per slot on this Vaccination Centre.");
                } else if (e.getMessage().equals("The maximum number of vaccines per slot cannot be blank.")) {
                    System.out.println(e.getMessage());
                    maximumNumVaccinesSlot = Utils.readLineFromConsole("Type the maximum number vaccines per slot on this Vaccination Centre.");
                }
                flag = true;
            }
        } while (flag);

        //Presents the data and asks for a confirmation
        if (Utils.confirm("Registering the Vaccination Centre: \nName: "+name+"\nAddress: "+address+"\nPhone Number: "+phoneNumber+"\nE-mail: "+email+"\nFax Number: "+faxNumber+ "\nWebsite Address: "+websiteAddress+"\nOpening Hours: "+openingHours+ "\nClosing Hours: "+closingHours+"\nSlot Duration: "+slotDuration+"\nMaximum number of vaccines per slot: "+maximumNumVaccinesSlot+"\nType of center: "+typeOfCenter+" \nConfirm?(s/n)")) {
            controller.saveVaccinationCenter();
            System.out.println();
            System.out.println("The Vaccination Centre was registered.");
        }
    }
}