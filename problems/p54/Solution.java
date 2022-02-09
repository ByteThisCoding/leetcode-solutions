package problems.p54;

import java.util.LinkedList;
import java.util.List;

public class Solution {

    public List<Integer> spiralOrder(int[][] matrix) {
        // store output in a list
        List<Integer> solutionList = new LinkedList<>();

        // use offsets to move through the matrix
        int xLeft = 0;
        int xRight = matrix[0].length - 1;
        int yTop = 0;
        int yBottom = matrix.length - 1;

        // determine num elements total
        int numTotal = matrix[0].length * matrix.length;

        while (true) {
            // move at the top from left to right
            for (int x = xLeft; x <= xRight; x++) {
                solutionList.add(matrix[yTop][x]);
            }

            // increment yTop for future reference
            yTop++;

            // check if we've reached the end
            if (solutionList.size() == numTotal) {
                break;
            }

            // move at the right from top to bottom
            for (int y = yTop; y <= yBottom; y++) {
                solutionList.add(matrix[y][xRight]);
            }

            // decrement xRight for future reference
            xRight--;

            // check if we've reached the end
            if (solutionList.size() == numTotal) {
                break;
            }

            // move at the bottom from right to left
            for (int x = xRight; x >= xLeft; x--) {
                solutionList.add(matrix[yBottom][x]);
            }

            // decrement yBottom for future reference
            yBottom--;

            // check if we've reached the end
            if (solutionList.size() == numTotal) {
                break;
            }

            // move at the left from bottom to right
            for (int y = yBottom; y >= yTop; y--) {
                solutionList.add(matrix[y][xLeft]);
            }

            // increment xLeft for future reference
            xLeft++;

            // check if we've reached the end
            if (solutionList.size() == numTotal) {
                break;
            }
        }

        return solutionList;
    }
}
