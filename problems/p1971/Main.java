package problems.p1971;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import utils.*;

/**
 * This will run and test the solution
 * This code is not submitted to LeetCode,
 * it exists for testing and demo purposes
 */
public class Main extends SolutionRunner<Boolean, Solution> {

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
    protected String getSolutionMethod() {
        return "validPath";
    }

    @Override
    protected boolean areValuesEqual(Boolean valueA, Boolean valueB) {
        return valueA.equals(valueB);
    }

    @Override
    protected ArrayList<TestCase<Boolean>> getTestCases() {
        ArrayList<TestCase<Boolean>> cases = new ArrayList<>();
        // first example
        cases.add(new TestCase<Boolean>("First example given by LeetCode", true, new Object[] {
                3,
                new int[][] {
                        new int[] { 0, 1 },
                        new int[] { 1, 2 },
                        new int[] { 2, 0 }
                },
                0,
                2
        }));
        // first example with different source + dest
        cases.add(new TestCase<Boolean>("First example given by LeetCode with different source + dest", true,
                new Object[] {
                        3,
                        new int[][] {
                                new int[] { 0, 1 },
                                new int[] { 1, 2 },
                                new int[] { 2, 0 }
                        },
                        2,
                        0
                }));
        // second example
        cases.add(new TestCase<Boolean>("Second example given by LeetCode", false, new Object[] {
                6,
                new int[][] {
                        new int[] { 0, 1 },
                        new int[] { 0, 2 },
                        new int[] { 3, 5 },
                        new int[] { 5, 4 },
                        new int[] { 4, 3 },
                },
                0,
                5
        }));
        // second example with path that does exist
        cases.add(new TestCase<Boolean>("Second example given by LeetCode with path exists", true,
                new Object[] {
                        6,
                        new int[][] {
                                new int[] { 0, 1 },
                                new int[] { 0, 2 },
                                new int[] { 3, 5 },
                                new int[] { 5, 4 },
                                new int[] { 4, 3 },
                        },
                        3,
                        5
                }));
        // simple case, direct connection
        cases.add(new TestCase<Boolean>("Test simple case with direct connection", true, new Object[] {
                2,
                new int[][] {
                        new int[] { 0, 1 },
                        new int[] { 1, 0 },
                },
                0,
                1
        }));
        // simple case, direct connection in reverse
        cases.add(new TestCase<Boolean>("Test simple case with direct connection reverse", true, new Object[] {
                2,
                new int[][] {
                        new int[] { 0, 1 },
                        new int[] { 1, 0 },
                },
                1,
                0
        }));
        // case: source and dest are the same
        cases.add(new TestCase<Boolean>("Test case where source and dest are the same", true, new Object[] {
                4,
                new int[][] {
                        new int[] { 0, 1 },
                        new int[] { 1, 3 },
                        new int[] { 3, 2 },
                        new int[] { 2, 0 },
                },
                0,
                0
        }));
        return cases;
    }

}
