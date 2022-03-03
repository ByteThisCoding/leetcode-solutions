package problems.p1493;

public class Solution {

    /**
     * Iterate over nums, and keep track of the last and 2nd to last
     * non 1 indices. Use them to find the best difference with 1 item removed
     * 
     * We'll also consider -1 and nums.length as non-one indices
     */
    public int longestSubarray(int[] nums) {

        int bestDiff = 0;

        int prevLastNonOneIndex = -1;
        int lastNonOneIndex = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 1) {
                int diff = i - prevLastNonOneIndex - 2;
                if (diff > bestDiff) {
                    bestDiff = diff;
                }

                prevLastNonOneIndex = lastNonOneIndex;
                lastNonOneIndex = i;
            }
        }

        return Math.max(nums.length - prevLastNonOneIndex - 2, bestDiff);
    }
}
