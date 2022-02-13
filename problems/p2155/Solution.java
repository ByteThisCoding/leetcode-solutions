package problems.p2155;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    /**
     * We'll use an approach similar to subset sum to count occurences
     * Instead of keeping an array, we'll count initial 0 count total,
     * then decrement as we perform 2nd loop
     */
    public List<Integer> maxScoreIndices(int[] nums) {
        int numZeros = 0;

        // count and fill arrays, starting with leftZeroCounts
        for (int i = 0; i < nums.length; i++) {
            numZeros += nums[i] == 0 ? 1 : 0;
        }

        // iterate through and use a map to record the best counts
        // alternatively, we could just perform two iterations:
        // one to find the best score, one to get the best scores
        Map<Integer, List<Integer>> iScores = new HashMap<>();
        int bestScore = -1;
        int oneRightCount = 0;
        for (int i = nums.length; i >= 0; i--) {
            if (i < nums.length && nums[i] == 1) {
                oneRightCount++;
            } else {
                numZeros--;
            }

            int score = numZeros + oneRightCount;

            if (score > bestScore) {
                bestScore = score;
            }

            if (iScores.containsKey(score)) {
                iScores.get(score).add(i);
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(i);
                iScores.put(score, newList);
            }
        }

        return iScores.get(bestScore);
    }
}
