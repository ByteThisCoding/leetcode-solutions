package problems.p1782;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Solution {

    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] nodeDegrees = new int[n + 1];
        Map<Integer, Map<Integer, Integer>> edgeDegrees = new HashMap<>();

        // calculate node and edge degrees
        for (int[] edge : edges) {
            // add node degrees for each node in the edge

            nodeDegrees[edge[0]]++;
            nodeDegrees[edge[1]]++;

            // associate the min node with the max node and increment edge count
            int minNode = Math.min(edge[0], edge[1]);
            int maxNode = Math.max(edge[0], edge[1]);

            if (!edgeDegrees.containsKey(minNode)) {
                edgeDegrees.put(minNode, new HashMap<>());
            }
            Map<Integer, Integer> minMap = edgeDegrees.get(minNode);
            minMap.put(maxNode, minMap.getOrDefault(maxNode, 0) + 1);
        }

        // sort node degrees
        int[] sortedDegrees = Arrays.copyOf(nodeDegrees, n + 1);
        Arrays.sort(sortedDegrees);

        // for each query, find out how many pairs st[left]+st[right] add to > query
        int[] answers = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int count = 0;

            // search for satisfying pairs
            int left = 1;
            int right = n;
            while (right > left) {
                // if the left + right satisfies the query, so will (left+1) + right, etc.
                // add all of those entries together and increment count accordingly
                if (sortedDegrees[right] + sortedDegrees[left] > queries[i]) {
                    count += (right - left);
                    right--;
                } else {
                    // otherwise, increment left to help with next iteratino
                    left++;
                }
            }

            // use edge degrees to filter out any duplicates in previous counts
            for (Entry<Integer, Map<Integer, Integer>> degreeCount : edgeDegrees.entrySet()) {
                Integer nodeA = degreeCount.getKey();
                int nodeADegree = nodeDegrees[nodeA];

                // iterate over b nodes
                for (Entry<Integer, Integer> bCount : degreeCount.getValue().entrySet()) {
                    Integer nodeB = bCount.getKey();
                    int nodeBDegree = nodeDegrees[nodeB];
                    int edgeDegree = bCount.getValue();

                    int sumDegree = nodeADegree + nodeBDegree;
                    int adjustedDegree = sumDegree - edgeDegree;

                    // query < sumDegree means the pair was included above
                    // adjustedDegree <= query means with edgeDegree removed, it is below threshold
                    if (adjustedDegree <= queries[i] && queries[i] < sumDegree) {
                        count--;
                    }
                }
            }

            answers[i] = count;
        }

        return answers;
    }
}
