package problems.p2100;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * We'll take an approach similar to subset sum
     * For each consecutive non inc / dec run:
     * : start with value of 1, then add 1 + prev to each subsequent
     */
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> goodDays = new ArrayList<>();

        // keep track of the valid number of days before each index
        // note that all values are defaulted to 0
        int[] numDaysBefore = new int[security.length];
        int[] numDaysAfter = new int[security.length];

        // iterate left to right: get non increasing runs
        for (int i = 1; i < security.length; i++) {
            if (security[i] <= security[i - 1]) {
                numDaysBefore[i] = 1 + numDaysBefore[i - 1];
            }
        }

        // iterate right to left: get non decreasing runs
        for (int i = security.length - 2; i >= 0; i--) {
            if (security[i + 1] >= security[i]) {
                numDaysAfter[i] = 1 + numDaysAfter[i + 1];
            }
        }

        // iterate over time windows
        for (int i = time; i < security.length - time; i++) {
            if (numDaysBefore[i] >= time && time <= numDaysAfter[i]) {
                goodDays.add(i);
            }
        }

        return goodDays;
    }
}
