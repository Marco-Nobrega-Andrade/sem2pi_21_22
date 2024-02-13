package app.domain.model.AnalyzePerformance;

public interface AnalyzePerformanceAlgorithms {
    /**
     * Uses the max Sum Contiguous Subarrays algorithms to find the sublist of the inOutList with maximum sum.
     * @param inOutList list with the difference between people leaving and arriving at a vaccination center in the many time intervals
     * @return sublist of the inOutList with maximum sum.
     */
    int[] getMaxSumList(int[] inOutList);
}
