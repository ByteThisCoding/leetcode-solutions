class Solution {
    public void rotate(int[][] matrix) {
        rotateRing(matrix, 0);
    }

    private void rotateRing(int[][] matrix, int ringNum) {
        // calculate the ring side length
        // -1 to ensure we don't overlap corners
        int ringSideLength = matrix[0].length - 2*ringNum - 1;

        // boundary condition, we've finished all rings
        if (ringSideLength < 1) {
            return;
        }

        // iterate over each element in each ring
        // there will always be four positions per each item in a ring side
        for (int i=0; i<ringSideLength; i++) {
            // first ring side, row is constant
            int rowA = ringNum;
            int colA = ringNum + i;

            // second ring side, col is constant
            int rowB = ringNum + i;
            int colB = matrix.length - 1 - ringNum;

            // third ring side, row is constant
            int rowC = matrix.length - 1 - ringNum;
            int colC = matrix.length - 1 - ringNum - i;

            // fourth ring side, col is constant
            int rowD = matrix.length - 1 - ringNum - i;
            int colD = ringNum;

            // put one pos in temp variable, then swap others
            int tempValue = matrix[rowD][colD];

            matrix[rowD][colD] = matrix[rowC][colC];
            matrix[rowC][colC] = matrix[rowB][colB];
            matrix[rowB][colB] = matrix[rowA][colA];
            matrix[rowA][colA] = tempValue;
        }

        // proceed with inner ring
        rotateRing(matrix, ringNum + 1);
    }
}