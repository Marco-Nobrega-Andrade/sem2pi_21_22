package app.controller;

import app.domain.model.Exceptions.NoScheduleAppointmentForSnsNumberException;
import app.domain.model.Exceptions.UserIsAlreadyInTheWaitingListException;
import app.domain.model.User.UserStore;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccineSchedule.VaccineScheduleDTO;
import app.domain.model.VaccineSchedule.VaccineScheduleStore;
import app.domain.model.WaitingUser.WaitingUserStore;

public class RegisterTheArrivalController {

    private App app;
    private VaccinationCenter actorCenter;

    /**
     * Instance the controller and get app and actorCenter
     */
    public RegisterTheArrivalController() {
        this.app=  App.getInstance();
        this.actorCenter = app.getCompany().getActorCenter();
    }

    /**
     * Check if in the actorCenter there is an appointment for today with a determinate snsNumber
     * If there isn't throws an error, else return a VaccineScheduleDTO: dtoSchedule
     * @param snsNumber - SNS Number inputted by the receptionist
     * @return dtoSchedule - VaccineScheduleDTO with the information about the appointment
     * @throws NoScheduleAppointmentForSnsNumberException
     */
    public VaccineScheduleDTO checkAppointmentList(int snsNumber) throws NoScheduleAppointmentForSnsNumberException {
        VaccineScheduleStore vaccineScheduleStore = this.actorCenter.getVaccineScheduleStore();
        VaccineScheduleDTO dtoSchedule = vaccineScheduleStore.checkIfThereIsAnAppointment(snsNumber);
        if (dtoSchedule == null){
            throw new NoScheduleAppointmentForSnsNumberException();
        }
        return  dtoSchedule;
    }

    /**
     * Get userStore and calls for the method to createWaitingUser
     * @param snsNumber - SNS Number inputted by the receptionist
     * @throws UserIsAlreadyInTheWaitingListException
     */
    public void addWaitingUser(int snsNumber) throws UserIsAlreadyInTheWaitingListException {

        UserStore userStore = app.getCompany().getUserStore();
        WaitingUserStore storeWaitingUser= this.actorCenter.getWaitingUserStore();
        storeWaitingUser.createWaitingUser(snsNumber,userStore);
    }

}
