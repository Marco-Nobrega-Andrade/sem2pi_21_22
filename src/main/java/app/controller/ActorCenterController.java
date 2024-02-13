package app.controller;

import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenterDTO;
import app.domain.model.VaccinationCenter.VaccinationCenterMapper;
import app.domain.model.VaccinationCenter.VaccinationCenterStore;

import java.util.ArrayList;

public class ActorCenterController {

    private App app;
    private VaccinationCenterStore vaccinationCenterStore;

    public ActorCenterController(){
        this.app= App.getInstance();
        this.vaccinationCenterStore = app.getCompany().getVaccinationCenterStore();
    }
    public ArrayList<VaccinationCenterDTO> getActorCenter() throws ListIsEmptyException {
        VaccinationCenterMapper vcm = new VaccinationCenterMapper();
        ArrayList <VaccinationCenter> vaccinationCenterList =this.vaccinationCenterStore.getAllVaccinationCenters();
        if(vaccinationCenterList.isEmpty()){
            throw new ListIsEmptyException("There is no vaccination centre registered on the system.");
        }

        return vcm.toDTO(vaccinationCenterList);
    }

    public void selectCenter(int opcao){
        app.getCompany().setActorCenter(vaccinationCenterStore.getVaccinationCenterFromStore(opcao,true));
    }

    public void selectCenter(VaccinationCenterDTO actorCenterDTO){
        String phoneNumber = actorCenterDTO.getPhoneNumber();
        app.getCompany().setActorCenter(vaccinationCenterStore.getVaccinationCenterFromStore(phoneNumber));
    }
}
