class IntCircularBuffer {

    private int[] values;
    private int numElements = 0;
    private int insertionIndex = 0;

    public IntCircularBuffer(int size) {
        values = new int[size];
    }

    /**
     * Returns the value that was removed, or 0 if none
     */
    public int add(int value) {
        int removedValue = values[insertionIndex];

        values[insertionIndex] = value;
        insertionIndex = (insertionIndex + 1) % values.length;
        numElements = Math.min(numElements + 1, values.length);
        
        return removedValue;
    }

    /**
     * For this leetcode problem, we don't want to operate on
     * a partially filled buffer
     */
    public int[] getValuesIfFilled() {
        if (numElements == values.length) {
            return values;
        }

        return new int[0];
    }
}