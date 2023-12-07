/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int[] treeQueries(TreeNode root, int[] queries) {
        Map<Integer, Integer> depths = new HashMap<>();
        Map<Integer, Integer> maxDepths = new HashMap<>();
        Map<Integer, TreeNode> parentNodes = new HashMap<>();

        determineDepths(root, depths, maxDepths, parentNodes, 0);

        int[] outputs = new int[queries.length];
        // for each query, grap parent of node value, then determine removal

        for (int i=0; i<queries.length; i++) {
            outputs[i] = determineQueryMaxHeight(
                queries[i],
                root,
                depths,
                maxDepths,
                parentNodes
            );
        }

        return outputs;
    }

    private int determineQueryMaxHeight(
        int query,
        TreeNode root,
        Map<Integer, Integer> depths,
        Map<Integer, Integer> maxDepths,
        Map<Integer, TreeNode> parentNodes 
    ) {
        // from the affected node, calculate new max depths
        int outputVal = 0;
        TreeNode parentNode = parentNodes.get(query);

        // case, parent has only one child, update based on parent depth
        if (parentNode.left == null || parentNode.right == null) {
            outputVal = depths.get(parentNode.val);
        } else if (parentNode.left.val == query) {
            // case, parent has two children, update based on other child value
            outputVal = maxDepths.get(parentNode.right.val);
        } else if (parentNode.right.val == query) {
            outputVal = maxDepths.get(parentNode.left.val);
        }


        // go up until we reach root or until value stops changing
        // keep track of path that was affected by the query
        int queryNodePathValue = parentNode.val;
        // move up the parent node
        parentNode = parentNodes.get(parentNode.val);
        while (parentNode != null) {
            // get the max of both nodes
            int thisMax = -1;
            if (parentNode.left != null) {
                // if equal, use current output val
                if (parentNode.left.val == queryNodePathValue) {
                    thisMax = outputVal;
                } else {
                    // otherwise, use existing value
                    thisMax = maxDepths.get(parentNode.left.val);
                }
            }

            if (parentNode.right != null) {
                // if equal, use current output val
                if (parentNode.right.val == queryNodePathValue) {
                    thisMax = Math.max(thisMax, outputVal);
                } else {
                    // otherwise, use existing value
                    thisMax = Math.max(
                        thisMax,
                        maxDepths.get(parentNode.right.val)
                    );
                }
            }

            // check if it has changed from removal
            int parentMax = maxDepths.get(parentNode.val);

            // if no change, get the previous max from the root
            if (parentMax == thisMax) {
                return maxDepths.get(root.val);
            }
            // if there is a change, keep propagating
            queryNodePathValue = parentNode.val;
            parentNode = parentNodes.get(parentNode.val);
            outputVal = thisMax;
        }

        // if we reach here, then outputVal is the new max depth
        return outputVal;
    }

    private int determineDepths(
        TreeNode startNode,
        Map<Integer, Integer> depths,
        Map<Integer, Integer> maxDepths,
        Map<Integer, TreeNode> parentNodes,
        int currentDepth
    ) {
        // find depth values and add to parent nodes
        int maxDepth = currentDepth;
        if (startNode.left != null) {
            parentNodes.put(startNode.left.val, startNode);

            maxDepth = determineDepths(
                startNode.left,
                depths,
                maxDepths,
                parentNodes,
                currentDepth + 1
            );
        }

        if (startNode.right != null) {
            parentNodes.put(startNode.right.val, startNode);

            maxDepth = Math.max(maxDepth, determineDepths(
                startNode.right,
                depths,
                maxDepths,
                parentNodes,
                currentDepth + 1
            ));
        }

        maxDepths.put(startNode.val, maxDepth);
        depths.put(startNode.val, currentDepth);
        return maxDepth;
    }
}