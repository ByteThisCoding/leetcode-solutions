package problems.p779;

public class Solution {

    /**
     * Solve the problem by using a concept analagous to binary search
     * Calc the string length and use k to decide which branches to follow
     */
    public int kthGrammar(int n, int k) {
        // we'll be comparing the halfway point with k
        int strLenHalf = (int) Math.pow(2, n - 2);
        boolean isZero = true;

        // iterate through the tree depths
        while (n > 1) {
            // if k is on the right side, flip is 0
            if (k > strLenHalf) {
                isZero = !isZero;
                k -= strLenHalf;
            }

            // decrement n and halve the string length
            n--;
            strLenHalf /= 2;
        }

        return isZero ? 0 : 1;
    }
}
