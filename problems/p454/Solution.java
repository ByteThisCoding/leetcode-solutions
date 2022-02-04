package problems.p454;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * Build up partial sums with quadratic iteration for last two arrays
     * Then, use quadratic iteration over first two and check against map
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> partialSums = createSubSumMap(nums3, nums4);

        // for each in nums1, count number of occurences of negative in partialSums
        int count = 0;
        for (int numA : nums1) {
            for (int numB : nums2) {
                count += partialSums.getOrDefault(-numB - numA, 0);
            }
        }
        return count;
    }

    /**
     * Create a map of sums for all sums from a to b
     * Key is sum, value is num occurences
     */
    private Map<Integer, Integer> createSubSumMap(int[] numsA, int[] numsB) {
        Map<Integer, Integer> sumsMap = new HashMap<>();

        for (int numA : numsA) {
            for (int numB : numsB) {
                Integer sum = numA + numB;
                sumsMap.put(
                        sum,
                        sumsMap.getOrDefault(sum, 0) + 1);
            }
        }

        return sumsMap;
    }
}
