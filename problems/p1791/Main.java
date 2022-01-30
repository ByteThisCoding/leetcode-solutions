package problems.p1791;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import utils.*;

public class Main extends SolutionRunner<Integer, Solution> {

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
        return "findCenter";
    }

    @Override
    protected boolean areValuesEqual(Integer valueA, Integer valueB) {
        return valueA.compareTo(valueB) == 0;
    }

    @Override
    protected ArrayList<TestCase<Integer>> getTestCases() {
        ArrayList<TestCase<Integer>> cases = new ArrayList<>();
        // example provided by leetcode
        cases.add(new TestCase<Integer>("LeetCode problem example", 2, new Object[] {
                new int[][] {
                        new int[] { 1, 2 },
                        new int[] { 2, 3 },
                        new int[] { 4, 2 }
                }
        }));
        // example with more edges
        cases.add(new TestCase<Integer>("LeetCode problem example", 2, new Object[] {
                new int[][] {
                        new int[] { 2, 6 },
                        new int[] { 1, 2 },
                        new int[] { 2, 3 },
                        new int[] { 4, 2 },
                        new int[] { 5, 2 }
                }
        }));

        return cases;
    }

}
