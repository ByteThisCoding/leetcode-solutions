package problems.p1791;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int findCenter(int[][] edges) {
        Set<Integer> outEdgesFound = new HashSet<>();

        for (int[] edge : edges) {
            // if this vertex has an edge already, it definitely is the center
            if (outEdgesFound.contains(edge[0])) {
                return edge[0];
            }
            // otherwise, add to set
            outEdgesFound.add(edge[0]);

            // perform the same operation for the second vertex
            if (outEdgesFound.contains(edge[1])) {
                return edge[1];
            }
            // otherwise, add to set
            outEdgesFound.add(edge[1]);
        }

        // default case, will never be reached
        return -1;
    }
}