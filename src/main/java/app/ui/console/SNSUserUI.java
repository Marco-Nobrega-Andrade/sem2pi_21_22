package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SNSUserUI implements Runnable {
    public SNSUserUI() {

    }
    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Schedule a vaccine.", new SNSUserVaccineScheduleUI()));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nSNS User Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
