import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Solution {

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        // initialize memo
        Object[] countMemo = new Object[locations.length];
        for (int i=0; i<locations.length; i++) {
            countMemo[i] = new HashMap<>();
        }

        // begin recursive search
        return countRoutesWithMemo(
            locations,
            start,
            finish,
            fuel,
            countMemo
        );
    }

    /**
     * Use a count memo to keep track of the count
     * from a start to an end pos with certain amount of fuel
     * 
     */
    private int countRoutesWithMemo(
        int[] locations,
        int start,
        int finish,
        int fuel,
        Object[] countMemo
    ) {
        // base case, no more fuel
        if (fuel < 0) {
            return 0;
        }

        // read from memo if available
        int memoValue = readMemo(start, fuel, countMemo);
        if (memoValue > -1) {
            return memoValue;
        }

        int count = 0;
        // if we're at the destination, add to the count
        if (start == finish) {
            count = 1;
        }

        for (int i=0; i<locations.length; i++) {
            if (i == start) {
                continue;
            }

            int distance = Math.abs(locations[start] - locations[i]);
            count = addToCount(count, countRoutesWithMemo(
                locations,
                i,
                finish,
                fuel - distance,
                countMemo
            ));
        }

        updateMemo(
            start,
            fuel,
            count,
            countMemo
        );
        return count;
    }

    private int readMemo(
        int start,
        int fuel,
        Object[] countMemo
    ) {
        return ((Map<Integer, Integer>) countMemo[start]).getOrDefault(fuel, -1);
    }

    private void updateMemo(
        int start,
        int fuel,
        int count,
        Object[] countMemo
    ) {
        ((Map<Integer, Integer>) countMemo[start]).put(fuel, count);
    }

    private int addToCount(int count, int addValue) {
        count = (count + addValue) % 1000000007;
        return count;
    }
}