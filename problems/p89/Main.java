package problems.p89;

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
        ArrayList<TestCase<List<Integer>>> testCases = new ArrayList<>();
        testCases.add(new TestCase<List<Integer>>("LeetCode first example",
                new ArrayList<Integer>(Arrays.asList(0, 1, 3, 2)), new Object[] {
                        2
                }));
        testCases.add(new TestCase<List<Integer>>("LeetCode second example",
                new ArrayList<Integer>(Arrays.asList(0, 1)), new Object[] {
                        1
                }));
        testCases.add(new TestCase<List<Integer>>("Test for n = 3 from whiteboard",
                new ArrayList<Integer>(Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4)), new Object[] {
                        3
                }));

        return testCases;
    }

}
