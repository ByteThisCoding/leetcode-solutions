import java.util.ArrayList;

class Solution {

    /**
     * Keep track of the count of indices lower and higher,
     * then return a list offset by num lower
     */
    public List<Integer> targetIndices(int[] nums, int target) {
        int numLower = 0;
        int numHigher = 0;

        // iterate once and keep track of how many are lower and higher
        for (int num : nums) {
            if (num < target) {
                numLower ++;
            } else if (num > target) {
                numHigher ++;
            }
        }

        // assemble the list of indices
        List<Integer> indices = new ArrayList<>();
        int numTarget = nums.length - numHigher - numLower;
        for (int i=0; i<numTarget; i++) {
            indices.add(numLower + i);
        }

        return indices;
    }
}