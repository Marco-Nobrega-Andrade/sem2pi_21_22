package app.domain.model.BruteForceAlgorithm;

import app.domain.model.AnalyzePerformance.BruteForceAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BruteForceAlgorithmTest {

    @Test
    void getMaxSumList() {

        BruteForceAlgorithm bruteForceAlgorithm = new BruteForceAlgorithm();
        int[] inOutList = new int[]{242, 194, 241, 92, 53, 27, 74, -75, -357, -125, -29, 141, 269, 121, 50, 26, -29, -118, -254, 89, 246, -20, -119, -322};
        int[] expectedOutputList = new int[]{242, 194, 241, 92, 53, 27, 74, -75, -357, -125, -29, 141, 269, 121, 50, 26};

        assertEquals(Arrays.toString(bruteForceAlgorithm.getMaxSumList(inOutList)), Arrays.toString(expectedOutputList));
    }
}