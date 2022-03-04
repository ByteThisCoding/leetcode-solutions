package problems.p1530;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Solution {

    // we'll use this outside of the recursive method for convenience
    private int pairCount = 0;

    public int countPairs(TreeNode root, int distance) {
        pairCount = 0;
        countPairsFromNode(root, distance);
        return pairCount;
    }

    /**
     * We'll use sorted maps to map the depth of each leaf to the
     * number of leaves with that depth
     * 
     * The map will have its keys sorted; this way we can get submaps more
     * efficiently, which we need in order to satisfy *less than or* equal
     */
    private SortedMap<Integer, Integer> countPairsFromNode(TreeNode root, int distance) {
        SortedMap<Integer, Integer> totalDepthCounts = new TreeMap<>();

        // base case, root is null
        if (root == null) {
            return totalDepthCounts;
        }

        // get sets for left & right subtrees
        SortedMap<Integer, Integer> leftDepthSet = countPairsFromNode(root.left, distance);
        SortedMap<Integer, Integer> rightDepthSet = countPairsFromNode(root.right, distance);

        // find all pairs from left to right
        // copy sub depth sets to total
        Set<Integer> rightExcludeValues = new HashSet<>();
        for (Entry<Integer, Integer> depth : leftDepthSet.entrySet()) {
            int depthPlus = depth.getKey() + 1;
            Integer compliment = distance - depthPlus;
            // get all items <= compliment
            if (compliment > 0) {
                SortedMap<Integer, Integer> subMap = rightDepthSet.subMap(0, compliment);

                for (Entry<Integer, Integer> complimentEntry : subMap.entrySet()) {
                    pairCount += complimentEntry.getValue() * depth.getValue();
                    rightExcludeValues.add(complimentEntry.getKey());
                }
            }

            totalDepthCounts.put(
                    depthPlus, totalDepthCounts.getOrDefault(depthPlus, 0) + depth.getValue());
        }

        // add right side to tree
        for (Entry<Integer, Integer> depth : rightDepthSet.entrySet()) {
            if (!rightExcludeValues.contains(depth.getKey())) {
                Integer compliment = distance - depth.getKey() - 1;
                // get all items <= compliment
                if (compliment > 0) {
                    SortedMap<Integer, Integer> subMap = leftDepthSet.subMap(0, compliment);

                    for (Entry<Integer, Integer> complimentEntry : subMap.entrySet()) {
                        pairCount += complimentEntry.getValue() * depth.getValue();
                    }
                }
            }

            int depthPlus = depth.getKey() + 1;
            totalDepthCounts.put(
                    depthPlus, totalDepthCounts.getOrDefault(depthPlus, 0) + depth.getValue());
        }

        // if this is a leaf, add 0 to totalDepthCounts
        if (leftDepthSet.size() == 0 && rightDepthSet.size() == 0) {
            totalDepthCounts.put(0, 1);
        }

        return totalDepthCounts;
    }
}
