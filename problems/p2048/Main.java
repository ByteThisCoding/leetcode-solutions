package problems.p2048;

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

        cases.add(new TestCase<Integer>("LeetCode Example 1", 22, new Object[] {
                1
        }));
        cases.add(new TestCase<Integer>("LeetCode Example 2", 1333, new Object[] {
                1000
        }));
        cases.add(new TestCase<Integer>("LeetCode Example 3", 3133, new Object[] {
                3000
        }));

        return cases;
    }

}
