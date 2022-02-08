package problems.p78;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.*;

public class Main extends SolutionRunner<List<List<Integer>>, Solution> {

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
    protected boolean areValuesEqual(List<List<Integer>> valueA, List<List<Integer>> valueB) {
        if (valueA.size() != valueB.size()) {
            return false;
        }

        for (int i = 0; i < valueA.size(); i++) {
            if (valueA.get(i).size() != valueB.get(i).size()) {
                return false;
            }

            for (int j = 0; j < valueA.get(i).size(); j++) {
                if (!valueA.get(i).get(j).equals(valueB.get(i).get(j))) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    protected ArrayList<TestCase<List<List<Integer>>>> getTestCases() {
        ArrayList<TestCase<List<List<Integer>>>> cases = new ArrayList<>();

        cases.add(new TestCase<List<List<Integer>>>("Powerset: []", new ArrayList<>(), new Object[] {
                new int[] {}
        }));
        cases.add(new TestCase<List<List<Integer>>>("Powerset: [0]", getPowerSetZeroExpected(), new Object[] {
                new int[] { 0 }
        }));
        cases.add(new TestCase<List<List<Integer>>>("Powerset: [0, 1]", getPowerSetZeroOneExpected(), new Object[] {
                new int[] { 0, 1 }
        }));
        cases.add(new TestCase<List<List<Integer>>>("Powerset: [0, 1, 2]", getPowerSetZeroOneTwoExpected(),
                new Object[] {
                        new int[] { 0, 1, 2 }
                }));

        return cases;
    }

    private List<List<Integer>> getPowerSetZeroExpected() {
        List<List<Integer>> powerSet = new ArrayList<>();
        powerSet.add(new ArrayList<>());
        powerSet.add(Arrays.asList(0));
        return powerSet;
    }

    private List<List<Integer>> getPowerSetZeroOneExpected() {
        List<List<Integer>> powerSet = new ArrayList<>();
        powerSet.add(new ArrayList<>());
        powerSet.add(Arrays.asList(0));
        powerSet.add(Arrays.asList(1));
        powerSet.add(Arrays.asList(0, 1));

        return powerSet;
    }

    private List<List<Integer>> getPowerSetZeroOneTwoExpected() {
        List<List<Integer>> powerSet = new ArrayList<>();
        powerSet.add(new ArrayList<>());
        powerSet.add(Arrays.asList(0));
        powerSet.add(Arrays.asList(1));
        powerSet.add(Arrays.asList(0, 1));
        powerSet.add(Arrays.asList(2));
        powerSet.add(Arrays.asList(0, 2));
        powerSet.add(Arrays.asList(1, 2));
        powerSet.add(Arrays.asList(0, 1, 2));

        return powerSet;
    }
}
