package problems.p1971bfsApproach;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // check base cases
        if (source == destination) {
            return true;
        }
        if (edges.length == 0) {
            return false;
        }

        // proceed to graph creation
        ArrayList<HashSet<Integer>> graph = createGraph(n, edges);
        return connectedGraphHasValue(source, destination, graph);
    }

    /**
     * Create an ArrayList representation of the input graph
     * 
     * Key is a node value, but since values are 0-n,
     * we can use ArrayLists instead of maps
     * 
     * Value is a set of node values the key connects to
     */
    private ArrayList<HashSet<Integer>> createGraph(int n, int[][] edges) {
        ArrayList<HashSet<Integer>> list = new ArrayList<>(n);

        // initialize with sets
        for (int i = 0; i < n; i++) {
            // create set for n
            list.add(new HashSet<Integer>());
        }

        for (int[] edge : edges) {
            // update values in sets
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }

        return list;
    }

    /**
     * Check if a subgraph starting with "nodeValue" contains "searchValue"
     * This uses an iterative depth-first search
     */
    private boolean connectedGraphHasValue(Integer nodeValue, Integer searchValue,
            ArrayList<HashSet<Integer>> graph) {

        HashSet<Integer> visitedNodes = new HashSet<>();
        Queue<Integer> nodesToVisit = new LinkedList<>();
        nodesToVisit.add(nodeValue);

        // normally, we'd check here, but in this problem, no node connects to itself

        while (nodesToVisit.size() > 0) {
            Integer currentNode = nodesToVisit.poll();
            visitedNodes.add(currentNode);

            for (Integer nextNode : graph.get(currentNode)) {
                if (!visitedNodes.contains(nextNode)) {
                    // we can check here to save time
                    if (nextNode.equals(searchValue)) {
                        return true;
                    }

                    nodesToVisit.add(nextNode);
                }
            }
        }

        return false;
    }
}