package app.controller;

import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.WaitingUser.WaitingUserStore;
import app.domain.model.WaitingUser.WaitingUserDTO;

import java.util.ArrayList;

public class ShowWaitingListController {
    private App app;
    private VaccinationCenter center;

    /**
     * This method gets the Waiting User Store and request the Store to check the existence of a Waiting Users List, if it exists it returns the List
     * @return waitingUsersListDTO
     * @throws ListIsEmptyException
     */

    public ArrayList<WaitingUserDTO> checkWaitingUserList() throws ListIsEmptyException {
        this.app=  App.getInstance();
        this.center = app.getCompany().getActorCenter();
        WaitingUserStore waitingUserStore = center.getWaitingUserStore();
        return waitingUserStore.checkWaitingUserList();
    }
}
