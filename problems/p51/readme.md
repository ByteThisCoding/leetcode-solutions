# 51. N-Queens
This is my solution for LeetCode's problem 51: https://leetcode.com/problems/n-queens

## Problem Analysis
We need to place **n** queens on an **n** by **n** chessboard so that none of them threaten each other. In other words, none should be able to intersect any of the others in one move. Note that queens are replacable, meaning that there is no distinction between the 1st, 2nd, 3rd queen, etc. This will enable us to search for queens by trying out positions one-at-a-time and while always moving forward in the iteration.

## Implementation Strategy
We will perform a depth-first search to find queen positions. To begin, we'll initialize a few variables:
* **assignedQueenPositions**: to record the assignments we've found so far. The last entry will represent the current assignment we're trying to find.
* **numPositionsFree**: a variable to track the number of cells not yet attacked / threatened.
* *positionAttacked**: a boolean array indicating which positions are being attacked and which aren't. This includes positions queens are occupying.

Then, we'll perform the recursive search using method **solveNthQueen**. This will iterate from the start position provided to the method to the last cell. For each iteration:
    1. If the cell is already threatened, continue to the next iteration.
    1. Calculate the positions that are attacked by this cell and store them in a set.
    1. Take those positions and update **positionAttacked** to indicate that those positions are now under attack.
    1. Add this current position as a queen assignment.
    1. If there are more queens to assign, call **solveNthQueen** with the next queen and skip to the next row (since two queens can't be in the same row). 
    1. If there aren't any, add this queen to **assignedQueenPositions** if there are no more cells that aren't under attack.
    1. Undo the assignment by restoring **assignedQueenPositions** and **positionAttacked**.

## Space and Time Complexity
The time complexity is *O(n!)* because our algorithm tries nearly all possible queen assignments. We have optimization to prevent most assignments that are invalid, but this does not change the worst case time complexity. The space complexity is *O(n^2)* since we are using a cache to record cells that are under attack.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Graph Traversal Algorithms](https://bytethisstore.com/articles/pg/graph-algorithms-depth-breadth-search)