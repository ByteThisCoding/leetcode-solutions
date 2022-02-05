package problems.p23;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import utils.*;

public class Main extends SolutionRunner<ListNode, Solution> {

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
    protected boolean areValuesEqual(ListNode valueA, ListNode valueB) {
        // iterate through until a discrepency is made
        while (valueA != null && valueB != null) {
            if (valueA.val != valueB.val) {
                return false;
            }
            valueA = valueA.next;
            valueB = valueB.next;
        }

        return valueA == null && valueB == null;
    }

    @Override
    protected ArrayList<TestCase<ListNode>> getTestCases() {
        ArrayList<TestCase<ListNode>> cases = new ArrayList<>();

        cases.add(new TestCase<ListNode>("LeetCode Example 1", getExampleOneListNodeExpected(),
                new Object[] { getExampleOneInputs() }));
        cases.add(new TestCase<ListNode>("LeetCode Example 2", getExampleTwoListNodeExpected(),
                new Object[] { getExampleTwoInputs() }));
        cases.add(new TestCase<ListNode>("LeetCode Example 3", getExampleThreeListNodeExpected(),
                new Object[] { getExampleThreeInputs() }));

        return cases;
    }

    // test case 1 input
    private ListNode getExampleOneListNodeExpected() {
        return listToLinkedNode(new int[] { 1, 1, 2, 3, 4, 4, 5, 6 });
    }

    // test case 1 output
    private ListNode[] getExampleOneInputs() {
        ListNode[] lists = new ListNode[3];
        lists[0] = listToLinkedNode(new int[] { 1, 4, 5 });
        lists[1] = listToLinkedNode(new int[] { 1, 3, 4 });
        lists[2] = listToLinkedNode(new int[] { 2, 6 });

        return lists;
    }

    // test case 2 input
    private ListNode getExampleTwoListNodeExpected() {
        return null;
    }

    // test case 2 output
    private ListNode[] getExampleTwoInputs() {
        ListNode[] lists = new ListNode[0];
        return lists;
    }

    // test case 3 input
    private ListNode getExampleThreeListNodeExpected() {
        return null;
    }

    // test case 3 output
    private ListNode[] getExampleThreeInputs() {
        ListNode[] lists = new ListNode[1];
        lists[0] = null;
        return lists;
    }

    private ListNode listToLinkedNode(int[] list) {
        if (list.length == 0) {
            return null;
        }

        ListNode head = new ListNode(list[0]);
        ListNode node = head;

        for (int i = 1; i < list.length; i++) {
            ListNode nextNode = new ListNode(list[i]);
            node.next = nextNode;
            node = nextNode;
        }

        return head;
    }

}