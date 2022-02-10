package problems.p515;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution {

    /**
     * Perform a breadth first search to record the largest results
     */
    public List<Integer> largestValues(TreeNode root) {
        // initialize response with smallest possible values
        List<Integer> largestRowValues = new ArrayList<>();

        // base case, root is null
        if (root == null) {
            return largestRowValues;
        }

        // otherwise, proceed
        // record the nodes to visit + distance from root in queue
        Queue<QueueNode> nodesToVisit = new LinkedList<>();
        nodesToVisit.add(new QueueNode(root, 0));

        // continue iterating while nodes left to visit
        while (nodesToVisit.size() > 0) {
            QueueNode queueNode = nodesToVisit.poll();

            // update largest value for this depth
            if (largestRowValues.size() == queueNode.distanceFromRoot) {
                largestRowValues.add(queueNode.node.val);
            } else {
                int newLargestValue = Math.max(largestRowValues.get(queueNode.distanceFromRoot), queueNode.node.val);
                largestRowValues.set(queueNode.distanceFromRoot, newLargestValue);
            }

            // check left node
            if (queueNode.node.left != null) {
                // add to queue
                nodesToVisit.add(new QueueNode(queueNode.node.left, queueNode.distanceFromRoot + 1));
            }

            // check right node
            if (queueNode.node.right != null) {
                // add to queue
                nodesToVisit.add(new QueueNode(queueNode.node.right, queueNode.distanceFromRoot + 1));
            }
        }

        return largestRowValues;
    }
}
