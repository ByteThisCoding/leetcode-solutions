package problems.p438;

import java.util.Arrays;

/**
 * Class which holds a fixed size circular buffer
 * This is a slightly modified version of the code taken from:
 * ==> https://bytethisstore.com/articles/pg/circular-buffer
 */
public class CircularBuffer<T> {

    // index to treat as the first item
    private int startIndex = 0;
    // index to treat as the index after the last item
    private int endIndex = 0;
    // internal array where the items will be stored
    private T[] items;

    // manually keep track of number of items
    private int numItems = 0;

    private int bufferSize;

    /**
     * The bufferSize must be provided and will remain constant
     */
    CircularBuffer(int bufferSize) {
        this.bufferSize = bufferSize;
        items = (T[]) new Object[bufferSize];
    }

    /**
     * Get the number of elements currently in the circular buffer
     */
    public int getNumElements() {
        return numItems;
    }

    public boolean isFull() {
        return numItems == bufferSize;
    }

    /**
     * Get the nth item
     * If there is no nth item or out of bounds, return void
     */
    public T getNthItem(int n) {
        // guard against invalid
        if (n >= bufferSize) {
            return null;
        }

        int index = (startIndex + n) % bufferSize;
        return items[index];
    }

    /**
     * Push an item
     * If the circular buffer is already full, remove and return that item
     * Otherwise, return null
     */
    public T push(T item) {
        T removedItem = null;
        if (getNumElements() == bufferSize) {
            removedItem = remove();
        }

        // insert item and update endIndex
        items[endIndex] = item;
        endIndex = (endIndex + 1) % bufferSize;

        // increment items count
        numItems++;

        return removedItem;
    }

    /**
     * Remove an item if there are any
     */
    public T remove() {
        // if there are no elements, return void
        if (getNumElements() == 0) {
            return null;
        }

        // otherwise, get the last element and update the pointer
        T removedItem = items[startIndex];
        items[startIndex] = null;

        // update start index
        startIndex = (startIndex + 1) % bufferSize;

        // decrement number of items
        numItems--;

        return removedItem;
    }

    public void clear() {
        items = (T[]) new Object[bufferSize];
        numItems = 0;
        startIndex = 0;
        endIndex = 0;
    }

    /**
     * Create a new array from the existing elements in the buffer
     * The order should be based on start and end index, not internal array
     */
    public T[] toArray() {
        T[] newAr = (T[]) new Object[this.numItems];
        // if empty, return immediately
        if (getNumElements() == 0) {
            return newAr;
        }

        // otherwise, proceed with iteration
        // add items from start index to end of array or endIndex
        int startEnd = endIndex > startIndex ? endIndex : bufferSize;
        for (int i = startIndex; i < startEnd; i++) {
            newAr[i - startIndex] = items[i];
        }

        // add items from 0 to endIndex
        int endEnd = endIndex <= startIndex ? endIndex : -1;
        for (int i = 0; i < endEnd; i++) {
            newAr[i + startEnd - startIndex] = items[i];
        }

        return newAr;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
