package problems.p985;

public class Solution {

    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int[] answers = new int[queries.length];

        // keep track of running sum of evens throughout
        int evenSum = 0;

        // scan through once to get initial even sum
        for (int num : nums) {
            if (num % 2 == 0) {
                evenSum += num;
            }
        }

        // move through queries and make adjustments
        for (int i = 0; i < queries.length; i++) {
            int qVal = queries[i][0];
            int qInd = queries[i][1];

            // grab the previous num value and combine with new value
            int prevNumVal = nums[qInd];
            int qCombinedVal = prevNumVal + qVal;

            // record new value
            nums[qInd] = qCombinedVal;

            // remove prev value from sum if even
            if (prevNumVal % 2 == 0) {
                evenSum -= prevNumVal;
            }
            // add combined to sum if even
            if (qCombinedVal % 2 == 0) {
                evenSum += qCombinedVal;
            }

            // record answer
            answers[i] = evenSum;
        }

        return answers;
    }

}
