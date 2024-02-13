package app.domain.model.AnalyzePerformance;

import app.domain.model.Exceptions.ListIsEmptyException;
import app.domain.model.Time;
import app.domain.model.User.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Performance implements Serializable {
    private final int MINUTES_OF_WORK = 720;
    private final Time openingTime = new Time(8, 0);
    private int numberOfTimeIntervals;
    private int[] inOutList;
    private int[] maxSumListIndexes;
    private int[] maxSumList;
    private int timeIntervals;
    private Date date;
    private int maxSum;

    public Performance() {}

    public void setTimeIntervals(int timeIntervals) {
        this.timeIntervals = timeIntervals;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    /**
     * Analyzes the performance of a vaccination center and saves its analysis and inputted data.
     * @param date the date of the desired performance analysis
     * @param timeIntervals is the length of the sections of time divided by the entire working day of the vaccination center
     * @param algorithmUsed name of the algorithm used to analyze the performance of a center
     * @param usersList list with all the users in the system
     * @param vaccinationCenterName name of the analysed vaccination center
     */

    public void analyzePerformance(Date date, int timeIntervals, String algorithmUsed, ArrayList<User> usersList, String vaccinationCenterName) throws ListIsEmptyException {
        numberOfTimeIntervals = MINUTES_OF_WORK / timeIntervals;
        setTimeIntervals(timeIntervals);
        setDate(date);
        inOutList = createInOutList(usersList, vaccinationCenterName);
        checkIfThereIsAnyUserInOutList();
        if(algorithmUsed.equals("Brute Force Algorithm")) {
            BruteForceAlgorithm bruteForceAlgorithm = new BruteForceAlgorithm();
            maxSumList = bruteForceAlgorithm.getMaxSumList(inOutList);
        }else{
            BenchmarkAlgorithm benchmarkAlgorithm = new BenchmarkAlgorithm();
            maxSumList = benchmarkAlgorithm.getMaxSumList(inOutList);
        }
        maxSumListIndexes = findMaxSumListIndexes(inOutList, maxSumList);
        maxSum = calculateMaxSumOfList(maxSumList);

    }

    /**
     * Creates a list with the difference between people leaving and arriving at a vaccination center in the many time intervals
     * @param usersList list with all the users in the system
     * @param vaccinationCenterName name of the analysed vaccination center
     */

    public int[] createInOutList(ArrayList<User> usersList, String vaccinationCenterName){
        int arrivals;
        int departures;
        int[] inOutList = new int[numberOfTimeIntervals];
        for (int i = 0; i < numberOfTimeIntervals; i++) {
            arrivals = 0;
            departures = 0;
            for (User user : usersList) {
                if (user.checkIfUserArrivedAtCenter(date, openingTime.addMinutes(i * timeIntervals), timeIntervals, vaccinationCenterName)) {
                    arrivals++;
                }
                if (user.checkIfUserLeftTheCenter(date, openingTime.addMinutes(i * timeIntervals), timeIntervals, vaccinationCenterName)) {
                    departures++;
                }
            }
            inOutList[i] = arrivals - departures;
        }
        return inOutList;
    }

    /**
     * Finds the indexes of the start and the end of the array maxSumList in the inOutList
     * @param inOutList list with the difference between people leaving and arriving at a vaccination center in the many time intervals
     * @param maxSumList the max sum sublist
     * @return Array with the indexes of the start and the end of the array maxSumList in the inOutList
     */

    public int[] findMaxSumListIndexes(int[] inOutList, int[] maxSumList){
        int j = 0;
        int counter = 0;
        int maxSumStart = -1;
        int [] maxSumListIndexes = new int[2];
        for (int i = 0; i < inOutList.length; i++) {
            if(inOutList[i] == maxSumList[j]) {
                j++;
                counter++;
                if(counter == 1)
                    maxSumStart = i;

                if(counter == maxSumList.length){
                    maxSumListIndexes[0] = maxSumStart;
                    maxSumListIndexes[1] = i;
                    return maxSumListIndexes;
                }
            }else{
                j = 0;
                counter = 0;
                maxSumStart = -1;
            }
        }
        maxSumListIndexes[0] = -1;
        maxSumListIndexes[1] = -1;
        return maxSumListIndexes;
    }

    /**
     * Calculates the maximum sum of the maximum sum list
     * @param maxSumList the max sum sublist
     * @return max sum of the inOutList and maxSumList
     * */

    public int calculateMaxSumOfList(int[] maxSumList) {
        int sum = 0;
        for (int j : maxSumList) {
            sum += j;
        }
        return sum;
    }

    /**
     * Converts back indexes of the inOutList to its equivalent time
     * @param index index of the inOutList
     * @return The time equivalent of the index in the inOutList
     */

    public Time convertIndexesToTime(int index){
        return openingTime.addMinutes(index * timeIntervals);
    }
    /**
     * Gets all the information about the performance of a center that the actor might want.
     * @return String with all the information about the performance of a center that the actor might want.
     */


    public String getPerformanceInfo(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.format(" %s\n%s \n\n%s\n%s \n\n%s%d \n\n%s%d%s%d%s%d%s%s%s%d%s%d%s%d%s%s%s"
                ,"Input List:", Arrays.toString(inOutList)
                ,"Max sum sublist of output:",Arrays.toString(maxSumList)
                , "Sum: ",maxSum
                , "Time interval: [",calendar.get(Calendar.DAY_OF_MONTH), "/", (calendar.get(Calendar.MONTH)+1), "/", calendar.get(Calendar.YEAR), " "
                , convertIndexesToTime(maxSumListIndexes[0]), ", "
                ,calendar.get(Calendar.DAY_OF_MONTH), "/", (calendar.get(Calendar.MONTH)+1), "/", calendar.get(Calendar.YEAR) , " "
                , convertIndexesToTime(maxSumListIndexes[1]+1), "]");
    }

    /**
     * Checks if at least one person arrived or left the center in the chosen date
     * @throws ListIsEmptyException there were no people arriving or leaving the center in the chosen date
     */

    public void checkIfThereIsAnyUserInOutList() throws ListIsEmptyException {
        boolean flag = false;
        for (int i = 0; i < inOutList.length; i++) {
            if(inOutList[i] != 0){
                flag = true;
            }
        }
        if(!flag)
            throw new ListIsEmptyException("InOutList");

    }
}
