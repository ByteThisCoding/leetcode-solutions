class Solution {

    // helper to keep track of response
    class IntCounter {

        private int val = 0;

        public void increment() {
            val ++;
        }

        public int getValue() {
            return val;
        }
    }

    public int averageOfSubtree(TreeNode root) {
        IntCounter counter = new IntCounter();
        getSumsOfSubtree(root, counter);
        return counter.getValue();
    }

    /**
     * Post-order traversal to find node sums and counts
     * [node value sum, number of nodes]
     */
    private int[] getSumsOfSubtree(TreeNode node, IntCounter counter) {
        int[] sums = new int[] {node.val, 1};

        if (node.left != null) {
            int[] leftSums = getSumsOfSubtree(node.left, counter);
            sums[0] += leftSums[0];
            sums[1] += leftSums[1];
        }

        if (node.right != null) {
            int[] rightSums = getSumsOfSubtree(node.right, counter);
            sums[0] += rightSums[0];
            sums[1] += rightSums[1];
        }

        // calculate average of this node
        int average = sums[0] / sums[1];
        if (average == node.val) {
            counter.increment();
        }

        return sums;
    }
}