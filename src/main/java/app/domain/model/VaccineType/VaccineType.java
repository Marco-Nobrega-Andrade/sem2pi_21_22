package app.domain.model.VaccineType;

import app.domain.shared.Constants;

import java.io.Serializable;

public class VaccineType implements Comparable<VaccineType>, Serializable {
    private String code;
    private String description;
    private String tech;

    public VaccineType(String code, String description, String tech) {
        checkCode(code);
        checkDescription(description);
        checkTech(tech);
        this.code = code;
        this.description = description;
        this.tech = tech;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    /**
     * Method that checks all acceptance criteria for the vaccine type parameter "code"
     * @param code
     */
    private void checkCode(String code) {
        code = code.trim();
        if (code.length() != 5){
            throw new IllegalArgumentException("Code must have 5 characters.");
        }
        if (!checkForNonAlphanumericChars(code)){
            throw new IllegalArgumentException("Code must only have alphanumeric characters.");
        }
        if (code.isBlank()){
            throw new IllegalArgumentException("Code cannot be blank.");
        }
    }

    /**
     * Method that checks if the String "code" is composed by only alphanumeric characters
     * @param code String code inputted by the user
     * @return false if there is a non-alphanumeric character, true otherwise
     */
    private boolean checkForNonAlphanumericChars(String code) {
        for (int i = 0; i < code.length(); i++) {
            if(!checkIfCharIsAlphanumeric(code.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Method that checks if a character belongs to the "AlphanumericCharacters" constant
     * @param charAt character from the String "code"
     * @return true if it is an alphanumeric character, false otherwise
     */
    private boolean checkIfCharIsAlphanumeric(char charAt) {
        for (int i = 0; i < Constants.ALPHANUMERIC_CHARS.length(); i++) {
            if (Constants.ALPHANUMERIC_CHARS.charAt(i) == charAt){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks all acceptance criteria for the vaccine type parameter "description"
     * @param description String "description" inputted by the user
     */
    private void checkDescription(String description) {
        if (description.isBlank()){
            throw new IllegalArgumentException("Description cannot be blank.");
        }
    }

    /**
     * Checks all acceptance criteria for the vaccine type parameter "tech"
     * @param tech String "tech" inputted by the user
     */
    private void checkTech(String tech) {
        if (tech.isBlank()){
            throw new IllegalArgumentException("The vaccine technology cannot be blank.");
        }else if (!tech.equalsIgnoreCase("Live-attenuated") && !tech.equalsIgnoreCase("Inactivated") && !tech.equalsIgnoreCase("Subunit") && !tech.equalsIgnoreCase("Toxoid") && !tech.equalsIgnoreCase("Viral vector") && !tech.equalsIgnoreCase("mRNA")){
            throw new IllegalArgumentException("The vaccine technology must be one of the presented options.");
        }

    }

    /**
     * Creates String with all info about the vaccine type
     * @return String with all information
     */
    @Override
    public String toString() {
        return "VaccineType{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", tech='" + tech + '\'' +
                '}';
    }

    /**
     * Check if two vaccine types are equal
     * @param otherObject object to compare
     * @return boolean
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        VaccineType otherVaccineType = (VaccineType) otherObject;
        return code.equals(otherVaccineType.code);
    }

    /**
     *
     */
    @Override
    public int compareTo(VaccineType o) {
        if(code.equals(o.getCode()))
            return 0;
        else
            return -1;
    }
}
