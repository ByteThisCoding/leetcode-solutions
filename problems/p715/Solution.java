import java.util.TreeMap;

class RangeModule {

    // store the left parts of the range and right part seperately
    private TreeMap<Integer, Integer> ranges = new TreeMap<>();
    
    /**
     * Remove all intersecting ranges, then add the new range
     */
    public void addRange(int left, int right) {
        int[] entry = deleteAllIntersectingRange(left, right);

        ranges.put(entry[0], entry[1]);
    }
    
    /**
     * Check if the map includes a range and that it expands far enough
     */
    public boolean queryRange(int left, int right) {
        Integer rangeLeft = ranges.floorKey(left);
        if (rangeLeft == null) {
            return false;
        }
        Integer rangeRight = ranges.get(rangeLeft);
        return rangeRight >= right;
    }
    
    /**
     * Remove all intersecting ranges, then if needed, re-introduce partial ranges
     */
    public void removeRange(int left, int right) {
        int[] entry = deleteAllIntersectingRange(left, right);
        
        if (entry[0] < left) {
            ranges.put(entry[0], left);
        }
        if (entry[1] > right) {
            ranges.put(right, entry[1]);
        }
    }

    /**
     * This deletes all ranges that are touched by range left->right
     * This is used in both insertions and removals
     * Returns the expanded entries
     */
    private int[] deleteAllIntersectingRange(int left, int right) {
        // keep track of the final range values to insert once merged
        int entryLeft = left;
        int entryRight = right;
        // iterate and remove overlapping ranges
        while (true) {
            // check if the entry immediately before the right value causes an overlap
            Integer rangeOverlapLeft = ranges.floorKey(entryRight);
            // if no conflict, exit
            if (rangeOverlapLeft == null || ranges.get(rangeOverlapLeft) < entryLeft) {
                break;
            }
            // extend the left right range borders to ensure we encompass the entire range
            entryLeft = Math.min(entryLeft, rangeOverlapLeft);
            entryRight = Math.max(entryRight, ranges.get(rangeOverlapLeft));
            // remove this intermediate range so there are no overlaps
            ranges.remove(rangeOverlapLeft);
        }

        return new int[] {entryLeft, entryRight};
    }
}
