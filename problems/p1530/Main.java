package problems.p1530;

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

        cases.add(new TestCase<Integer>("Root Node Only", 0, new Object[] {
                new TreeNode(),
                2
        }));
        cases.add(new TestCase<Integer>("LeetCode Example 1", 1, new Object[] {
                createExampleOneTree(),
                3
        }));
        cases.add(new TestCase<Integer>("LeetCode Example 2", 2, new Object[] {
                createExampleTwoTree(),
                3
        }));
        cases.add(new TestCase<Integer>("LeetCode Example 3", 1, new Object[] {
                createExampleThreeTree(),
                3
        }));

        return cases;
    }

    private TreeNode createExampleOneTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);

        return root;
    }

    private TreeNode createExampleTwoTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        return root;
    }

    private TreeNode createExampleThreeTree() {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(6);

        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(3);

        root.right.left.right = new TreeNode(2);

        return root;
    }
}
