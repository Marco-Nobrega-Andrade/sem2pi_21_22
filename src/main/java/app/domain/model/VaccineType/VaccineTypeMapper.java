package app.domain.model.VaccineType;

import java.util.ArrayList;

public class VaccineTypeMapper {
    public VaccineTypeMapper (){
    }

    /**
     * Creates vaccine type DTO ArrayList
     * @param vaccineTypeList ArrayList to transform
     * @return ArrayList with all vaccine type DTO
     */
    public ArrayList<VaccineTypeDTO> toDTO(ArrayList<VaccineType> vaccineTypeList){
        ArrayList<VaccineTypeDTO> vaccineTypeListDTO = new ArrayList<>();
        for (int i = 0; i < vaccineTypeList.size(); i++) {
            VaccineType vaccineType = vaccineTypeList.get(i);
            VaccineTypeDTO vaccineTypeDTO = toDTO(vaccineType);
            vaccineTypeListDTO.add(vaccineTypeDTO);
        }

        return vaccineTypeListDTO;
    }

    /**
     * creates a vaccine type DTO object
     * @param vaccineType vaccine type to transform
     * @return vaccine type DTO
     */
    public VaccineTypeDTO toDTO(VaccineType vaccineType){
        String code = vaccineType.getCode();
        String description = vaccineType.getDescription();
        String tech = vaccineType.getTech();
        return new VaccineTypeDTO(code,description,tech);
    }

    public VaccineType toModel(VaccineTypeDTO vaccineTypeDTO){
        String code = vaccineTypeDTO.getCode();
        String description = vaccineTypeDTO.getDescription();
        String tech = vaccineTypeDTO.getTech();
        return new VaccineType(code,description,tech);
    }
}
