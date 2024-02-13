package app.domain.model.Employee;

import app.domain.model.PwdGenerator;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;
import java.util.ArrayList;


public class EmployeeStore implements Serializable {

    private ArrayList<Employee> employeeList = new ArrayList<>();
    ArrayList<String> employeeCredentials = new ArrayList<>();
    private int id;

    public Employee createEmployee(String name, String address, int phoneNumber, String email, int citizenCardNumber, String role){
        this.id= employeeList.size()+1;
        String ID = String.format("%010d", id);
        return (new Employee(name, address, phoneNumber, email, citizenCardNumber, role, ID));
    }

    /**
     * Checks if there is an employee with the same email/phoneNumber/CitizenCardNumber
     * @param ne Employee created by the user
     */
    public void validateNewEmployee(Employee ne){
        for (Employee emp : employeeList) {
            if (ne.getEmail().equals(emp.getEmail())){
                throw new IllegalArgumentException("Couldn't save. This email already exists");
            }else if (ne.getPhoneNumber()==emp.getPhoneNumber()){
                throw new IllegalArgumentException("Couldn't save. This phone number already exists");
            }else if (ne.getCitizenCardNumber()==emp.getCitizenCardNumber()){
                throw new IllegalArgumentException("Couldn't save. This Citizen Card Number already exists");
            }
        }
    }
    public void addEmployeeWithCredentials(AuthFacade authFacade){
        for (String em: employeeCredentials){
            String [] employeeAtri =  em.split(",");
            authFacade.addUserWithRole(employeeAtri[0], employeeAtri[1], employeeAtri[2], employeeAtri[3]);
        }

    }

    public String addEmployee(Employee ne, AuthFacade authFacade){

        validateNewEmployee(ne);
        String name= ne.getName();
        String email= ne.getEmail();
        String role= ne.getRole();
        String pwd = PwdGenerator.generatePassword();
        authFacade.addUserWithRole(name, email, pwd, role);
        employeeList.add(ne);
        employeeCredentials.add(name+","+ email+","+pwd+","+role);
        return pwd;
    }

    public ArrayList<Employee> findByRole(String role){
        if(role == null || role.length() == 0){
            throw new IllegalArgumentException("Role cannot be null or empty.");
        }
        ArrayList<Employee> returnList = new ArrayList<>();

        for(Employee e : employeeList){
            if(e.getRole().equals(role)){
                returnList.add(e);
            }
        }

        return returnList;
    }

}
