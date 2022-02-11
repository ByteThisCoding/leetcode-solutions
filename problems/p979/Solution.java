package problems.p979;

public class Solution {

    public int distributeCoins(TreeNode root) {
        TraversalRecord record = new TraversalRecord();
        traverseAndCount(root, record);
        return record.totalNumTransfers;
    }

    /**
     * Recursively move through the tree via depth first traversal
     * Keep track of values along the branch + overall values
     * Modify TraversalRecord in place
     */
    private void traverseAndCount(TreeNode root, TraversalRecord record) {
        // process left branch
        int leftCount = 0;
        if (root.left != null) {
            traverseAndCount(root.left, record);
            // total number of transfers always operates on positives
            record.totalNumTransfers += Math.abs(record.branchNumTransfers);

            // this will be negative if there are any 0s along the path
            leftCount = record.branchNumTransfers;
        }

        // process right branch
        int rightCount = 0;
        if (root.right != null) {
            traverseAndCount(root.right, record);
            record.totalNumTransfers += Math.abs(record.branchNumTransfers);
            rightCount = record.branchNumTransfers;
        }

        // update total number of transfers
        record.branchNumTransfers = root.val - 1 + leftCount + rightCount;
    }
}
