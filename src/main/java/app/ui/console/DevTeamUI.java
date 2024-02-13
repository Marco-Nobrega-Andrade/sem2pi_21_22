package app.ui.console;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class DevTeamUI implements Runnable{

    public DevTeamUI()
    {

    }
    public void run()
    {
        System.out.printf("\n");
        System.out.printf("Development Team:\n");
        System.out.printf("\t Gonçalo Ramalho - 1190610@isep.ipp.pt \n");
        System.out.printf("\t João Batista    - 1211396@isep.ipp.pt \n");
        System.out.printf("\t David Dias      - 1211415@isep.ipp.pt \n");
        System.out.printf("\t Ezequiel Estima - 1211417@isep.ipp.pt \n");
        System.out.printf("\t Marco Andrade   - 1211469@isep.ipp.pt \n");
    }
}
