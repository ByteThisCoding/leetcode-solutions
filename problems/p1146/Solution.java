class SnapshotArray {

    private int[] elements;
    private int currentSnap = 0;
    private List<List<Integer>> elementSnapshotChanges;
    private List<Map<Integer, Integer>> revHistory = new ArrayList<>();

    public SnapshotArray(int length) {
        elements = new int[length];
        // initialize record of change history
        elementSnapshotChanges = new ArrayList<>();
        for (int i=0; i<length; i++) {
            elementSnapshotChanges.add(
                new ArrayList<>()
            );
        }
    }
    
    /**
     * Set a value, and if not yet done, update the map
     * to indicate a revision has taken place
     */
    public void set(int index, int val) {
        int oldValue = elements[index];
        elements[index] = val;

        // update revision history if needed
        if (currentSnap > 0
            && !revHistory.get(currentSnap - 1).containsKey(index)) {

                revHistory.get(currentSnap - 1).put(index, oldValue);
                elementSnapshotChanges.get(index).add(currentSnap - 1);
        }
    }
    
    /**
     * When we snap, add a revision history map
     */
    public int snap() {
        revHistory.add(new HashMap<>());
        currentSnap ++;
        return currentSnap - 1;
    }
    
    /**
     * Use binary search to find the most recent snapshot that changed 
     * the value of the index
     * 
     * If none found, return current value
     */
    public int get(int index, int snap_id) {
        // get the most recent update snap before snap_id
        int mostRecent = findSnapshotIndex(index, snap_id);
        if (mostRecent == -1) {
            return elements[index];
        }

        // grab the value
        return revHistory.get(mostRecent).get(index);
    }

    /**
     * Use binary search to find the snapshot index corresponding to snap_id
     * Find lowest index >= snap_id or -1 if none relevant
     */
    private int findSnapshotIndex(int index, int snap_id) {
        List<Integer> indexChanges = elementSnapshotChanges.get(index);
        int left = 0;
        int right = indexChanges.size();

        // we want to return the smallest value greater than snap_id
        int lowestGreaterThanEqual = -1;
        while (right > left) {
            int binarySearchIndex = (right + left) / 2;
            int thisChange = indexChanges.get(binarySearchIndex);
            // if equal, return exact match
            if (thisChange == snap_id) {
                lowestGreaterThanEqual = thisChange;
                break;
            }

            // if higher
            if (thisChange > snap_id) {
                right = binarySearchIndex;
                lowestGreaterThanEqual = thisChange;
            } else {
                // if lower
                left = binarySearchIndex + 1;
            }
        }

        return lowestGreaterThanEqual;
    }
}