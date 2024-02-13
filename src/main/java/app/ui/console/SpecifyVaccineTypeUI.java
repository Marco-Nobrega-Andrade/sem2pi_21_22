package app.ui.console;

import app.controller.SpecifyVaccineTypeController;
import app.ui.console.utils.Utils;

public class SpecifyVaccineTypeUI implements Runnable{

    public SpecifyVaccineTypeUI(){
    }

    @Override
    public void run() {

        boolean flag;
        String code = Utils.readLineFromConsole("Type the vaccine type code (must be 5 alphanumeric digits).");
        String description = Utils.readLineFromConsole("Type the vaccine type description.");
        String tech = Utils.readLineFromConsole("Type the vaccine type technology (Live-attenuated,Inactivated,Subunit,Toxoid,Viral vector,mRNA).");

        SpecifyVaccineTypeController controller = new SpecifyVaccineTypeController();

        do {
            // Tries to save the inputs, creating a VaccineType object
            try {
                controller.createVaccineType(code,description,tech);
                flag = false;
            // Catch any exception thrown when creating the object.
            // Checks for the exceptions messages, then prints that message and asks for the input again
            } catch (IllegalArgumentException e) {
                if (e.getMessage().equals("Code must have 5 characters.")){
                    System.out.println(e.getMessage());
                    code = Utils.readLineFromConsole("Type the vaccine type code (must be 5 alphanumeric digits).");
                }else if (e.getMessage().equals("Code must only have alphanumeric characters.")){
                    System.out.println(e.getMessage());
                    code = Utils.readLineFromConsole("Type the vaccine type code (must be 5 alphanumeric digits).");
                }else if (e.getMessage().equals("Code cannot be blank.")){
                    System.out.println(e.getMessage());
                    code = Utils.readLineFromConsole("Type the vaccine type code (must be 5 alphanumeric digits).");
                }else if (e.getMessage().equals("Description cannot be blank.")){
                    System.out.println(e.getMessage());
                    description = Utils.readLineFromConsole("Type the vaccine type description.");
                }else if (e.getMessage().equals("The vaccine technology cannot be blank.")){
                    System.out.println(e.getMessage());
                    tech = Utils.readLineFromConsole("Type the vaccine type technology (Live-attenuated,Inactivated,Subunit,Toxoid,Viral vector,mRNA).");
                }else if (e.getMessage().equals("The vaccine technology must be one of the presented options.")){
                    System.out.println(e.getMessage());
                    tech = Utils.readLineFromConsole("Type the vaccine type technology (Live-attenuated,Inactivated,Subunit,Toxoid,Viral vector,mRNA).");
                }
                flag = true;
            }
        } while (flag);

        //Presents the data and asks for a confirmation
        if (Utils.confirm("Saving new vaccine type: \nCode: "+code+"\nDescription: "+description+"\nTechnology: "+tech+"\nConfirm?(s/n)")){
            try {
                controller.saveVaccineType();
                System.out.println();
                System.out.println("The vaccine type was saved.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
