package app.domain.model.AnalyzePerformance;

import com.isep.mdis.Sum;

import java.io.Serializable;

public class BenchmarkAlgorithm implements AnalyzePerformanceAlgorithms, Serializable {

    /**
     * Uses the benchmark algorithm to find the sublist of the inOutList with maximum sum
     @param inOutList list with the difference between people leaving and arriving at a vaccination center in the many time intervals
     @return sublist of the inOutList with maximum sum.
     */

    @Override
    public int[] getMaxSumList(int[] inOutList) {
        return Sum.Max(inOutList);
    }
}
