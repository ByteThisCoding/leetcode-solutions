import java.math.BigInteger;

class Solution {

    /**
     * Solve by:
     * 1. For each bomb, check which bombs it blows up immediately (ignorning chain reaction)
     * 2. Perform Breadth-First-Search to find the largest sequence
     */
    public int maximumDetonation(int[][] bombs) {
        // for each bomb, check others it can immediately blow up (without chain)
        List<List<Integer>> firstLevelDetonations = new ArrayList<>();
        // initialize
        for (int i=0; i<bombs.length; i++) {
            firstLevelDetonations.add(new ArrayList<>());
        }

        for (int i=0; i<bombs.length-1; i++) {
            for (int j=i+1; j<bombs.length; j++) {
                // if bombs[i] explodes bombs[j], add to list
                if (doesBombDetonateBomb(bombs[i], bombs[j])) {
                    firstLevelDetonations.get(i).add(j);
                }
                if (doesBombDetonateBomb(bombs[j], bombs[i])) {
                    firstLevelDetonations.get(j).add(i);
                }
            }
        }

        // now, traverse as graph and find largest connected subgraph
        boolean[] isNodeVisited = new boolean[bombs.length];
        int largestChainSize = 0;
        for (int i=0; i<bombs.length; i++) {
            if (isNodeVisited[i]) {
                continue;
            }

            int thisChainSize = getConnectedSubgraphSize(
                bombs,
                i,
                isNodeVisited,
                firstLevelDetonations
            );
            largestChainSize = Math.max(largestChainSize, thisChainSize);
        }

        return largestChainSize;
    }

    private int getConnectedSubgraphSize(
        int[][] bombs,
        int startIndex,
        boolean[] isNodePreviouslyVisited,
        List<List<Integer>> firstLevelDetonations
    ) {
        boolean[] isNodeVisited = new boolean[bombs.length];
        Queue<Integer> nodesToVisit = new LinkedList<>();
        nodesToVisit.add(startIndex);

        int numDetonations = 0;

        while (nodesToVisit.size() > 0) {
            int index = nodesToVisit.remove();
            // ignore if we've already visited
            if (isNodeVisited[index]) {
                continue;
            }

            // if this is a new element to a connected graph we found earlier, add to the ref
            isNodeVisited[index] = true;
            isNodePreviouslyVisited[index] = true;
            numDetonations ++;

            List<Integer> detonationsFromThisBomb = firstLevelDetonations.get(index);
            for (int bombDetonatedIndex : detonationsFromThisBomb) {
                nodesToVisit.add(bombDetonatedIndex);
            }
        }

        return numDetonations;
    }

    /**
     * Check if bomb A detonates bomb B (not necessarily if the reverse)
     * Use BigInt because of the size of the inputs we receive
     */
    private boolean doesBombDetonateBomb(int[] bombA, int[] bombB) {
        BigInteger x1 = BigInteger.valueOf(bombA[0]);
        BigInteger y1 = BigInteger.valueOf(bombA[1]);
        BigInteger r1 = BigInteger.valueOf(bombA[2]);
        
        BigInteger x2 = BigInteger.valueOf(bombB[0]);
        BigInteger y2 = BigInteger.valueOf(bombB[1]);
        
        BigInteger dx = x1.subtract(x2);
        BigInteger dy = y1.subtract(y2);
        
        BigInteger distanceSquared = dx.multiply(dx).add(dy.multiply(dy));
        BigInteger radiusSquared = r1.multiply(r1);
        
        // no need to square root, since inequality holds either way
        return distanceSquared.compareTo(radiusSquared) <= 0;
    }
}

/**
 * For each bomb:
 *  - Check all bombs it can blow up (n squared???)
 * 
 * tell if bomb detonates another bomb
 *  - distance = sqrt((x2-x1)^2+(y2-y1)^2)
 */