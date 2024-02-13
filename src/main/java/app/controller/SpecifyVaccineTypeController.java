package app.controller;

import app.domain.model.VaccineType.VaccineType;
import app.domain.model.VaccineType.VaccineTypeStore;

public class SpecifyVaccineTypeController {
    private App app;
    private VaccineTypeStore store;
    private VaccineType vt;

    public SpecifyVaccineTypeController() {
        this.app = App.getInstance();
    }

    /**
     * Method that creates the vaccine type object.
     * Gets the "VaccineTypeStore" first and then uses it to create the object
     * @param code String "code" inputted by the user
     * @param description String "description" inputted by the user
     * @param tech String "tech" inputted by the user
     */
    public void createVaccineType(String code, String description, String tech){
        store = app.getCompany().getVaccineTypeStore();
        vt = store.createVaccineType(code, description, tech);
    }

    /**
     * Saves the new VaccineTypes in VaccineTypeStore
     */
    public void saveVaccineType(){
        store.saveVaccineType(vt);
    }
}
