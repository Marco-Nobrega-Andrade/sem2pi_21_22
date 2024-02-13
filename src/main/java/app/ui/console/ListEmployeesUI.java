package app.ui.console;

import app.controller.CreateEmployeeController;
import app.controller.ListEmployeeController;
import app.domain.model.Employee.Employee;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ListEmployeesUI implements Runnable {


    public ListEmployeesUI() {
    }

    public void run()
    {
        boolean flag;
        Employee ne = null;
        String role = null;
        String option = null;
        List<String> options = new ArrayList<String>();
        options.add("Nurse");
        options.add("Recepcionist");
        options.add("Coordinator");



            option = (String) Utils.showAndSelectOne(options, "\n\nBy which role do you want to see employees?:");

        if (option != null) {
            if (option.equals("Nurse")){
                role= Constants.ROLE_NURSE;
            }else if (option.equals("Recepcionist")){
                role=Constants.ROLE_RECEPCIONIST;
            }else if (option.equals("Coordinator")){
                role=Constants.ROLE_COORDINATOR;
            }

            ListEmployeeController controller = new ListEmployeeController();

            ArrayList<Employee> employees = controller.getListOfEmployeesByRole(role);//Nao pode haver dependencia para o domain.

            for(Employee e : employees){
                    System.out.println(e.toString());
            }

        }

    }
}
