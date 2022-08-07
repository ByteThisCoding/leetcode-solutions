package problems.p985;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.*;

public class Main extends SolutionRunner<int[], Solution> {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        // run against tests
        new Main().run();
    }

    @Override
    protected Class<Solution> getSolutionClass() {
        return Solution.class;
    }

    @Override
    protected boolean areValuesEqual(int[] valueA, int[] valueB) {
        if (valueA.length != valueB.length) {
            return false;
        }

        for (int i = 0; i < valueA.length; i++) {
            if (valueA[i] != valueB[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected String resultToString(int[] result) {
        return Arrays.toString(result);
    }

    @Override
    protected ArrayList<TestCase<int[]>> getTestCases() {
        ArrayList<TestCase<int[]>> cases = new ArrayList<>();

        cases.add(new TestCase<int[]>("LeetCode Example 1", new int[] { 8, 6, 2, 4 }, new Object[] {
                new int[] { 1, 2, 3, 4 },
                new int[][] {
                        new int[] { 1, 0 },
                        new int[] { -3, 1 },
                        new int[] { -4, 0 },
                        new int[] { 2, 3 }
                }
        }));
        cases.add(new TestCase<int[]>("LeetCode Example 2", new int[] { 0 }, new Object[] {
                new int[] { 1 },
                new int[][] {
                        new int[] { 4, 0 },
                }
        }));

        return cases;
    }
}