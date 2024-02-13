package app.ui.console;

import app.controller.ActorCenterController;
import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.VaccinationCenter.VaccinationCenterDTO;
import app.ui.console.utils.Utils;
import app.ui.gui.VaccineAdministration.TestStartVaccineAdministrationGUI;

import java.util.ArrayList;
import java.util.List;

    public class NurseUI implements Runnable{
        public NurseUI(){
        }

        /**
         * This method allow nurse users to access to their menu options and specify what center their work
         */

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
                        options.add(new MenuItem("See SNS Users in the Waiting List", new ShowWaitingListUI()));
                        options.add(new MenuItem("Manage vaccine to a SNS User", new TestStartVaccineAdministrationGUI()));

                        int option = 0;
                        do {
                            option = Utils.showAndSelectIndex(options, "\n\nNurse Menu:");

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
