package problems.p41;

public class Solution {

    public int firstMissingPositive(int[] nums) {

        // first run, swap out items that are not in their expected positions
        for (int i = 0; i < nums.length; i++) {
            // check value is in boundary and is not already in correct position
            if (nums[i] > 0 && nums[i] < nums.length && nums[i] != i + 1) {
                int tmp = nums[i] - 1;

                // also ensure value to swap is not in correct position
                if (nums[tmp] != tmp + 1) {
                    nums[i] = nums[tmp];
                    nums[tmp] = tmp + 1;

                    // rewind, so we can perform same check on number we just swapped
                    i--;
                }
            }
        }

        // second run, scan until we find first unexpected position, if any
        for (int i = 0; i < nums.length; i++) {
            // index should be equal to (actual item - 1)
            if (i != nums[i] - 1) {
                return i + 1;
            }
        }

        // if no unexpected, we have 1...n items, return n+1
        return nums.length + 1;
    }
}
