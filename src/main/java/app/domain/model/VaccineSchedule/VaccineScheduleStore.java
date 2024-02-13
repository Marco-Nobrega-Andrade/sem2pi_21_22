package app.domain.model.VaccineSchedule;

import app.domain.model.Exceptions.ScheduleAtTheSameTimeException;
import app.domain.model.Exceptions.ScheduleForThisTimeIsFullException;
import app.domain.model.Exceptions.TimeNotPredefinedException;
import app.domain.model.Exceptions.TimeOutOfBoundsException;
import app.domain.model.Time;
import app.domain.model.VaccineType.VaccineType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static app.domain.model.Time.getClosestTimeInPredefinedTimeIntervals;

public class VaccineScheduleStore implements Serializable {
    private ArrayList<VaccineSchedule> vaccineSchedulesList = new ArrayList<>();
    private Time openingHours;
    private Time closingHours;
    private int slotDuration;
    private int maximumNumVaccinesSlot;

    public void setOpeningHours(Time openingHours) {
        this.openingHours = openingHours;
    }

    public void setClosingHours(Time closingHours) {
        this.closingHours = closingHours;
    }

    public void setSlotDuration(int slotDuration) {
        this.slotDuration = slotDuration;
    }

    public void setMaximumNumVaccinesSlot(int maximumNumVaccinesSlot) {
        this.maximumNumVaccinesSlot = maximumNumVaccinesSlot;
    }

    /**
     * creates vaccine schedule based on all inputs given by the user
     * @param scheduleDTO the DTO containing all the information needed to schedule a vaccination
     */

    public VaccineSchedule createVaccineSchedule (VaccineScheduleDTO scheduleDTO){
        VaccineScheduleMapper mapper = new VaccineScheduleMapper();
        return (mapper.toModel(scheduleDTO));
    }


    public ArrayList<VaccineSchedule> getVaccineSchedulesList() {
        return new ArrayList<>(vaccineSchedulesList);
    }

    /**
     * Checks if the center scheduled all the vaccines available for that day
     * @param newVaccineSchedule
     * @throws ScheduleForThisTimeIsFullException cannot schedule more than the capacity of the vaccination center
     */

    public void checkForPredefinedHours(VaccineSchedule newVaccineSchedule) throws TimeNotPredefinedException {
        Time scheduleTime = newVaccineSchedule.getTime();
        Time predefinedTime = getClosestTimeInPredefinedTimeIntervals(openingHours,scheduleTime,slotDuration);
        if (scheduleTime.compareTo(predefinedTime) != 0){
            Time closestTimeGreater = getClosestTimeInPredefinedTimeIntervals(openingHours,scheduleTime,slotDuration);
            Time closestTimeLess = closestTimeGreater.subtractMinutes(slotDuration);
            throw new TimeNotPredefinedException("This time is not valid. The closest valid times are "+ closestTimeLess.toString()+" and "+closestTimeGreater.toString());
        }
    }

    /**
     * Checks if the actor is scheduling a vaccination at the same time as other scheduled vaccination
     * @param newVaccineSchedule
     * @throws ScheduleAtTheSameTimeException Cannot schedule at the same time as other vaccination
     */

    public void checkForSchedulesPassedMaximumCapacity(VaccineSchedule newVaccineSchedule) throws ScheduleForThisTimeIsFullException {
        int counter = 0;
        for (VaccineSchedule vaccineSchedule : vaccineSchedulesList ){
            Time vaccineScheduleTime = vaccineSchedule.getTime();
            if(vaccineScheduleTime == newVaccineSchedule.getTime()){
                counter++;
            }
            if(counter == maximumNumVaccinesSlot){
                throw new ScheduleForThisTimeIsFullException();
            }
        }
    }

    /**
     * Check if the actor is scheduling a vaccination before the current time
     * @param vaccineSchedule
     * @throws TimeOutOfBoundsException Cannot schedule before the current time
     */

