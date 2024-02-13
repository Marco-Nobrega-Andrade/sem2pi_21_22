package app.domain.model.VaccineType;

import app.domain.model.Exceptions.ListIsEmptyException;

import java.io.Serializable;
import java.util.ArrayList;

public class VaccineTypeStore implements Serializable {
    private ArrayList<VaccineType> vaccineTypeList = new ArrayList<>();
    private VaccineType vaccineTypeOngoingOutbreak = null;

    /**
     * Creates the object "Vaccine Type", verifying all the acceptance criteria.
     *
     * @param code        String "code" inputted by the user
     * @param description String "description" inputted by the user
     * @param tech        String "tech" inputted by the user
     * @return VaccineType object
     */
    public VaccineType createVaccineType(String code, String description, String tech) {
        return new VaccineType(code, description, tech);
    }

    /**
     * if validated, saves the Vaccine Type in the vaccineTypeList
     *
     * @param vt object VaccineType created
     */
    public void saveVaccineType(VaccineType vt) {
        validateVaccineType(vt);
        this.vaccineTypeList.add(vt);
    }

    /**
     * Checks if the code of the new VaccineType already exists in vaccineTypeList
     *
     * @param nvt object VaccineType created
     */
    private void validateVaccineType(VaccineType nvt) {
        for (VaccineType vt : vaccineTypeList) {
            if (vt.getCode().equals(nvt.getCode())) {
                throw new IllegalArgumentException("Couldn't save. This vaccine type already exists");
            }
        }
    }

    /**
     * Gets vaccine type list
     * @return ArrayList with all vaccine type
     */
    public ArrayList<VaccineType> getVaccineTypeList() {
        return new ArrayList<>(vaccineTypeList);
    }

    /**
     * Gets vaccine type from the list
     * @param index integer position of the vaccine type in the list
     * @return vaccine type in position index
     */
    public VaccineType getVaccineTypeFromStore(int index) {
        return vaccineTypeList.get(index);
    }

    /**
     * Gets the vaccine type of the ongoing outbreak
     * @return vaccine type correspondent
     */
    public VaccineType getVaccineTypeOngoingOutbreak() {
        return vaccineTypeOngoingOutbreak;
    }

    /**
     * Sets which vaccine type is the vaccine type of the ongoing outbreak
     * @param index integer position of the vaccine type in the vaccine type list
     * @throws ArrayIndexOutOfBoundsException there isn't any vaccine types registered in the system
     */
    public void setVaccineTypeOngoingOutbreak(int index) throws ArrayIndexOutOfBoundsException {
        if(index >= vaccineTypeList.size() || index < 0){
            throw new ArrayIndexOutOfBoundsException("That is not an option.");
        }
        vaccineTypeOngoingOutbreak = vaccineTypeList.get(index);
    }

    /**
     * Checks if a vaccine type is the same as the vaccine type of the ongoing outbreak
     * @param vaccineType vaccine type to compare
     * @return boolean
     */
    public boolean checkIfOngoingOutbreakIsTheChosenVaccineType(VaccineType vaccineType) {
        if (vaccineTypeOngoingOutbreak == null) {
            return false;
        } else {
            return vaccineTypeOngoingOutbreak.equals(vaccineType);
        }
    }

    public ArrayList<VaccineTypeDTO> verifyConditionVaccineTypeExist() throws ListIsEmptyException {
        if(vaccineTypeList.isEmpty()){
            throw new ListIsEmptyException("There is no Vaccine Type registered on the system.");
        }
        else{
            VaccineTypeMapper vaccineTypeMapper = new VaccineTypeMapper();
            return vaccineTypeMapper.toDTO(vaccineTypeList);
        }
    }

}
