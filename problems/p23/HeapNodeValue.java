package problems.p23;

/**
 * Used by the heap to sort values and their linked list indices
 */
public class HeapNodeValue {

    public int value;
    public int linkedListIndex;

    HeapNodeValue(int value, int linkedListIndex) {
        this.value = value;
        this.linkedListIndex = linkedListIndex;
    }
}
