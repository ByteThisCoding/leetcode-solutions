import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        // keep track so we can initialize array later
        int numElements = 0;

        // use lists to keep track of items in each diagonal
        List<List<Integer>> diagonals = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> row = nums.get(i);

            for (int j = 0; j < row.size(); j++) {
                int index = i + j;

                // fill in if we don't have enough lists yet
                if (diagonals.size() <= index) {
                    diagonals.add(new ArrayList<>());
                }

                // add this entry to the diagonal
                diagonals.get(index).add(row.get(j));
                
                numElements ++;
            }
        }

        // create the final output array
        int[] res = new int[numElements];
        int index = 0;

        for (List<Integer> diagonal : diagonals) {
            // iterate backwards, since the last elements are always added first
            // (bottom left to top right)
            for (int i = diagonal.size() - 1; i >= 0; i--) {
                res[index] = diagonal.get(i); 
                index ++;
            }
        }
        return res;
    }
}