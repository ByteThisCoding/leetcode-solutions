package problems.p23;

/**
 * ListNode class provided by the problem
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * Custom implementation for testing purposes
     */
    @Override
    public String toString() {
        String str = "" + this.val + " ==> ";
        if (next == null) {
            str += "null";
        } else {
            str += next.toString();
        }
        return str;
    }
}
