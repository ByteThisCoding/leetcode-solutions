class Solution {
    
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> rootNodes = new ArrayList<>();

        // turn to_delete into a set for faster access
        Set<Integer> deletionSet = new HashSet<>();
        for (int item : to_delete) {
            deletionSet.add(item);
        }

        // base case, root node is to be deleted
        if (!deletionSet.contains(root.val)) {
            rootNodes.add(root);
        }

        processNode(root, deletionSet, rootNodes);

        return rootNodes;
    }

    private boolean processNode(
        TreeNode node,
        Set<Integer> deletionSet,
        List<TreeNode> rootNodes
    ) {
        // process children and update this nodes ref
        if (node.left != null) {
            boolean doDelete = processNode(node.left, deletionSet, rootNodes);
            if (doDelete) {
                node.left = null;
            }
        }
        if (node.right != null) {
            boolean doDelete = processNode(node.right, deletionSet, rootNodes);
            if (doDelete) {
                node.right = null;
            }
        }

        // if this is node to delete, update refs
        if (deletionSet.contains(node.val)) {
            if (node.left != null) {
                rootNodes.add(node.left);
            }
            if (node.right != null) {
                rootNodes.add(node.right);
            }
            return true;
        }

        return false;
    }
}