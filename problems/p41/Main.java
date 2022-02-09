package problems.p41;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        cases.add(new TestCase<Integer>("Simple Case: [1, 2]", 3, new Object[] {
                new int[] { 1, 2 }
        }));
        cases.add(new TestCase<Integer>("Simple Case Reverse Order: [2, 1]", 3, new Object[] {
                new int[] { 2, 1 }
        }));
        cases.add(new TestCase<Integer>("LeetCode example 1", 3, new Object[] {
                new int[] { 1, 2, 0 }
        }));
        cases.add(new TestCase<Integer>("LeetCode example 2", 2, new Object[] {
                new int[] { 3, 4, -1, 1 }
        }));
        cases.add(new TestCase<Integer>("LeetCode example 3", 1, new Object[] {
                new int[] { 7, 8, 9, 11, 12 }
        }));
        cases.add(new TestCase<Integer>("LeetCode example 1 with duplicates", 3, new Object[] {
                new int[] { 1, 2, 0, 1, 2 }
        }));
        cases.add(new TestCase<Integer>("Many negatives and zeros", 2, new Object[] {
                new int[] { 0, 0, 0, 0, -1, -2, -3, -5, -11, 0, 0, 0, 1, -6, -12, }
        }));

        return cases;
    }

}