    public void checkForSchedulesBeforeNow(VaccineSchedule vaccineSchedule) throws TimeOutOfBoundsException {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        Calendar vaccineDate = Calendar.getInstance();
        vaccineDate.setTime(vaccineSchedule.getDate());
        if(today.get(Calendar.YEAR) == vaccineDate.get(Calendar.YEAR) && today.get(Calendar.MONTH) == vaccineDate.get(Calendar.MONTH)  && today.get(Calendar.DAY_OF_MONTH) ==vaccineDate.get(Calendar.DAY_OF_MONTH)) {
            Time time = Time.currentTime();
            if(vaccineSchedule.getTime().compareTo(time) != 1){
                throw new TimeOutOfBoundsException("You cannot schedule a vaccination at this time.");
            }
        }else if(today.after(vaccineDate)){
            throw new TimeOutOfBoundsException("Cannot schedule a vaccination a day before today.");
        }

    }

    /**
     * Checks if the center is out of the working hours
     * @param newVaccineSchedule
     * @throws TimeOutOfBoundsException the time inputted by there user is before the opening hours, after the closing hours minus the slot duration (because the center cannot give vaccinations right before the closing of the center).
     */

    public void checkForSchedulesOutOfCenterWorkingHours(VaccineSchedule newVaccineSchedule) throws TimeOutOfBoundsException {
        if (closingHours.isGreaterThan(openingHours)){
            if ((newVaccineSchedule.getTime().compareTo(openingHours) < 0 && newVaccineSchedule.getTime().compareTo(closingHours) <= 0) ||
                    (newVaccineSchedule.getTime().compareTo(openingHours) > 0 && newVaccineSchedule.getTime().compareTo(closingHours) >= 0)){
                throw new TimeOutOfBoundsException("The vaccination center isn't open at that time.");
            }else if ((newVaccineSchedule.getTime().compareTo(openingHours) < 0 && newVaccineSchedule.getTime().compareTo(closingHours.subtractMinutes(slotDuration)) <= 0) ||
                    (newVaccineSchedule.getTime().compareTo(openingHours) > 0 && newVaccineSchedule.getTime().compareTo(closingHours.subtractMinutes(slotDuration)) >= 0)){
                throw new TimeOutOfBoundsException("There is no time to administer the vaccine. The vaccination center will close soon after that.");
            }
        }else if(openingHours.isGreaterThan(closingHours)){
            if (newVaccineSchedule.getTime().compareTo(closingHours) > 0 && newVaccineSchedule.getTime().compareTo(openingHours) <= 0){
                throw new TimeOutOfBoundsException("The vaccination center isn't open at that time.");
            }else if (newVaccineSchedule.getTime().compareTo(closingHours.subtractMinutes(slotDuration)) > 0 && newVaccineSchedule.getTime().compareTo(openingHours) <= 0){
                throw new TimeOutOfBoundsException("There is no time to administer the vaccine. The vaccination center will close soon after that.");
            }
        }
    }

    /**
     * Checks if there is an appointment for today with a snsNumber and return a vaccineScheduleDTO
     * If vaccineScheduleDTO = null there isn't an appointment
     * @param SnsNumber
     * @return vaccineScheduleDTO
     */
    public VaccineScheduleDTO checkIfThereIsAnAppointment(int SnsNumber){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        Calendar vaccineDate = Calendar.getInstance();
        VaccineScheduleDTO vaccineScheduleDTO = null;
        for(VaccineSchedule  vaccineSchedule :vaccineSchedulesList){
            if (vaccineSchedule.checkSnsNumber(SnsNumber)==1){
                vaccineDate.setTime(vaccineSchedule.getDate());
                if(today.get(Calendar.YEAR) == vaccineDate.get(Calendar.YEAR) && today.get(Calendar.MONTH) == vaccineDate.get(Calendar.MONTH)  && today.get(Calendar.DAY_OF_MONTH) ==vaccineDate.get(Calendar.DAY_OF_MONTH)){
                    int snsNumber = vaccineSchedule.getSnsNumber();
                    VaccineType vaccineType = vaccineSchedule.getVaccineType();
                    Date date = vaccineSchedule.getDate();
                    Time time = vaccineSchedule.getTime();
                    vaccineScheduleDTO = new VaccineScheduleDTO(snsNumber,vaccineType,date,time);
                }
            }
        }
        return vaccineScheduleDTO;
    }

    /**
     * Adds the vaccineSchedule to the list of vaccine schedules
     * @param vaccineSchedule
    */
    public void addVaccineSchedule(VaccineSchedule vaccineSchedule) {
        vaccineSchedulesList.add(vaccineSchedule);
    }
}
