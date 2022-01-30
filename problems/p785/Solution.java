package problems.p785;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {

    public boolean isBipartite(int[][] graph) {
        // use a map to record each vertex and if it's in set A (false implies set B)
        // this can be reused to check if a node has already been visited
        Map<Integer, Boolean> setMemberships = new HashMap<>();

        // perform a bfs for each connected subgraph, or 1 if entire graph is connected
        for (Integer node = 0; node < graph.length; node++) {
            if (!setMemberships.containsKey(node)) {
                if (!connectedIsBipartite(node, graph, setMemberships)) {
                    return false;
                } else if (setMemberships.size() == graph.length) {
                    // we can stop iterating if entire graph has been traversed
                    return true;
                }
            }
        }

        return true;
    }

    /**
     * Run a BFS on a single connected graph
     */
    private boolean connectedIsBipartite(int startNode, int[][] graph, Map<Integer, Boolean> setMemberships) {
        // breadth-first search: record nodes to visit
        Queue<Integer> nodesToVisit = new LinkedList<>();
        nodesToVisit.add(startNode);
        setMemberships.put(startNode, true);

        // perform bfs
        while (nodesToVisit.size() > 0) {
            Integer node = nodesToVisit.poll();

            for (int connectedNode : graph[node]) {
                // if it's been visited, ensure bipartite is not violated
                if (setMemberships.containsKey(connectedNode)) {
                    // if the connected node is the same set as current, definitely not bipartite
                    if (setMemberships.get(connectedNode).equals(setMemberships.get(node))) {
                        return false;
                    }
                } else {
                    // add to set memberships and to nodes to visit
                    setMemberships.put(connectedNode, !setMemberships.get(node));
                    nodesToVisit.add(connectedNode);
                }
            }
        }

        return true;
    }
}
