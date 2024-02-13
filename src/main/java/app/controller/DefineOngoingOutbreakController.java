package app.controller;

import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.VaccineType.VaccineType;
import app.domain.model.VaccineType.VaccineTypeDTO;
import app.domain.model.VaccineType.VaccineTypeMapper;
import app.domain.model.VaccineType.VaccineTypeStore;
import app.ui.console.DefineOngoingOutbreakUI;

import java.util.ArrayList;

public class DefineOngoingOutbreakController {
    private App app;
    private VaccineTypeStore store;

    public DefineOngoingOutbreakController(){
        this.app = App.getInstance();
    }

    public ArrayList<VaccineTypeDTO> getVaccineTypeList () throws ListIsEmptyException {
        store = app.getCompany().getVaccineTypeStore();
        ArrayList<VaccineType> vaccineTypeList = store.getVaccineTypeList();
        if(vaccineTypeList.isEmpty()){
            throw new ListIsEmptyException("There is no vaccine type registered on the system.");
        }
        VaccineTypeMapper vaccineTypeMapper = new VaccineTypeMapper();
        return vaccineTypeMapper.toDTO(vaccineTypeList);
    }

    public void setVaccineTypeOutbreak(int index) throws ArrayIndexOutOfBoundsException{
        store.setVaccineTypeOngoingOutbreak(index);
    }

    public VaccineType getVaccineTypeFromStore(int index){
        return store.getVaccineTypeFromStore(index);
    }

}
