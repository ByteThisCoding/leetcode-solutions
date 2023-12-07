import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public int[][] merge(int[][] intervals) {
        // output
        List<int[]> mergedIntervals = new ArrayList<>();

        // sort the intervals
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int minLeft = intervals[0][0];
        int maxRight = intervals[0][1];
        for (int i=1; i<intervals.length; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];

            // if left is between, they intersect
            if (left >= minLeft && left <= maxRight) {
                maxRight = Math.max(right, maxRight);
            } else {
                mergedIntervals.add(
                    new int[] { minLeft, maxRight }
                );
                // prep next interval iteration
                minLeft = left;
                maxRight = right;
            }
        }

        // add in remaining interval
        mergedIntervals.add(
            new int[] { minLeft, maxRight }
        );

        return (int[][]) mergedIntervals.toArray(
            new int[mergedIntervals.size()][]
        );
    }
}