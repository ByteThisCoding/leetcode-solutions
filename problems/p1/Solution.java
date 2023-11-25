import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // map between value and index in nums
        Map<Integer, Integer> values = new HashMap<>();

        for (int i=0; i<nums.length; i++) {
            // get the difference between target and current element
            int diff = target - nums[i];
            // now, check if a previous item is equal to that difference
            if (values.containsKey(diff)) {
                return new int[] {i, values.get(diff)};
            }
            values.put(nums[i], i);
        }

        return new int[0];
    }
}