class Solution {
    public int bestClosingTime(String customers) {

        // count the number of 'N' before, not including current pos
        // and 'Y' after, including pos
        int smallestNyCount = Integer.MAX_VALUE;
        int smallestNyCountIndex = -1;

        int nCount = 0;
        int yCount = 0;
        // find the min of their sums
        for (int i=0; i<customers.length(); i++) {
            int thisNyCount = nCount - yCount;
            if (thisNyCount < smallestNyCount) {
                smallestNyCount = thisNyCount;
                smallestNyCountIndex = i;
            }

            if (customers.charAt(i) == 'N') {
                nCount ++;
            } else {
                yCount ++;
            }
        }

        // edge case, best to be open at all times
        if (nCount - yCount < smallestNyCount) {
            smallestNyCountIndex = customers.length();
        }

        return smallestNyCountIndex;
    }
}