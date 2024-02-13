package app.domain.model.Employee;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;

public class Employee implements Serializable {

    private String id;
    private String name;
    private String address;
    private int phoneNumber;
    private String email;
    private int citizenCardNumber;
    private String role;


    public Employee(String name, String address, int phoneNumber, String email , int citizenCardNumber, String role, String id) {
        checkName(name);
        checkAddress(address);
        checkPhoneNumber(phoneNumber);
        checkCC(citizenCardNumber);
        Email emailcheck = new Email(email);

        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = String.valueOf(emailcheck);
        this.citizenCardNumber = citizenCardNumber;
        this.role = role;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCitizenCardNumber() {
        return citizenCardNumber;
    }

    public void setCitizenCardNumber(int citizenCardNumber) {
        this.citizenCardNumber = citizenCardNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee: \n" +
                "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Address: " + address + "\n" +
                "Phone Number: " + phoneNumber +"\n"+
                "E-mail: " + email + "\n" +
                "Citizen Card Number: " + citizenCardNumber +"\n" +
                "Role: " + role + "\n";
    }

    /**
     * Checks all acceptance criteria for the Employee parameter "name"
     * @param name String "name" inputted by the user
     */
    private void checkName(String name) {
        if (name.isBlank()){
            throw new IllegalArgumentException("Name cannot be blank.");
        }
    }

    /**
     * Checks all acceptance criteria for the Employee parameter "address"
     * @param address String "address" inputted by the user
     */
    private void checkAddress(String address) {
        if (address.isBlank()){
            throw new IllegalArgumentException("Address cannot be blank.");
        }
    }

    /**
     * Checks all acceptance criteria for the Employee parameter "phoneNumber"
     * @param phoneNumber int "phoneNumber" inputted by the user
     */
    private void checkPhoneNumber(int phoneNumber){
        if((Integer.toString(phoneNumber).length() != 9)){
            throw new IllegalArgumentException("The phone number has to have 9 digits.");
        }
    }

    /**
     * Checks all acceptance criteria for the Employee parameter "citizenCardNumber"
     * @param citizenCardNumber int "citizenCardNumber " inputted by the user
     */
    private void checkCC(int citizenCardNumber){
        if((Integer.toString(citizenCardNumber).length() != 8)){
            throw new IllegalArgumentException("The Citizen Card Number has to have 8 digits.");
        }
    }

}
