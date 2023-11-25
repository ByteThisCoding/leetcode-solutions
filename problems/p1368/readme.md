# 1368. Count Pairs Of Nodes
This is my solution for LeetCode's problem 1368: https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/

## Problem Analysis
The problem asks us to return the minimum cost to make the grid have at least one valid path. A valid path is a path from the top left to the bottom right cell where the arrows (as described in the problem) lead you from the start to the end. Notice that the problem doesn't ask *which* cells to change, only the count. Therefore, we don't actually need to determine which cells would need to change, and this simplifies the problem.

To figure out how many cells we need to change, we need to:
1. Figure out which cells are in the initial path starting from the first position.
1. Find all cells neighboring that are the cells in that path but aren't themselves in that path.
1. For each of those cells, traverse its path and repeat. Each time we "jump" from a path to a neighboring cell's path, we increment the cost it would take to get to that cell, since a "jump" is the same as changing the arrow direction of a cell.

## Implementation Strategy
The code will map cells `[x,y]` to position `x*width+y` so we can use one-dimensional arrays to store information. We'll keep track of cells that were visited using a boolean array **cellIsVisited**. First, we'll traverse the path starting from the initial position 0. The path traversal algorithm will be defined in its own method so we can re-use it. It will:
1. Create a list to indicate cells that are a part of this path called **visitedCells**.
1. Add the **cursor**, or current cell, to the **visitedCells** and set **cellIsVisited[cursor]** to true.
1. If the cursor is already at the end cell, return a list of just that cell.
1. While the cursor is in a valid position:
    1. Update the cursor based on the direction of the arrow in the position in the grid.
    1. If the position is out of bounds or points to a cell already visited, return the current list of **visitedCells**.
    1. Add the **cursor**, or current cell, to the **visitedCells** and set **cellIsVisited[cursor]** to true.
    1. If the cursor is already at the end cell, return a list of just that cell.

Once the first path traversal is done, return 0 if the path already reached the target cell. Otherwise, use a variable **currentCost** to represent the cost it would take to get to the end cell (so far). Then, for each cell in **visitedCells**:
1. Make a new list **newVisitedCells**.
1. For the current cell, calculate the cell to the left, right, up, and down. For each, if they're valid and haven't been visited, call **traversePath** with that cell and add all responses to **newVisitedCells** or return **currentCost** if we've hit the end cell.
1. Once all cells are visited in **visitedCells**, increment **currentCost** and set **visitedCells** to **newVisitedCells** and repeat.

## Space and Time Complexity
This has a space complexity of *O(n * m)* where **n** and **m** are the height and width of the grid. Each cell is visited at most twice: once when traversing its path for the first time, and once when iterating over **visitedCells**. The space complexity is *O(n * m)* since we have a boolean array of size **n** * **m**, and in the worst case, **visitedCells** will have almost every cell (i.e. if a path almost gets to the target cell but not quite).


## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Linked Lists](https://bytethisstore.com/articles/pg/linked-list)