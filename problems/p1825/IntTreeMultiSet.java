class IntSortedMultiSet {

    // store the ints
    private TreeMap<Integer, Integer> map = new TreeMap<>();

    public void insert(int value) {
        map.put(
            value,
            map.getOrDefault(value, 0) + 1
        );
    }

    /**
     * Remove a value
     * If multiple instances of this value exist, remove only one
     */
    public void remove(int value) {
        if (map.containsKey(value)) {
            map.put(
                value,
                map.getOrDefault(value, 1) - 1
            );
            if (map.get(value) == 0) {
                map.remove(value);
            }
        }
    }

    public int getSumOfFirstKElements(int k) {
        int sum = 0;
        int numElements = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int numAdditions = Math.min(entry.getValue(), k - numElements);
            sum += entry.getKey() * numAdditions;
            numElements += numAdditions;
            if (numElements == k) {
                return sum;
            }
        }

        return sum;
    }

    public int getSumOfLastKElements(int k) {
        int sum = 0;
        int numElements = 0;
        for (Map.Entry<Integer, Integer> entry : map.descendingMap().entrySet()) {
            int numAdditions = Math.min(entry.getValue(), k - numElements);
            sum += entry.getKey() * numAdditions;
            numElements += numAdditions;
            if (numElements == k) {
                return sum;
            }
        }

        return sum;
    }
}