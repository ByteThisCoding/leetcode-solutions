import java.util.LinkedList;
import java.util.List;

class Solution {

    public int minCost(int[][] grid) {
        // keep track of visited positions
        // visited[i] = grid[i % grid.length][i - grid.length];
        int width = grid[0].length;
        int numPositions = grid.length*width;
        boolean[] cellIsVisited = new boolean[numPositions];
        cellIsVisited[0] = true;

        int currentCost = 0;

        List<Integer> visitedNodes = traversePath(
            grid,
            width,
            numPositions,
            cellIsVisited,
            currentCost,
            0
        );

        if (visitedNodes.size() == 1 && visitedNodes.get(0) == numPositions - 1) {
            return currentCost;
        }

        // keep iterating until we find the node
        while (visitedNodes.size() > 0) {
            currentCost ++;

            List<Integer> newVisitedNodes = new ArrayList<>();
            for (int node : visitedNodes) {
                // for each unvisited bordering node, visit if valid
                // check right
                int rightNode = node + 1;
                if (rightNode % width != 0 && rightNode < numPositions && !cellIsVisited[rightNode]) {
                    List<Integer> newNodes = traversePath(
                        grid,
                        width,
                        numPositions,
                        cellIsVisited,
                        currentCost,
                        rightNode
                    );
                    if (newNodes.size() == 1 && newNodes.get(0) == numPositions - 1) {
                        //System.out.println("right");
                        return currentCost;
                    }
                    newVisitedNodes.addAll(newNodes);
                }

                // left node
                int leftNode = node - 1;
                if (node % width != 0 && leftNode % width > 0 && !cellIsVisited[leftNode]) {
                    List<Integer> newNodes = traversePath(
                        grid,
                        width,
                        numPositions,
                        cellIsVisited,
                        currentCost,
                        leftNode
                    );
                    if (newNodes.size() == 1 && newNodes.get(0) == numPositions - 1) {
                        //System.out.println("left");
                        return currentCost;
                    }
                    newVisitedNodes.addAll(newNodes);
                }

                // lower node
                int lowerNode = node + width;
                if (lowerNode < numPositions && !cellIsVisited[lowerNode]) {
                    List<Integer> newNodes = traversePath(
                        grid,
                        width,
                        numPositions,
                        cellIsVisited,
                        currentCost,
                        lowerNode
                    );
                    if (newNodes.size() == 1 && newNodes.get(0) == numPositions - 1) {
                        //System.out.println("down");
                        return currentCost;
                    }
                    newVisitedNodes.addAll(newNodes);
                }

                // upper node
                int upperNode = node - width;
                if (upperNode >= 0 && !cellIsVisited[upperNode]) {
                    List<Integer> newNodes = traversePath(
                        grid,
                        width,
                        numPositions,
                        cellIsVisited,
                        currentCost,
                        upperNode
                    );
                    if (newNodes.size() == 1 && newNodes.get(0) == numPositions - 1) {
                        //System.out.println("up");
                        return currentCost;
                    }
                    newVisitedNodes.addAll(newNodes);
                }
            }

            visitedNodes = newVisitedNodes;
        }

        return -1;
    }

    /**
     * Traverse a single path in the grid
     * Returns a list of visited indices
     */
    private List<Integer> traversePath(
        int[][] grid,
        int width,
        int numPositions,
        boolean[] cellIsVisited,
        int currentCost,
        int cursor
    ) {
        List<Integer> visitedCells = new LinkedList<>();
        visitedCells.add(cursor);
        cellIsVisited[cursor] = true;

        // base case, this cell to visit is a target cell
        if (cursor == numPositions - 1) {
            visitedCells = new LinkedList<>();
            visitedCells.add(cursor);
            cellIsVisited[cursor] = true;
            return visitedCells;
        }

        while (true) {
            // keep moving until outside of grid or at position already visited
            boolean isEndOfPath = false;
            int row = cursor / width;
            int col = cursor % width;

            switch (grid[row][col]) {
                case 1:
                    // move right unless out of bounds
                    if (cursor % width == width - 1) {
                        isEndOfPath = true;
                    } else {
                        cursor ++;
                    }
                    break;
                case 2:
                    // move left unless out of bounds
                    if (cursor % width == 0) {
                        isEndOfPath = true;
                    } else {
                        cursor --;
                    }
                    break;
                case 3:
                    // move down unless out of bounds
                    if (cursor >= numPositions - width) {
                        isEndOfPath = true;
                    } else {
                        cursor += width;
                    }
                    break;
                case 4:
                    // move up unless out of bounds
                    if (cursor < width) {
                        isEndOfPath = true;
                    } else {
                        cursor -= width;
                    }
                    break;
            }

            // if we hit a visited cell, we're in a cycle
            if (cellIsVisited[cursor]) {
                isEndOfPath = true;
            }

            if (isEndOfPath) {
                return visitedCells;
            } else {
                cellIsVisited[cursor] = true;
                visitedCells.add(cursor);
                // if we naturally reach the last position, return 0 immediately
                if (cursor == numPositions - 1) {
                    visitedCells = new LinkedList<>();
                    visitedCells.add(cursor);
                    cellIsVisited[cursor] = true;
                    return visitedCells;
                }
            }
        }
    }
}