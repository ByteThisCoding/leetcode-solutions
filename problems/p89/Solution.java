package problems.p89;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * Conceptually, this iterates over nodes in a tree,
     * but the implementation works over a list
     */
    public List<Integer> grayCode(int n) {
        // there will be 2^n elements total
        int iterationLimit = (int) Math.pow(2, n - 1);
        // initialize array with capacity size
        List<Integer> sequence = new ArrayList<>(iterationLimit * 2);

        // iterate and add items
        boolean doSwap = false;
        // in first iteration, array is empty, so set outside to help
        int item = 0;
        for (int i = 0; i < iterationLimit; i++) {

            int db = item * 2;
            if (doSwap) {
                sequence.add(db + 1);
                sequence.add(db);
            } else {
                sequence.add(db);
                sequence.add(db + 1);
            }

            // prep for next iteration
            doSwap = !doSwap;
            item = sequence.get(i + 1);
        }

        return sequence;
    }
}
