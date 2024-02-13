package app.domain.model.Report;

import app.domain.shared.Constants;
import app.ui.console.DailyReportUI;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class Timer {

    private java.util.Timer timer = new java.util.Timer();
    TimerTask tt;

    /**
     * Create a timer to run the task of creating a report for a specif time defined in the config files
      */
    public void createTimer() {
        Calendar time = Calendar.getInstance();

        Properties props = new Properties();
        try
        {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex) {
        }

        String stringProp = props.getProperty(Constants.PARAMS_REPORT_TIME);
        if(stringProp!= null) {
            String[] arrayTime = (stringProp.split(":"));

            if (arrayTime.length == 2) {
                try {
                    if (Integer.parseInt(arrayTime[0]) < 24 && Integer.parseInt(arrayTime[0]) >= 0 && Integer.parseInt(arrayTime[1]) <= 59 && Integer.parseInt(arrayTime[1]) >= 0) {
                        int hours = Integer.parseInt(arrayTime[0]);
                        int minutes = Integer.parseInt(arrayTime[1]);

                        time.set(Calendar.HOUR_OF_DAY, hours);
                        time.set(Calendar.MINUTE, minutes);
                        time.set(Calendar.SECOND, 0);
                        tt = new TimerTask() {
                            @Override
                            public void run() {
                                new DailyReportUI().run();
                            }
                        };

                        timer.schedule(tt, time.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period: 1 day
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
    }

    public void endTimer(){
        if (tt!=null) {
            tt.cancel();
        }
            timer.cancel();
    }
}
