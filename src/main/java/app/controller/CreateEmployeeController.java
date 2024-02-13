package app.controller;


import app.domain.model.Employee.Employee;
import app.domain.model.Employee.EmployeeStore;
import pt.isep.lei.esoft.auth.AuthFacade;

public class CreateEmployeeController {

    private App app;
    private EmployeeStore store;
    Employee ne;
    private AuthFacade authFacade;

    public CreateEmployeeController()
    {
        this.app = App.getInstance();
    }

    public Employee createNewEmployee(String name, String address, int phoneNumber, String email, int citizenCardNumber, String role){

        store = app.getCompany().getStoreEmployee();
        ne = store.createEmployee(name, address, phoneNumber, email, citizenCardNumber, role);
        return ne;
    }

    public String saveEmployee(){
        authFacade = app.getCompany().getAuthFacade();
        String pwd = store.addEmployee(ne,authFacade);
        return pwd;
    }


}
