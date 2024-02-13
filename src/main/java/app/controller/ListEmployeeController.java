package app.controller;

import app.domain.model.Employee.Employee;
import app.domain.model.Employee.EmployeeStore;

import java.util.ArrayList;

public class ListEmployeeController {

    private App app;
    public ListEmployeeController()
    {
        this.app = App.getInstance();
    }


    public ArrayList<Employee> getListOfEmployeesByRole(String role){
        EmployeeStore employeeStore = app.getCompany().getStoreEmployee();
        return employeeStore.findByRole(role);
    }


}
