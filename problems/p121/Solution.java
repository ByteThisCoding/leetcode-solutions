class Solution {

    public int maxProfit(int[] prices) {
        // keep track of best found so far
        int bestDiff = 0;

        // keep track of working indices
        int left = 0;
        int right = 0;

        // default this to a high value
        int currentMin = Integer.MAX_VALUE;

        // keep iterating through values until the right margin is out of bounds
        while (true) {
            // if the right index points to a value smaller than the left,
            // replace the left value with the right value, we've found a new min
            if (prices[right] < currentMin) {
                currentMin = prices[right];
                left = right;
            }

            // increment to look at the next item
            right += 1;

            // bounds check
            if (right >= prices.length) {
                break;
            }

            // if the difference is better, update the best diff found so far
            int diff = prices[right] - prices[left];
            if (diff > bestDiff) {
                bestDiff = diff;
            }
        }

        return bestDiff;
    }
}