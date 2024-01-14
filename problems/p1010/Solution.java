class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int numPairs = 0;

        // record pair items and number of times they occur
        int[] partialPairs = new int[61];

        for (int timeItem : time) {
            timeItem = timeItem % 60;

            // check if this number helps to form a pair
            numPairs += partialPairs[timeItem];

            // modulus in case timeItem == 0
            int pairPartner = (60 - timeItem) % 60;

            // record this number's partner in the partial pairs
            partialPairs[pairPartner] += 1;
        }

        return numPairs;
    }
}