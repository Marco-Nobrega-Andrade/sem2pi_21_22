package app.ui.console;

import app.controller.ActorCenterController;
import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.VaccinationCenter.VaccinationCenterDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ReceptionistUI implements Runnable{
    public ReceptionistUI(){
    }

    @Override
    public void run() {


        ActorCenterController controller =new ActorCenterController();
        try {
            ArrayList<VaccinationCenterDTO> vaccinationCenterDTOS = controller.getActorCenter();
            int opt = 0;
            System.out.println();
            opt = Utils.showAndSelectIndex(vaccinationCenterDTOS, "In which center are you working?");
            do {
                if (opt >= 0 && opt <= vaccinationCenterDTOS.size()) {
                    controller.selectCenter(opt);

                    List<MenuItem> options = new ArrayList<MenuItem>();
                    options.add(new MenuItem("Register the arrival of an  SNS User", new RegisterTheArrivalUI()));
                    options.add(new MenuItem("Register a vaccination", new ReceptionistVaccineScheduleUI()));



                    int option = 0;
                    do {
                        option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

                        if ((option >= 0) && (option < options.size())) {
                            options.get(option).run();
                        }
                    }
                    while (option != -1);

                }
            }
            while (opt < 0 && opt > vaccinationCenterDTOS.size());
        }catch (ListIsEmptyException e){
            System.out.println(e.getMessage());
        }
    }
}
