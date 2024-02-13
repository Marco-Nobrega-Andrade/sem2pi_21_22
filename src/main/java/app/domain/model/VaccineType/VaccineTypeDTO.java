package app.domain.model.VaccineType;

import java.io.Serializable;

public class VaccineTypeDTO implements Comparable<VaccineTypeDTO>, Serializable {
    private String code;
    private String description;
    private String tech;

    public VaccineTypeDTO(String code, String description, String tech) {
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

    @Override
    public String toString() {
        return String.format("%s", this.description);
    }

    @Override
    public int compareTo(VaccineTypeDTO o) {
        if(code.equals(o.getCode()))
            return 0;
        else
            return -1;
    }
}
