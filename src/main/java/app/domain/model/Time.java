package app.domain.model;

import java.io.Serializable;
import java.util.Calendar;

public class Time implements Comparable<Time>, Serializable {
    private int hours;
    private int minutes;

    /**
     * Creates object time
     * @param time String in format "hh:mm"
     */
    public Time (String time){
        String [] arrayTime = (time.split(":"));
        checkStringTime(arrayTime);
        this.hours = Integer.parseInt(arrayTime[0]);
        this.minutes = Integer.parseInt(arrayTime[1]);
    }

    /**
     * Creates object time
     * @param hours
     * @param minutes
     */
    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    /**
     * Creates object time based on another object time
     * @param otherTime object time
     */
    public Time (Time otherTime){
        this.hours = otherTime.getHours();
        this.minutes = otherTime.getMinutes();
    }

    /**
     * get object hours
     * @return integer hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * Defines object's hours
     * @param hours integer hours
     */
    public void setHours(int hours) {
        this.hours = hours;
    }
    /**
     * get object minutes
     * @return integer minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Defines object's minutes
     * @param minutes integer minutes
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * Creates string with time's info
     * @return String with all information
     */
    @Override
    public String toString() {
        return String.format("%02d:%02d",this.hours,this.minutes);
    }

    /**
     * Compares two times
     * @param o time to compare
     * @return 1 if it is greater, 0 if it is equal and -1 if it is lesser
     */
    @Override
    public int compareTo(Time o) {
        if (this.isGreaterThan(o)){
            return 1;
        }else if (o.isGreaterThan(this)){
            return -1;
        }
        return 0;
    }

    /**
     * Checks if one time is greater than the other
     * @param otherTime time to compare
     * @return boolean
     */
    public boolean isGreaterThan(Time otherTime) {
        return toMinutes() > otherTime.toMinutes();
    }

    public boolean isLessThan(Time otherTime) {
        return toMinutes() < otherTime.toMinutes();
    }
    /**
     * Checks for correct string time format
     * @param arrayTime string time split in array
     */
    private void checkStringTime(String[] arrayTime) {
        if (Integer.parseInt(arrayTime[0]) >=24 || Integer.parseInt(arrayTime[0]) < 0 || Integer.parseInt(arrayTime[1]) > 59 || Integer.parseInt(arrayTime[1]) < 0 ) {
            throw new IllegalArgumentException("The time should be between 00:00 and 23:59");
        }
        if (arrayTime.length != 2){
            throw new IllegalArgumentException("The time is in the wrong format.");
        }
    }

    /**
     * Convert time to minutes
     * @return integer minutes
     */
    public int toMinutes() {
        return hours * 60 + minutes;
    }

    /**
     * Adds minutes to time object
     * @param minutes integer minutes to add
     * @return object time with minutes added
     */
    public Time addMinutes(int minutes) {
        int numberOfHoursAdd;
        int min = this.getMinutes() + minutes;
        int hrs = this.getHours();
        if (min>=60){
            numberOfHoursAdd = min/60;
            min = min - (numberOfHoursAdd * 60);
            hrs = hrs + numberOfHoursAdd ;
        }
        if (hrs >= 24){
            hrs = hrs - 24;
        }
        return new Time(hrs,min);

    }
    /**
     * Subtracts minutes to time object
     * @param minToSubtract integer minutes to subtract
     * @return object time with minutes subtracted
     */
    public Time subtractMinutes(int minToSubtract){
        int min;
        int hrs = this.getHours();
        if(this.getMinutes() >= minToSubtract){
            min = this.getMinutes() - minToSubtract;
        }else{
            min = 60 - (minToSubtract - this.getMinutes());
            hrs--;
        }
        if(hrs < 0){
            hrs = 24 + hrs;
        }

        return new Time(hrs,min);
    }

    /**
     * Gives the current time
     * @return current time as object time
     */
    public static Time currentTime() {
        Calendar agora = Calendar.getInstance();
        int hour = agora.get(Calendar.HOUR_OF_DAY);
        int minute = agora.get(Calendar.MINUTE);
        return new Time(hour,minute);
    }

    public static Time getClosestTimeInPredefinedTimeIntervals(Time initialTime, Time compareTime, int intervalTime){
        if (compareTime.isGreaterThan(initialTime)) {
            Time predefinedTime = new Time(initialTime);
            while (predefinedTime.isLessThan(compareTime)){
                if (predefinedTime.getHours()== 23 && predefinedTime.addMinutes(intervalTime).getHours() == 0){
                    return predefinedTime.addMinutes(intervalTime);
                }
                predefinedTime = predefinedTime.addMinutes(intervalTime);
            }
            return new Time(predefinedTime);
        }else if (initialTime.isGreaterThan(compareTime)){
            Time predefinedTime = new Time(initialTime);
            while (predefinedTime.isGreaterThan(new Time(0,0))){
                predefinedTime = predefinedTime.addMinutes(intervalTime);
            }
            while (predefinedTime.isLessThan(compareTime)){
                predefinedTime = predefinedTime.addMinutes(intervalTime);
            }
            return new Time(predefinedTime);
        }else{
            return new Time(compareTime);
        }
    }
}
