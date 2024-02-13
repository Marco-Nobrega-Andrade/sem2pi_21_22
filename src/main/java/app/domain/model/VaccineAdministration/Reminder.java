package app.domain.model.VaccineAdministration;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Simple demo that uses java.util.Timer to schedule a task 
 * to execute once seconds have passed.
 */

public class Reminder {
    Timer timer;

    public Reminder(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
    }

    class RemindTask extends TimerTask {

        public void run() {

            try {

                File file = new File("LeavingNotification.txt");
                FileWriter fw = new FileWriter(file, true);
                fw.append("You can leave now\n");
                fw.close();
            }catch(IOException e){}
            timer.cancel(); //Terminate the timer thread
        }
    }
}