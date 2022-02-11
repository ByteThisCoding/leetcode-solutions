package problems.p200;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    /**
     * The question is equivalent to asking:
     * how many connected components are there?
     */
    public int numIslands(char[][] grid) {
        int numConnected = 0;

        Map<Integer, Set<Integer>> visited = new HashMap<>();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == '1' && !isVisited(row, col, visited)) {
                    numConnected++;
                    traverseIslandComponent(row, col, grid, visited);
                }
            }
        }

        return numConnected;
    }

    private void traverseIslandComponent(int row, int col, char[][] grid, Map<Integer, Set<Integer>> visited) {
        // guard: out of bounds
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length) {
            return;
        }

        // if not an island, don't proceed
        if (grid[row][col] != '1') {
            return;
        }

        // guard: don't proceed if already visited
        if (isVisited(row, col, visited)) {
            return;
        }

        // mark current position as visited
        markVisited(row, col, visited);

        // traverse children: check left, right, up, down
        traverseIslandComponent(row, col - 1, grid, visited);
        traverseIslandComponent(row, col + 1, grid, visited);
        traverseIslandComponent(row - 1, col, grid, visited);
        traverseIslandComponent(row + 1, col, grid, visited);
    }

    /**
     * Utility to check if a position has been visited
     */
    private boolean isVisited(int row, int col, Map<Integer, Set<Integer>> visited) {
        if (!visited.containsKey(row)) {
            return false;
        }

        return visited.get(row).contains(col);
    }

    /**
     * Utility to mark a cell as being visited
     */
    private void markVisited(int row, int col, Map<Integer, Set<Integer>> visited) {
        if (!visited.containsKey(row)) {
            visited.put(row, new HashSet<>());
        }
        visited.get(row).add(col);
    }
}
