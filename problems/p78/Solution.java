package problems.p78;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * The power set is a set of all combinations of some input set
     * It is itself a set of sets
     */
    public List<List<Integer>> subsets(int[] nums) {
        // initialize array with capacity
        int powerSetSize = (int) Math.pow(2, nums == null ? 0 : nums.length);
        List<List<Integer>> powerSet = new ArrayList<>(powerSetSize);

        // first entry is the empty set
        powerSet.add(new ArrayList<>());

        // iterate for each item
        for (int i = 0; i < nums.length; i++) {
            // iterate over previous existing items and copy in
            int powerSetPartialSize = powerSet.size();
            for (int j = 0; j < powerSetPartialSize; j++) {
                List<Integer> newSet = new ArrayList<>(powerSet.get(j));
                newSet.add(nums[i]);
                powerSet.add(newSet);
            }
        }

        return powerSet;
    }
}
