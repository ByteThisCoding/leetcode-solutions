package problems.p11;

public class Solution {

    /**
     * Area at any given time is min of two heights
     * multiplied by the amount of horizontal space between them
     */
    public int maxArea(int[] height) {
        int posLeft = 0;
        int posRight = height.length - 1;

        int bestArea = 0;
        while (posRight > posLeft) {
            // take min of two, as if we took max, water would spill over
            int thisArea = Math.min(height[posLeft], height[posRight]) * (posRight - posLeft);

            // update if we've just found a better area
            if (thisArea > bestArea) {
                bestArea = thisArea;
            }

            // move the cursor of the pointer which has smaller height
            if (height[posLeft] < height[posRight]) {
                posLeft++;
            } else {
                posRight--;
            }
        }

        return bestArea;
    }
}
