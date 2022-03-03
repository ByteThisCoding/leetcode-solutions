package problems.p2048;

public class Solution {

    public int nextBeautifulNumber(int n) {
        DigitLinkedList numAsList = new DigitLinkedList(n + 1);

        while (true) {
            boolean isBalanced = true;
            // check if this is a balanced number
            for (int i = 0; i < 10; i++) {
                if (numAsList.digitCounts[i] > 0 && numAsList.digitCounts[i] != i) {
                    isBalanced = false;
                    break;
                }
            }

            if (isBalanced) {
                return numAsList.toInt();
            }

            numAsList.increment();
        }
    }
}
