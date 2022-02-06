package problems.p1315;

public class Solution {

    public int sumEvenGrandparent(TreeNode root) {
        return getSubtreeSum(root, -1, -1);
    }

    /**
     * Recursively solve the problem for subtrees
     * Pass in whether or not parents or grandparents were even
     */
    private int getSubtreeSum(TreeNode root, int parentValue, int grandParentValue) {
        // initialize to 0, default case
        int sum = 0;
        // if grandParent is even, add this node's value to sum
        if (grandParentValue % 2 == 0) {
            sum += root.val;
        }
        // return the sum + values from recursive calls
        return sum + (root.left == null ? 0 : getSubtreeSum(root.left, root.val, parentValue))
                + (root.right == null ? 0 : getSubtreeSum(root.right, root.val, parentValue));
    }

}
