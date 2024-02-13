package app.domain.model.VaccineSchedule;

import app.domain.model.Time;
import app.domain.model.VaccineType.VaccineType;

import java.util.Calendar;
import java.util.Date;

public class VaccineScheduleDTO {
    private int snsNumber;
    private VaccineType vaccineType;
    private Date date;
    private Time time;

    public VaccineScheduleDTO(int snsNumber, VaccineType vaccineType, Date date, Time time){
        this.snsNumber = snsNumber;
        this.vaccineType = vaccineType;
        this.date = date;
        this.time = time;
    }

    public VaccineScheduleDTO(int snsNumber, VaccineType vaccineType, Date date, String time) {
        this.snsNumber = snsNumber;
        this.vaccineType = vaccineType;
        this.date = date;
        this.time = new Time(time);
    }

    public int getSnsNumber() {
        return snsNumber;
    }

    public VaccineType getVaccineType() {
        return vaccineType;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    /**
     * To String containing all information about the vaccine schedule
     * @return
     */
    @Override
    public String toString() {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        return "Schedule\n" +
                "Sns Number= " + snsNumber +"\n"+
                "VaccineType: " + vaccineType.getDescription() + "\n"+
                "Date: " + c1.get(Calendar.DAY_OF_MONTH) + "-" + (c1.get(Calendar.MONTH)+1) + "-" + c1.get(Calendar.YEAR)+ "\n"+
                "Time: " + time + "\n";
    }
}
