package app.domain.model.User;

import pt.isep.lei.esoft.auth.domain.model.Email;
import java.util.Date;

public class UserDTO {

    private int id;
    private String name;
    private String sex;
    private Date birthDate;
    private String address;
    private int phoneNumber;
    private String eMail;
    private int snsNumber;
    private int ccNumber;

    public UserDTO(int id,String name, String sex, Date birthDate, String address, int phoneNumber, String eMail, int snsNumber, int ccNumber){
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.snsNumber = snsNumber;
        this.ccNumber = ccNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return eMail;
    }

    public int getSnsNumber() {
        return snsNumber;
    }

    public int getCcNumber() {
        return ccNumber;
    }

        @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", eMail='" + eMail + '\'' +
                ", snsNumber=" + snsNumber +
                ", ccNumber=" + ccNumber +
                '}';
    }

}
