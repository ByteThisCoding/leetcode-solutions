# 1424. Diagonal Traverse II
This is my solution for LeetCode's problem 1424: https://leetcode.com/problems/diagonal-traverse-ii

## Problem Analysis
The given problem requires transforming a 2D list of integers into a 1D array. The transformation follows a specific pattern: reordering the elements in a diagonal traversal sequence. This task involves navigating the 2D list in a manner that simulates moving diagonally, starting from the top-left corner and proceeding along each diagonal. The challenge lies in efficiently mapping each element from its original position in the 2D list to the correct position in the 1D array, especially considering that the 2D list may not be a perfect rectangle, since rows may have different lengths. We could iterate over the 2D array in a manner similar to a diagonal and output everything, but there are more efficient ways of proceeding.

Let's analyze a 3x3 matrix. Below, we see a matrix of x:y positions, their order in the final list after traversing diagonals, the index of the diagonal it belongs to, and the index of the number within its own diagonal.
```
[0:0, 0:1, 0:2], 0,2,5 | 0,1,2 | 0,1,2
[1:0, 1:1, 1:2], 1,4,7 | 1,2,3 | 0,1,1
[2:0, 2:1, 2:2], 3,6,8 | 2,3,4 | 0,0,0
```

From that, we can see that the diagonal number of a cell is the row+col of that cell.

## Implementation Strategy
Use a list of lists to keep track of values at each diagonal. We can use row+col to determine which diagonal any given cell corresponds to, then add it to the corresponding list. Since we iterate over rows, we are always adding elements to diagonals in reverse order, so we must iterate backwards when we iterate over each list to make the final array.

## Space and Time Complexity
The time complexity is *O(n)* since we iterate over each element once and the Tree Map uses *O(log n)* for each insertion. The space complexity is *O(n)* where **n** is the number of elements in all rows, since we have a seperate output list.