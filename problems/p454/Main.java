package problems.p454;

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
    protected boolean areValuesEqual(Integer valueA, Integer valueB) {
        return valueA.equals(valueB);
    }

    @Override
    protected ArrayList<TestCase<Integer>> getTestCases() {
        ArrayList<TestCase<Integer>> cases = new ArrayList<>();

        cases.add(new TestCase<Integer>("LeetCode example 1", 2, new Object[] {
                new int[] { 1, 2 },
                new int[] { -2, -1 },
                new int[] { -1, 2 },
                new int[] { 0, 2 }
        }));
        cases.add(new TestCase<Integer>("LeetCode example 2", 1, new Object[] {
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 }
        }));
        cases.add(new TestCase<Integer>("No solutions", 0, new Object[] {
                new int[] { 1 },
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 }
        }));
        cases.add(new TestCase<Integer>("LeetCode example 1 in alternate order", 2, new Object[] {
                new int[] { -2, -1 },
                new int[] { 1, 2 },
                new int[] { 0, 2 },
                new int[] { -1, 2 },
        }));

        return cases;
    }

}