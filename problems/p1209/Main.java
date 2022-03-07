package problems.p1209;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import utils.*;

public class Main extends SolutionRunner<String, Solution> {

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
    protected boolean areValuesEqual(String valueA, String valueB) {
        return valueA.equals(valueB);
    }

    @Override
    protected ArrayList<TestCase<String>> getTestCases() {
        ArrayList<TestCase<String>> cases = new ArrayList<>();

        cases.add(new TestCase<String>("LeetCode Example 1", "abcd", new Object[] {
                "abcd",
                2
        }));
        cases.add(new TestCase<String>("LeetCode Example 2", "aa", new Object[] {
                "deeedbbcccbdaa",
                3
        }));
        cases.add(new TestCase<String>("LeetCode Example 3", "ps", new Object[] {
                "pbbcggttciiippooaais",
                2
        }));
        cases.add(new TestCase<String>("Diagram Example", "dd", new Object[] {
                "daabbcccbad",
                3
        }));
        cases.add(new TestCase<String>("Deletion At Beginning", "bc", new Object[] {
                "aaabc",
                3
        }));
        cases.add(new TestCase<String>("Deletion At End", "bc", new Object[] {
                "bcaaa",
                3
        }));
        cases.add(new TestCase<String>("All Chars Removed", "", new Object[] {
                "aabbcc",
                2
        }));

        return cases;
    }

}
