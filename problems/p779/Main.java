package problems.p779;

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

        cases.add(new TestCase<Integer>("LeetCode example 1", 0, new Object[] {
                1, 1
        }));
        cases.add(new TestCase<Integer>("LeetCode example 2", 0, new Object[] {
                2, 1
        }));
        cases.add(new TestCase<Integer>("LeetCode example 3", 1, new Object[] {
                2, 2
        }));
        cases.add(new TestCase<Integer>("n=3 k=1", 0, new Object[] {
                3, 1
        }));
        cases.add(new TestCase<Integer>("n=3 k=2", 1, new Object[] {
                3, 2
        }));
        cases.add(new TestCase<Integer>("n=3 k=1", 0, new Object[] {
                3, 1
        }));
        cases.add(new TestCase<Integer>("n=3 k=3", 1, new Object[] {
                3, 3
        }));
        cases.add(new TestCase<Integer>("n=3 k=4", 0, new Object[] {
                3, 4
        }));
        cases.add(new TestCase<Integer>("n=3 k=2", 1, new Object[] {
                3, 2
        }));
        cases.add(new TestCase<Integer>("n=4 k=1", 0, new Object[] {
                4, 1
        }));
        cases.add(new TestCase<Integer>("Whiteboard example", 0, new Object[] {
                5, 11
        }));

        return cases;
    }

}