package problems.p1705;

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

        cases.add(new TestCase<Integer>("LeetCode Example 1", 7, new Object[] {
                new int[] { 1, 2, 3, 5, 2 },
                new int[] { 3, 2, 1, 4, 2 }
        }));
        cases.add(new TestCase<Integer>("LeetCode Example 2", 5, new Object[] {
                new int[] { 3, 0, 0, 0, 0, 2 },
                new int[] { 3, 0, 0, 0, 0, 2 }
        }));

        return cases;
    }

}
