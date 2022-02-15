package problems.p2161;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * To sort against pivot, but maintain relative ordering:
     * : Create two arrays, one for values to the left, one for right
     * : Count number of occurences of the pivot itself
     * : Once arrays are filled, replace original array with values
     */
    public int[] pivotArray(int[] nums, int pivot) {
        // we'll anticipate each array to have roughly half of the total elements
        // allocate capacity accordingly
        List<Integer> leftPivotValues = new ArrayList<>(nums.length / 2);
        List<Integer> rightPivotValues = new ArrayList<>(nums.length / 2);

        int numPivotValues = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < pivot) {
                leftPivotValues.add(nums[i]);
            } else if (nums[i] > pivot) {
                rightPivotValues.add(nums[i]);
            } else {
                numPivotValues++;
            }
        }

        // update original array with new values
        // this will replace all values
        for (int i = 0; i < leftPivotValues.size(); i++) {
            nums[i] = leftPivotValues.get(i);
        }

        // re-add the number of pivot occurences itself
        int offset = leftPivotValues.size() + numPivotValues;
        for (int i = leftPivotValues.size(); i < offset; i++) {
            nums[i] = pivot;
        }

        // add values to the right of the pivot
        for (int i = 0; i < rightPivotValues.size(); i++) {
            nums[offset + i] = rightPivotValues.get(i);
        }

        return nums;
    }
}
