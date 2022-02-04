package problems.p438;

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
        cases.add(new TestCase<List<Integer>>("LeetCode example 1", new ArrayList<>(Arrays.asList(0, 6)), new Object[] {
                "cbaebabacd",
                "abc"
        }));
        cases.add(new TestCase<List<Integer>>("LeetCode example 2", new ArrayList<>(Arrays.asList(0, 1, 2)),
                new Object[] {
                        "abab",
                        "ab"
                }));
        cases.add(new TestCase<List<Integer>>("Case with no palidromes", new ArrayList<>(),
                new Object[] {
                        "abcdefg",
                        "hijk"
                }));
        cases.add(new TestCase<List<Integer>>("Case with almost palindromes (all but one char)", new ArrayList<>(),
                new Object[] {
                        "abcabcabc",
                        "abcd"
                }));
        cases.add(new TestCase<List<Integer>>("Case with repeated indices palindromes",
                new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4)),
                new Object[] {
                        "abababab",
                        "abab"
                }));

        return cases;
    }

}