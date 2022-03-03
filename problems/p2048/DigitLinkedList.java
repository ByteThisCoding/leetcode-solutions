package problems.p2048;

/**
 * A reverse link of digits which represents a number
 * Each node corresponds to a digit
 * 
 * This will keep track of number of digits
 */
public class DigitLinkedList {

    // first digit will keep track of counts
    public int[] digitCounts = new int[10];

    // the digit in this part of the list
    private int digit;

    // refs to other parts of the list
    private DigitLinkedList prev = null;
    private DigitLinkedList next = null;

    /**
     * Create the linked list and initialize with some number
     */
    public DigitLinkedList(int startInt) {
        initializeFromNumber(startInt);
    }

    /**
     * For internal use only, create a new (sub) linked list and set prev reference
     */
    private DigitLinkedList(int startInt, DigitLinkedList prev) {
        this.prev = prev;
        initializeFromNumber(startInt);
    }

    /**
     * Convert the list back to an integer
     */
    public int toInt() {
        int subValue = 0;
        if (next != null) {
            subValue = next.toInt() * 10;
        }
        return subValue + digit;
    }

    /**
     * Increment the digits and update the counts accordingly
     */
    public void increment() {
        int prevDigit = digit;
        if (digit == 9) {
            digit = 0;
            if (next == null) {
                next = new DigitLinkedList(1, this);
            } else {
                next.increment();
            }
        } else {
            digit++;
        }

        // update counts for digits
        decrementDigitCount(prevDigit);
        incrementDigitCount(digit);
    }

    /**
     * Create a string representing the linked list (digits in reverse order)
     */
    public String toString() {
        String str = digit + "";
        if (next != null) {
            str += " => " + next.toString();
        }
        return str;
    }

    /**
     * Increment the count of a digit
     * Only the top level will keep the map of digits to their counts
     */
    private void incrementDigitCount(int digit) {
        if (prev == null) {
            digitCounts[digit]++;
        } else {
            prev.incrementDigitCount(digit);
        }
    }

    /**
     * Decrement the count of a digit
     * Only the top level will keep the map of digits to their counts
     */
    private void decrementDigitCount(int digit) {
        if (prev == null) {
            digitCounts[digit]--;
        } else {
            prev.decrementDigitCount(digit);
        }
    }

    /**
     * Update this number + next based on start int
     */
    private void initializeFromNumber(int startInt) {
        digit = startInt % 10;
        if (startInt > 9) {
            next = new DigitLinkedList(startInt / 10, this);
        }
        incrementDigitCount(digit);
    }
}
