package app.controller;


import app.domain.model.Report.Timer;

public class TimerController {

    /**
     * Create a Timer object
     * @return Timer object
     */
    public Timer createTimer(){
        Timer timer = new Timer();
        timer.createTimer();
        return timer;
    }

    /**
     * Cancel the timer
     * @param timer Timer object
     */
    public void cancelTimer (Timer timer){
        timer.endTimer();

    }

}
