package app.ui.console;

import app.controller.RegisterTheArrivalController;
import app.domain.model.Exceptions.MissingUserException;
import app.domain.model.Exceptions.NoScheduleAppointmentForSnsNumberException;
import app.domain.model.Exceptions.UserIsAlreadyInTheWaitingListException;
import app.domain.model.VaccineSchedule.VaccineScheduleDTO;
import app.ui.console.utils.Utils;

public class RegisterTheArrivalUI implements  Runnable{


    @Override
    public void run() {

        RegisterTheArrivalController controller = new RegisterTheArrivalController();
        String snsNumber;
        VaccineScheduleDTO dtoSchedule;
        do{
            snsNumber = Utils.readLineFromConsole("Type the SNS Number:");
            try{
                Integer.parseInt(snsNumber);
            }catch (NumberFormatException e){
                System.out.println("The input must only be numbers.");
            }
            if (snsNumber.length()!=8){
                System.out.println("The SNS Number must have 8 digits.");
            }
            System.out.println();
        }while(snsNumber.length()!=9);

        try {
            dtoSchedule = controller.checkAppointmentList(Integer.parseInt(snsNumber));
            System.out.println(dtoSchedule);
            if (Utils.confirm("Confirms the appointment? \n Confirm?(s/n)")){
                try{
                    controller.addWaitingUser(Integer.parseInt(snsNumber));
                    System.out.println();
                    System.out.println("The arrival of the SNS user was successfully registered.");
                }catch (IllegalArgumentException | UserIsAlreadyInTheWaitingListException e){
                    System.out.println(e.getMessage());
                }
            }
        } catch (NoScheduleAppointmentForSnsNumberException | MissingUserException e) {
            System.out.println(e.getMessage());;
        }

    }
}
