class Solution {
    public int getMaximumConsecutive(int[] coins) {
        // sort the array
        Arrays.sort(coins);

        int consecutiveCount = 1; //including 0
        // iterate over until we find a value that is larger than the largest consecutive
        // if so, in a sorted list, every coin after will be greater, so we've found the gap
        for (int coin : coins) {
            // if this coin is larger than the consecutive found so far,
            // we've found our gap and can't continue
            if (coin > consecutiveCount) {
                break;
            }

            consecutiveCount += coin;
        }

        return consecutiveCount;
    }
}