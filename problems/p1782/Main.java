package problems.p1782;

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
    protected ArrayList<TestCase<int[]>> getTestCases() {
        ArrayList<TestCase<int[]>> cases = new ArrayList<>();

        cases.add(new TestCase<int[]>("LeetCode Example 1", new int[] { 6, 5 }, new Object[] {
                4,
                new int[][] {
                        new int[] { 1, 2 },
                        new int[] { 2, 4 },
                        new int[] { 1, 3 },
                        new int[] { 2, 3 },
                        new int[] { 2, 1 }
                },
                new int[] {
                        2, 3
                }
        }));
        cases.add(new TestCase<int[]>("LeetCode Example 2", new int[] { 10, 10, 9, 8, 6 }, new Object[] {
                5,
                new int[][] {
                        new int[] { 1, 5 },
                        new int[] { 1, 5 },
                        new int[] { 3, 4 },
                        new int[] { 2, 5 },
                        new int[] { 1, 3 },
                        new int[] { 5, 1 },
                        new int[] { 2, 3 },
                        new int[] { 2, 5 }
                },
                new int[] {
                        1, 2, 3, 4, 5
                }
        }));
        cases.add(new TestCase<int[]>("LeetCode Failed Case",
                new int[] { 10, 8, 10, 10, 8, 8, 10, 10, 10, 10, 8, 10, 10, 8, 10, 8, 8, 3 }, new Object[] {
                        5,
                        new int[][] {
                                new int[] { 4, 5 },
                                new int[] { 1, 3 },
                                new int[] { 1, 4 },
                        },
                        new int[] {
                                0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 2
                        }
                }));

        return cases;
    }

    @Override
    public String resultToString(int[] result) {
        return Arrays.toString(result);
    }
}
