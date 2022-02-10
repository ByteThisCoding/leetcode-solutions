package problems.p515;

/**
 * Used by the Queue to hold node + distance from root
 */
public class QueueNode {

    public int distanceFromRoot;
    public TreeNode node;

    QueueNode(TreeNode node, int distanceFromRoot) {
        this.distanceFromRoot = distanceFromRoot;
        this.node = node;
    }
}
