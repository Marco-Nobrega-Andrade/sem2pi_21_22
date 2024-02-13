package app.domain.model.VaccineSchedule;

import app.domain.model.Time;
import app.domain.model.VaccineType.VaccineType;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class VaccineSchedule implements Serializable {
    private int snsNumber;
    private VaccineType vaccineType;
    private Date date;
    private Time time;

    public VaccineSchedule(int snsNumber, VaccineType vaccineType, Date date, Time time) {
        this.snsNumber = snsNumber;
        this.vaccineType = vaccineType;
        this.date = date;
        this.time = time;
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

    public int checkSnsNumber(int SnsNumber){
        if (SnsNumber==this.snsNumber){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        return String.format("SNS user: %d\nVaccine type: %s\nDate: %d-%d-%d\nTime: %s",snsNumber,vaccineType.getDescription(),calendar1.get(Calendar.DAY_OF_MONTH),calendar1.get(Calendar.MONTH)+1,calendar1.get(Calendar.YEAR),time.toString());
    }
}
