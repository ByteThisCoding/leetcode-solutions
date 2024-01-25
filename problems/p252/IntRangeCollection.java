/**
 * Keep track of multiple int ranges and detect collisions
 */
class IntRangeCollection {

    // treemap keeps sorted based on key
    TreeMap<Integer, Integer> ranges = new TreeMap<>();

    /**
     * [Open, closed]
     * Closed at end, since you can end a meeting and start another at the same time
     * 
     * Returns true if range intersects with existing range
     */
    public boolean addRangeIfNoIntersection(int start, int end) {
        // get the first key less than end
        Integer lowerRangeEnd = ranges.lowerKey(end);

        // if present, there is an intersection
        if (lowerRangeEnd != null && (lowerRangeEnd >= start || ranges.get(lowerRangeEnd) > start)) {
            return true;
        }

        // we could merge intervals, but let's have space redundancy for speed improvement
        ranges.put(start, end);
        return false;
    }
}