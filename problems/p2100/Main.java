package problems.p2100;

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

        cases.add(new TestCase<List<Integer>>("LeetCode Example 1", Arrays.asList(2, 3), new Object[] {
                new int[] { 5, 3, 3, 3, 5, 6, 2 },
                2
        }));
        cases.add(new TestCase<List<Integer>>("LeetCode Example 2", Arrays.asList(0, 1, 2, 3, 4), new Object[] {
                new int[] { 1, 1, 1, 1, 1 },
                0
        }));
        cases.add(new TestCase<List<Integer>>("LeetCode Example 3", Arrays.asList(), new Object[] {
                new int[] { 1, 2, 3, 4, 5, 6 },
                2
        }));
        cases.add(new TestCase<List<Integer>>("Simple Zero Case", Arrays.asList(), new Object[] {
                new int[] { 1, 2, 3 },
                1
        }));
        cases.add(new TestCase<List<Integer>>("Simple Zero Case Reverse", Arrays.asList(), new Object[] {
                new int[] { 3, 2, 1 },
                1
        }));
        cases.add(new TestCase<List<Integer>>("Simple All Case", Arrays.asList(0, 1, 2), new Object[] {
                new int[] { 1, 2, 3 },
                0
        }));

        return cases;
    }

}
