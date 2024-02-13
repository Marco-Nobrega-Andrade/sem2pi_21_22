package app.ui.console;

import app.controller.DefineOngoingOutbreakController;
import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.VaccineType.VaccineTypeDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Objects;

public class DefineOngoingOutbreakUI implements Runnable{
    public DefineOngoingOutbreakUI(){}

    @Override
    public void run() {
        DefineOngoingOutbreakController controller = new DefineOngoingOutbreakController();
        ArrayList<VaccineTypeDTO> vaccineTypeListDto = null;
        boolean flag = false;

        try {
            vaccineTypeListDto = controller.getVaccineTypeList();
            System.out.println("Choose a Vaccine Type.");
            System.out.println("(Choose the option number)");
            for (int i = 0; i < vaccineTypeListDto.size(); i++) {
                System.out.print(i + 1 + ". ");
                System.out.println(vaccineTypeListDto.get(i).toString());
            }
            int index = -1;

            do {
                try {
                    index = Integer.parseInt(Objects.requireNonNull(Utils.readLineFromConsole("Option"))) - 1 ;
                    controller.setVaccineTypeOutbreak(index);
                    flag = false;
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println(e.getMessage());
                    flag = true;
                }catch (NumberFormatException e){
                    System.out.println("You need to choose an option.");
                    flag = true;
                }
            }while (flag);

            boolean confirmQuestion = Utils.confirm("Are you sure you want to define " + controller.getVaccineTypeFromStore(index).getDescription() + " as your vaccine vaccine type for the outbreak disease (s/n)");
            if(confirmQuestion){
                System.out.println("The vaccine type for the ongoing outbreak was defined.");
            }else{
                System.out.println("The vaccine type for the ongoing outbreak was not defined.");
            }
        } catch (ListIsEmptyException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("You need to choose an option");
        }
    }
}
