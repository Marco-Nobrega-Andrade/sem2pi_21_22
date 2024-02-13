package Test;

import app.domain.model.Employee.Employee;
import app.domain.model.Employee.EmployeeStore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeStoreTest {

    @Test
    public void ensureNameisBlankNotAllowed() {
        try {
            EmployeeStore store = new EmployeeStore();
            store.createEmployee("", "qa", 123456789, "as@gmail.com", 12312312, "NURSE");
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    public void ensureAddressisBlankNotAllowed() {
        try {
            EmployeeStore store = new EmployeeStore();
            store.createEmployee("joao", "", 123456789, "as@gmail.com", 12312312, "NURSE");
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    public void ensureEmailisBlankNotAllowed() {
        try {
            EmployeeStore store = new EmployeeStore();
            store.createEmployee("as", "qa", 123456789, "", 12312312, "NURSE");
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    public void ensureEmailisIncorretNotAllowed() {
        try {
            EmployeeStore store = new EmployeeStore();
            store.createEmployee("as", "qa", 123456789, "as@gmailcom", 12312312, "NURSE");
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    public void ensurePhoneNumberIsIncorrectNotAllowed() {
        try {
            EmployeeStore store = new EmployeeStore();
            store.createEmployee("as", "qa", 123, "as@gmail.com", 12312312, "NURSE");
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    public void ensureCitizenCardIsIncorrectNotAllowed() {
        try {
            EmployeeStore store = new EmployeeStore();
            store.createEmployee("asas", "qa", 123456789, "as@gmail.com", 123, "NURSE");
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }

    @Test
    public void validateNewEmployeeIfPhoneNumberIsTheSame(){
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("asas", "qa", 123456789, "as@gmail.com", 12312312, "NURSE",String.format("%010d", 1)));
        Employee ne = new  Employee("jon", "qa", 123456789, "ass@gmail.com", 12312332, "NURSE",String.format("%010d", 2));
        try {
            for (Employee emp : employeeList) {
                if (ne.getEmail().equals(emp.getEmail())){
                    throw new IllegalArgumentException("Couldn't save. This email already exists");
                }else if (ne.getPhoneNumber()==emp.getPhoneNumber()){
                    throw new IllegalArgumentException("Couldn't save. This phone number already exists");
                }else if (ne.getCitizenCardNumber()==emp.getCitizenCardNumber()){
                    throw new IllegalArgumentException("Couldn't save. This Citizen Card Number already exists");
                }
            }

        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }
    @Test
    public void validateNewEmployeeIfEmailIsTheSame(){
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("asas", "qa", 123456789, "as@gmail.com", 12312312, "NURSE",String.format("%010d", 1)));
        Employee ne = new  Employee("jon", "qa", 123123123, "as@gmail.com", 12312332, "NURSE",String.format("%010d", 2));
        try {
            for (Employee emp : employeeList) {
                if (ne.getEmail().equals(emp.getEmail())){
                    throw new IllegalArgumentException("Couldn't save. This email already exists");
                }else if (ne.getPhoneNumber()==emp.getPhoneNumber()){
                    throw new IllegalArgumentException("Couldn't save. This phone number already exists");
                }else if (ne.getCitizenCardNumber()==emp.getCitizenCardNumber()){
                    throw new IllegalArgumentException("Couldn't save. This Citizen Card Number already exists");
                }
            }

        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }
    @Test
    public void validateNewEmployeeIfCCIsTheSame(){
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("asas", "qa", 123456789, "as@gmail.com", 12312312, "NURSE",String.format("%010d", 1)));
        Employee ne = new  Employee("jon", "qa", 123123123, "ass@gmail.com", 12312313, "NURSE",String.format("%010d", 2));
        try {
            for (Employee emp : employeeList) {
                if (ne.getEmail().equals(emp.getEmail())){
                    throw new IllegalArgumentException("Couldn't save. This email already exists");
                }else if (ne.getPhoneNumber()==emp.getPhoneNumber()){
                    throw new IllegalArgumentException("Couldn't save. This phone number already exists");
                }else if (ne.getCitizenCardNumber()==emp.getCitizenCardNumber()){
                    throw new IllegalArgumentException("Couldn't save. This Citizen Card Number already exists");
                }
            }

        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }



}