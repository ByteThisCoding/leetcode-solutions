package problems.p54;

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

        cases.add(new TestCase<List<Integer>>("LeetCode example 1", Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5),
                new Object[] {
                        new int[][] {
                                new int[] { 1, 2, 3 },
                                new int[] { 4, 5, 6 },
                                new int[] { 7, 8, 9 },
                        }
                }));
        cases.add(new TestCase<List<Integer>>("LeetCode example 2",
                Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7), new Object[] {
                        new int[][] {
                                new int[] { 1, 2, 3, 4 },
                                new int[] { 5, 6, 7, 8 },
                                new int[] { 9, 10, 11, 12 }
                        }
                }));
        cases.add(new TestCase<List<Integer>>("1x1 Matrix", Arrays.asList(1), new Object[] {
                new int[][] {
                        new int[] { 1 }
                }
        }));

        return cases;
    }

}
