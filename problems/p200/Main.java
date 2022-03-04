package problems.p200;

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

        cases.add(new TestCase<Integer>("LeetCode Example 1", 1, new Object[] {
                new char[][] {
                        new char[] { '1', '1', '1', '1', '0' },
                        new char[] { '1', '1', '0', '1', '0' },
                        new char[] { '1', '1', '0', '0', '0' },
                        new char[] { '0', '0', '0', '0', '0' },
                }
        }));

        cases.add(new TestCase<Integer>("LeetCode Example 2", 3, new Object[] {
                new char[][] {
                        new char[] { '1', '1', '0', '0', '0' },
                        new char[] { '1', '1', '0', '0', '0' },
                        new char[] { '0', '0', '1', '0', '0' },
                        new char[] { '0', '0', '0', '1', '1' },
                }
        }));

        return cases;
    }
}
