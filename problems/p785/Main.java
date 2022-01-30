package problems.p785;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import utils.*;

public class Main extends SolutionRunner<Boolean, Solution> {

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
    protected String getSolutionMethod() {
        return "isBipartite";
    }

    @Override
    protected boolean areValuesEqual(Boolean valueA, Boolean valueB) {
        return valueA.equals(valueB);
    }

    @Override
    protected ArrayList<TestCase<Boolean>> getTestCases() {
        ArrayList<TestCase<Boolean>> cases = new ArrayList<>();
        cases.add(new TestCase<Boolean>("LeetCode first example", false, new Object[] {
                new int[][] {
                        new int[] { 1, 2, 3 },
                        new int[] { 0, 2 },
                        new int[] { 0, 1, 3 },
                        new int[] { 0, 2 }
                }
        }));
        cases.add(new TestCase<Boolean>("LeetCode second example", true, new Object[] {
                new int[][] {
                        new int[] { 1, 3 },
                        new int[] { 0, 2 },
                        new int[] { 1, 3 },
                        new int[] { 0, 2 }
                }
        }));
        cases.add(new TestCase<Boolean>("Zero nodes", true, new Object[] {
                new int[][] {}
        }));
        cases.add(new TestCase<Boolean>("Two nodes", true, new Object[] {
                new int[][] {
                        new int[] { 1 },
                        new int[] { 0 }
                }
        }));
        cases.add(new TestCase<Boolean>("Disconnected Graph which is bipartite", true, new Object[] {
                new int[][] {
                        // first subgraph
                        new int[] { 1, 3 },
                        new int[] { 0, 2 },
                        new int[] { 1, 3 },
                        new int[] { 0, 2 },
                        // second subgraph
                        new int[] { 5 },
                        new int[] { 4 }
                }
        }));
        cases.add(new TestCase<Boolean>("Disconnected Graph which is not bipartite", false, new Object[] {
                new int[][] {
                        // first subgraph
                        new int[] { 1, 3 },
                        new int[] { 0, 2 },
                        new int[] { 1, 3 },
                        new int[] { 0, 2 },
                        // second subgraph
                        new int[] { 5 },
                        new int[] { 6 },
                        new int[] { 7 },
                        new int[] { 5 }
                }
        }));

        return cases;
    }

}
