package app.domain.model.VaccineSchedule;

import app.domain.model.Time;
import app.domain.model.VaccineType.VaccineType;

import java.util.Date;

public class VaccineScheduleMapper {

    /**
     * Receives a VaccineScheduleDTO and returns a VaccineSchedule
     * @param scheduleDTO
     * @return VaccineSchedule
     */

    public VaccineSchedule toModel(VaccineScheduleDTO scheduleDTO){
        int snsNumber = scheduleDTO.getSnsNumber();
        VaccineType vaccineType = scheduleDTO.getVaccineType();
        Date date = scheduleDTO.getDate();
        Time time = scheduleDTO.getTime();

        return new VaccineSchedule(snsNumber, vaccineType, date , time);
    }
}
