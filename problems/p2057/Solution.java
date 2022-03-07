package problems.p2057;

public class Solution {

    public int smallestEqual(int[] nums) {

        // use a for loop to iterate over each number
        for (int i = 0; i < nums.length; i++) {
            // check the problem statement condition
            if (i % 10 == nums[i]) {
                // if that condition passed, we can return
                // i immediately without checking anything else
                return i;
            }
        }

        // if this line is reached, then no "i" satisfied the condition
        return -1;
    }
}
