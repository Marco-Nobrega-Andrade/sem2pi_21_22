package app.domain.model.VaccineAdministration;

import app.domain.model.Exceptions.*;
import app.domain.model.Time;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.VaccineDTO;
import app.domain.model.Vaccine.VaccineMapper;
import app.domain.model.VaccineType.VaccineTypeDTO;
import app.domain.shared.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class VaccineAdministrationStore implements Serializable {

    private ArrayList<VaccineAdministration> vaccineAdministrationsList = new ArrayList<>();
    private VaccineAdministration administration;

    /**
     * This method return a VaccineDTO of the vaccine list searching by type
     *
     * @param vaccineTypeDTO VaccineTypeDTO the vaccine type that wants to be searched on the vaccine list
     * @return VaccineDTO vaccine searched by type
     * @throws NotVaccineForThatVaccineTypeException if there's not a vaccine for that type saved on the system
     */

    public VaccineDTO getVaccineByType(VaccineTypeDTO vaccineTypeDTO) throws NotVaccineForThatVaccineTypeException {
        VaccineMapper vaccineMapper = new VaccineMapper();
        for (VaccineAdministration administration : vaccineAdministrationsList) {
            if (administration.getVaccine().getVaccineType() == vaccineTypeDTO) {
                return vaccineMapper.toDTO(administration.getVaccine());
            }
        }
        throw new NotVaccineForThatVaccineTypeException();
    }

    /**
     * This method return the next dose to be administered to the user
     *
     * @param vaccineType VaccineTypeDTO
     * @return int represent the dose that would be administered on the user
     * @throws NotPreviousDoseForThatVaccineException if there's no previous dose
     */

    public int getNextDose(VaccineTypeDTO vaccineType) throws NotPreviousDoseForThatVaccineException {
        int next = 0;
        for (VaccineAdministration administration : vaccineAdministrationsList) {
            if (administration.getVaccine().getVaccineType() == vaccineType) {
                next = administration.getDoseNumber() + 1;
            }
        }
        if (next == 0) {
            throw new NotPreviousDoseForThatVaccineException();
        }
        return next;
    }

    /**
     * @param age         int that represent the age group number on the number of age groups
     * @param dose        int that represent the dose number on the number of doses
     * @param vaccineType VaccineTypeDTO
     * @param vaccineDTO  VaccineDTO
     * @return double that represents the dosage to be administered on the user
     * @throws NotDosageWasFoundedForThatVaccineException if there's no dosage founded
     */

    public double getNextDosage(int age, int dose, VaccineTypeDTO vaccineType, VaccineDTO vaccineDTO) throws NotDosageWasFoundedForThatVaccineException {

        ArrayList<Double> dosageList;

        dosageList = vaccineDTO.getDosageList();
        int j = ((dose - 1) * 2) + (age - 1);

        return dosageList.get(j);
    }

    /**
     * @param vaccineType VaccineTypeDTO
     * @return boolean, true if the list of vaccine administration is not empty, false if is empty.
     */

    public boolean verifyIfExistVaccinationRecord(VaccineTypeDTO vaccineType) {
        if (!vaccineAdministrationsList.isEmpty()) {
            for (VaccineAdministration administration : vaccineAdministrationsList) {
                if (administration.getVaccine().getVaccineType() == vaccineType) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param start
     * @param end
     * @param center
     * @return
     */

    public Date verifyIfIsFullyVaccinatedOnInterval(Date start, Date end, VaccinationCenter center) {
        VaccineAdministration lastAdministration = null;
        boolean flag = false;
        for (VaccineAdministration administration : vaccineAdministrationsList) {
            if (administration.getDoseNumber() == administration.getVaccine().getNumberOfDoses()) {
                flag = true;
                lastAdministration = administration;
            }
        }

        if (flag) {
            if (lastAdministration.getAdministrationDate().after(start)
                    && lastAdministration.getAdministrationDate().before(end)
                    && lastAdministration.getVaccinationCenter().equals(center)) {
                return lastAdministration.getAdministrationDate();
            }
        }
        return null;
    }

    /**
     * @param vaccineAdministrationDTO VaccineAdministrationDTO a data transfer object that have all the parameter necessaries to create a vaccine administration object
     * @return VaccineAdministration an object that represent the information of the process of administration a vaccine on a user
     */

    public VaccineAdministration createNewVaccineAdministration(VaccineAdministrationDTO vaccineAdministrationDTO) {

        VaccineAdministrationMapper vaccineAdministrationMapper = new VaccineAdministrationMapper();
        return vaccineAdministrationMapper.toModel(vaccineAdministrationDTO);

    }

    /**
     * @param vaccineAdministration VaccineAdministration, object to be added on the list of vaccine administrations
     */

    public void saveNewVaccineAdministration(VaccineAdministration vaccineAdministration) {
        vaccineAdministrationsList.add(vaccineAdministration);
    }

    /**
     * Checks if the user arrived at the center in the right time interval
     *
     * @param date          the date of the desired performance analysis
     * @param time          starting time of the time interval
     * @param timeIntervals is the length of the sections of time divided by the entire working day of the vaccination center
     * @param vaccinationCenterName          name of the vaccination center that is being checked
     * @return returns a boolean, is positive if the user arrived that center at that time interval and date
     */

    public boolean checkIfUserArrivedAtCenter(Date date, Time time, int timeIntervals, String vaccinationCenterName) {
        Time timePlusIntervals = time.addMinutes(timeIntervals);
        for (VaccineAdministration administration : vaccineAdministrationsList) {
            if (vaccinationCenterName.equals(administration.getVaccinationCenter().getName())) {
                if (date.getYear() == administration.getVaccineScheduleDate().getYear() && date.getMonth() == administration.getVaccineScheduleDate().getMonth() && date.getDate() == administration.getVaccineScheduleDate().getDate()) {
                    if (time.compareTo(administration.getArrivalTime()) <= 0 && timePlusIntervals.compareTo(administration.getArrivalTime()) > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if the user left the center in the right time interval
     *
     * @param date the date of the desired performance analysis
     * @param time starting time of the time interval
     * @param timeIntervals is the length of the sections of time divided by the entire working day of the vaccination center
     * @param vaccinationCenterName name of the vaccination center that is being checked
     * @return returns a boolean, is positive if the user left the center at that time interval and date
     */

    public boolean checkIfUserLeftTheCenter(Date date, Time time, int timeIntervals, String vaccinationCenterName) {
        Time timePlusIntervals = time.addMinutes(timeIntervals);
        for (VaccineAdministration administration : vaccineAdministrationsList) {
            if (vaccinationCenterName.equals(administration.getVaccinationCenter().getName())) {
                if (date.getYear() == administration.getLeavingDate().getYear() && date.getMonth() == administration.getLeavingDate().getMonth() && date.getDate() == administration.getLeavingDate().getDate()) {
                    if (time.compareTo(administration.getLeavingTime()) <= 0 && timePlusIntervals.compareTo(administration.getLeavingTime()) > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean verifyLotNumber(String lotNumber) throws MissingCorrectFormatToLotNumber {
        String[] lotNumberSplit = lotNumber.split("-");
        if (lotNumberSplit.length != 2) {
            throw new MissingCorrectFormatToLotNumber("Lot number must be separated by a dash (\"-\")");
        }
        if (lotNumberSplit[0].length() != 5 || lotNumberSplit[1].length() != 2) {
            throw new MissingCorrectFormatToLotNumber();
        }
        if (!checkForNonAlphanumericChars(lotNumberSplit[0]) || !checkForNonNumericCharacters(lotNumberSplit[1])) {
            throw new MissingCorrectFormatToLotNumber();
        }
        return true;
    }

    /**
     * Method that checks if a string is composed by only numeric characters
     *
     * @param string String inputted by the user
     * @return false if there is a non-numeric character, true otherwise
     */
    private boolean checkForNonNumericCharacters(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (!checkIfCharIsNumber(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that checks if a character belongs to the "Numbers" constant
     *
     * @param charAt character from a string
     * @return true if it is a number, false otherwise
     */
    private boolean checkIfCharIsNumber(char charAt) {
        for (int i = 0; i < Constants.NUMBERS.length(); i++) {
            if (Constants.NUMBERS.charAt(i) == charAt) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that checks if the String "code" is composed by only alphanumeric characters
     *
     * @param string String inputted by the user
     * @return false if there is a non-alphanumeric character, true otherwise
     */
    private boolean checkForNonAlphanumericChars(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (!checkIfCharIsAlphanumeric(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that checks if a character belongs to the "AlphanumericCharacters" constant
     *
     * @param charAt character from the String "code"
     * @return true if it is an alphanumeric character, false otherwise
     */
    private boolean checkIfCharIsAlphanumeric(char charAt) {
        for (int i = 0; i < Constants.ALPHANUMERIC_CHARS.length(); i++) {
            if (Constants.ALPHANUMERIC_CHARS.charAt(i) == charAt) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check if the user was vaccinated in a specific date and center
     *
     * @param center vaccination center
     * @param today  Day in which this method was called
     * @return true if he was vaccined and false if it isn't the case
     */
    public int checkIfUserWasVaccinatedToday(VaccinationCenter center, Calendar today) {
        int contador = 0;
        Calendar vaccineAdminnistrationDate = Calendar.getInstance();
        for (VaccineAdministration vaccineAdministration : this.vaccineAdministrationsList) {
            vaccineAdminnistrationDate.setTime(vaccineAdministration.getAdministrationDate());
            VaccinationCenter vaccinationCenter = vaccineAdministration.getVaccinationCenter();
            if (center.equalsVaccinationCenters(vaccinationCenter)) {
                if (today.get(Calendar.YEAR) == vaccineAdminnistrationDate.get(Calendar.YEAR) && today.get(Calendar.MONTH) == vaccineAdminnistrationDate.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) == vaccineAdminnistrationDate.get(Calendar.DAY_OF_MONTH)) {
                    contador++;
                }
            }
        }
        return contador;
    }

    /**
     * @param vaccineAdministration
     * @throws DuplicateVaccineAdministrationException
     */

    public void checkForDuplicateAdministration(VaccineAdministration vaccineAdministration) throws DuplicateVaccineAdministrationException {
        for (VaccineAdministration va : vaccineAdministrationsList) {
            if (vaccineAdministration.equals(va)) {
                throw new DuplicateVaccineAdministrationException();
            }
        }
    }

}
