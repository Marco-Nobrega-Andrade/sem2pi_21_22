package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AdminUI implements Runnable{
    public AdminUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Specify a new type of vaccine.", new SpecifyVaccineTypeUI()));
        options.add(new MenuItem("Register a Vaccination Center.", new RegisterVaccinationCenterUI()));
        options.add(new MenuItem("Create a new Employee ", new CreateEmployeeUI()));
        options.add(new MenuItem("Create a new Vaccine", new SpecifyVaccineUI()));
        options.add(new MenuItem("List employees by their role", new ListEmployeesUI()));
        options.add(new MenuItem("Import users from csv file", new ImportUsersUI()));
        options.add(new MenuItem("Define the ongoing outbreak ", new DefineOngoingOutbreakUI()));


        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
