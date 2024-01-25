class Solution {
    public int minimumSemesters(int n, int[][] relations) {
        // represent graphs as list of lists
        List<List<Integer>> nodes = new ArrayList<>(n);
        List<Integer> visitedNodeDepths = new ArrayList<>(n);
        for (int i=0; i<n; i++) {
            nodes.add(new ArrayList<>());
            visitedNodeDepths.add(-1);
        }

        // record classes that have no prerequisites
        Set<Integer> nonEntryCourses = new HashSet<>();

        for (int[] courseRelation : relations) {
            // add from course
            List<Integer> coursesFromNode = nodes.get(courseRelation[0]-1);
            coursesFromNode.add(courseRelation[1]-1);

            // ensure the "to" path is added to non-prereq courses
            nonEntryCourses.add(courseRelation[1]-1);
        }

        // dfs, ignoring visited nodes
        int maxPath = 0;
        for (int i=0; i<nodes.size(); i++) {
            if (nonEntryCourses.contains(i)) {
                continue;
            }

            int thisMaxPath = findMaximumPathSize(
                i,
                nodes,
                visitedNodeDepths,
                new HashSet<>()
            );

            // if cycle found
            if (thisMaxPath == -1) {
                return -1;
            }

            maxPath = Math.max(maxPath, thisMaxPath);
        }

        // if any cycles, no courses can be studied
        if (visitedNodeDepths.contains(-1)) {
            return -1;
        }

        return maxPath;
    }

    private int findMaximumPathSize(
        int startNode,
        List<List<Integer>> nodes,
        List<Integer> visitedNodeDepths,
        Set<Integer> traversalVisitedNodes
    ) {
        // check if cycle detected
        if (traversalVisitedNodes.contains(startNode)) {
            return -1;
        }
        traversalVisitedNodes.add(startNode);

        List<Integer> nodeConnections = nodes.get(startNode);

        // base case
        if (nodeConnections.size() == 0) {
            visitedNodeDepths.set(startNode, 1);
            traversalVisitedNodes.remove(startNode);
            return 1;
        }

        // check cache
        if (visitedNodeDepths.get(startNode) > -1) {
            traversalVisitedNodes.remove(startNode);
            return visitedNodeDepths.get(startNode);
        }

        // dfs to calculate
        int maxDepth = 0;
        for (int nodeConnection : nodeConnections) {
            int thisDepth = findMaximumPathSize(
                nodeConnection,
                nodes,
                visitedNodeDepths,
                traversalVisitedNodes
            );
            if (thisDepth == -1) {
                return -1;
            }

            maxDepth = Math.max(thisDepth + 1, maxDepth);
        }

        visitedNodeDepths.set(startNode, maxDepth);
        traversalVisitedNodes.remove(startNode);

        return maxDepth;
    }
}