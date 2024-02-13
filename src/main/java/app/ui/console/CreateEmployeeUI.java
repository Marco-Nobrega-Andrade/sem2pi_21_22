package app.ui.console;

import app.controller.CreateEmployeeController;
import app.domain.model.Employee.Employee;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CreateEmployeeUI implements Runnable{

    public CreateEmployeeUI() {
    }

    public void run()
    {
        boolean flag;
        Employee ne = null;
        String role = null;

        List<String> options = new ArrayList<String>();
        options.add("Nurse");
        options.add("Recepcionist");
        options.add("Coordinator");



        String option = (String) Utils.showAndSelectOne(options, "\n\nWhich role to create employ:");

        if (option != null) {
            if (option.equals("Nurse")){
                role=Constants.ROLE_NURSE;
            }else if (option.equals("Recepcionist")){
                role=Constants.ROLE_RECEPCIONIST;
            }else if (option.equals("Coordinator")){
                role=Constants.ROLE_COORDINATOR;
            }

            String name = Utils.readLineFromConsole("Type the name.");
            String address = Utils.readLineFromConsole("Type the address.");
            int phoneNumber = Utils.readIntegerFromConsole("Type the phone number");
            String email = Utils.readLineFromConsole("Type the email.");
            int citizenCardNumber = Utils.readIntegerFromConsole("Type the Citizen Card Number.");

            CreateEmployeeController controller = new CreateEmployeeController();

            do {
                try {
                    ne =controller.createNewEmployee(name, address, phoneNumber, email, citizenCardNumber, role);
                    flag = false;
                } catch (IllegalArgumentException e) {
                    if (e.getMessage().equals("Name cannot be blank.")) {
                        System.out.println(e.getMessage());
                        name = Utils.readLineFromConsole("Type the name.");
                    } else if (e.getMessage().equals("Address cannot be blank.")) {
                        System.out.println(e.getMessage());
                        address = Utils.readLineFromConsole("Type the address.");
                    } else if (e.getMessage().equals("The phone number has to have 9 digits.")) {
                        System.out.println(e.getMessage());
                        phoneNumber = Utils.readIntegerFromConsole("Type phone number.");
                    } else if (e.getMessage().equals("Invalid Email Address.")) {
                        System.out.println(e.getMessage());
                        email = Utils.readLineFromConsole("Type the email.");
                    } else if (e.getMessage().equals("The Citizen Card Number has to have 8 digits.")) {
                        System.out.println(e.getMessage());
                        citizenCardNumber = Utils.readIntegerFromConsole("Type the Citizen Card Number.");
                    }
                    flag = true;
                }
            } while (flag);

            System.out.println(ne);
            if (Utils.confirm("Saving new employee:\n Confirm?(s/n)")){
                try{
                String pwd = controller.saveEmployee();
                System.out.println();
                System.out.println("The employee was succefully registered");
                System.out.println();
                System.out.println("The information needed to login is:");
                System.out.println("Email: " + email);
                System.out.println("Password: " + pwd);
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }

        }

    }
}
