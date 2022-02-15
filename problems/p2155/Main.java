package problems.p2155;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.*;

public class Main extends SolutionRunner<List<Integer>, Solution> {

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
    protected boolean areValuesEqual(List<Integer> valueA, List<Integer> valueB) {
        if (valueA.size() != valueB.size()) {
            return false;
        }

        for (int i = 0; i < valueA.size(); i++) {
            if (!valueA.get(i).equals(valueB.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected ArrayList<TestCase<List<Integer>>> getTestCases() {
        ArrayList<TestCase<List<Integer>>> cases = new ArrayList<>();

        cases.add(new TestCase<List<Integer>>("LeetCode Example 1", Arrays.asList(4, 2), new Object[] {
                new int[] { 0, 0, 1, 0 }
        }));
        cases.add(new TestCase<List<Integer>>("LeetCode Example 2", Arrays.asList(3), new Object[] {
                new int[] { 0, 0, 0 }
        }));
        cases.add(new TestCase<List<Integer>>("LeetCode Example 3", Arrays.asList(0), new Object[] {
                new int[] { 1, 1 }
        }));

        return cases;
    }
}
