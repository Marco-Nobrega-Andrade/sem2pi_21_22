package app.domain.model.AnalyzePerformance;

import java.io.Serializable;

public class BruteForceAlgorithm implements AnalyzePerformanceAlgorithms, Serializable {

    /**
     * Uses the brute force algorithm to find the sublist of the inOutList with maximum sum
     @param inOutList list with the difference between people leaving and arriving at a vaccination center in the many time intervals
     @return sublist of the inOutList with maximum sum.
     */
    @Override
    public int[] getMaxSumList(int[] inOutList) {
        int maxSum = inOutList[0];
        int maxStart = 0;
        int maxEnd = 0;
        for(int i = 0; i < inOutList.length; i++){
            int currSum = 0;
            for(int j = i; j < inOutList.length; j++){
                currSum += inOutList[j];
                if(maxSum < currSum){
                    maxSum = currSum;
                    maxStart = i;
                    maxEnd = j;
                }
            }
        }

        int [] maxSumList = new int[maxEnd-maxStart +1];
        for (int k = 0; k < maxSumList.length; k++)
            maxSumList[k] = inOutList[maxStart + k];

        return maxSumList;
    }
}