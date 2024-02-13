package app.ui.console;

import app.controller.DailyReportController;
import app.domain.model.Exceptions.ListIsEmptyException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class DailyReportUI implements  Runnable{
    @Override
    public void run() {
        DailyReportController ctrl = new DailyReportController();

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);

        try {
            File file = new File("Report.txt");
            FileWriter fw = new FileWriter(file, true);
            try {
                ArrayList<String> listOfInformation = ctrl.createReport(today);
                if (listOfInformation !=null) {
                    for (String information : listOfInformation) {
                        fw.append(information).append("\n");
                    }
                }
                fw.append("\n");

            } catch (ListIsEmptyException e) {
                try {
                    fw.append(e.getMessage());
                    fw.append("\n");
                } catch (IOException ignored) {
                }
            } catch (IOException ignored) {
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                fw.append("The parameter Type.Report in the config file is wrong\n");
            }
            fw.close();
        } catch (IOException ignored) {
        }

    }
}
