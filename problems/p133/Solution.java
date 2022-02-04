package problems.p133;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {

    /**
     * Use BFS to clone the graph
     * Our cache uses node references, we could also use node value for keys
     */
    public Node cloneGraph(Node node) {
        // Base case: empty graph
        if (node == null) {
            return null;
        }

        // map original nodes to their cloned counterparts
        Map<Node, Node> cloneMap = new HashMap<>();
        cloneMap.put(node, new Node(node.val));

        Queue<Node> nodesToVisit = new LinkedList<>();
        nodesToVisit.add(node);

        // perform a bfs to clone as we go + reuse cloneMap to keep track of visited
        while (nodesToVisit.size() > 0) {
            Node currentNode = nodesToVisit.poll();
            Node clonedCurrentNode = cloneMap.get(currentNode);

            for (Node neighbor : currentNode.neighbors) {
                Node clonedNeighbor;
                // if this was already cloned
                if (cloneMap.containsKey(neighbor)) {
                    clonedNeighbor = cloneMap.get(neighbor);
                } else {
                    clonedNeighbor = new Node(neighbor.val);
                    cloneMap.put(neighbor, clonedNeighbor);
                    nodesToVisit.add(neighbor);
                }

                clonedCurrentNode.neighbors.add(clonedNeighbor);
            }
        }

        return cloneMap.get(node);
    }
}
