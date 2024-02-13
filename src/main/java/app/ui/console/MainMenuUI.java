package app.ui.console;

import app.controller.SerializationController;
import app.controller.TimerController;
import app.domain.model.Report.Timer;
import app.ui.console.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class MainMenuUI {

    public MainMenuUI()
    {
    }

    public void run() throws IOException
    {

        SerializationController ctrlSeri = new SerializationController();
        if (Utils.confirm("Do you want to import saved data? (s/n)")){
            if(ctrlSeri.readFile()){
                System.out.println("Operation Succeed");
            }else{
                System.out.println("Operation failed");
            }
        }

        TimerController ctrlTimer = new TimerController();
        Timer timer = ctrlTimer.createTimer();

        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Do Login", new AuthUI()));
        options.add(new MenuItem("Know the Development Team",new DevTeamUI()));
        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );

        ctrlTimer.cancelTimer(timer);

        if (Utils.confirm("Do you want to save the data? (s/n)")){
            if(ctrlSeri.writeFile()){
                System.out.println("Operation Succeed");
            }else{
                System.out.println("Operation failed");
            }
        }
    }


}
