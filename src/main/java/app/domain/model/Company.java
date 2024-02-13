package app.domain.model;

import app.domain.model.Employee.EmployeeStore;
import app.domain.model.User.UserStore;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenterStore;
import app.domain.model.Vaccine.VaccineStore;
import app.domain.model.VaccineType.VaccineTypeStore;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.io.*;


/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private String designation;
    private AuthFacade authFacade;
    private EmployeeStore employeeStore = new EmployeeStore();
    private VaccineTypeStore vaccineTypeStore = new VaccineTypeStore();
    private VaccinationCenterStore vaccinationCenterStore = new VaccinationCenterStore();
    private VaccineStore vaccineStore = new VaccineStore();
    private VaccinationCenter actorCenter;
    private UserStore userStore = new UserStore();



    public Company(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
    }


    public UserStore getUserStore() {
        return userStore;
    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public EmployeeStore getStoreEmployee() {
        return employeeStore;
    }

    public VaccineTypeStore getVaccineTypeStore() {
        return vaccineTypeStore;
    }

    public VaccineStore getVaccineStore(){
        return vaccineStore;
    }

    public VaccinationCenterStore getVaccinationCenterStore() {
        return vaccinationCenterStore;
    }

    public VaccinationCenter getActorCenter() {
        return actorCenter;
    }

    public void setActorCenter(VaccinationCenter actorCenter) {
        this.actorCenter = actorCenter;
    }

    public boolean saveSerialization(){
        try {
            ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream("SerializationData.bin"));
            try {
                out.writeObject(employeeStore);
                out.writeObject(vaccineStore);
                out.writeObject(vaccineTypeStore);
                out.writeObject(vaccinationCenterStore);
                out.writeObject(userStore);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException e) {
            return  false;
        }
    }

    public boolean importSerialization (){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("SerializationData.bin"));
            try {
                this.employeeStore = (EmployeeStore) in.readObject();
                this.vaccineStore = (VaccineStore) in.readObject();
                this.vaccineTypeStore= (VaccineTypeStore) in.readObject();
                this.vaccinationCenterStore = (VaccinationCenterStore) in.readObject();
                this.userStore = (UserStore) in.readObject();
            } finally {
                in.close();
                employeeStore.addEmployeeWithCredentials(authFacade);
                userStore.addUserWithCredentials(authFacade);
            }
            return true;
        } catch (IOException | ClassNotFoundException e) {
           return false;
        }
    }
}
