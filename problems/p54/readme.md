# 54. Spiral Matrix
This is my solution for LeetCode's problem 54: https://leetcode.com/problems/spiral-matrix/

## Problem Analysis
Given an *m x n* matrix, or 2D list, we need to return a 1D list containing all items in the matrix. The output list should contain the items in order as if we traversed the matrix in a spiral pattern, starting at the top left, then moving to the right, down, left, and up, ignoring items we've already visited. We will need to determine an efficient way of making this traversal without redundant iteration or storage.

## Implementation Strategy
We will define our iteration in terms of *steps* and *substeps*. A **substep** is a traversal from start to finish in a single direction. A **step** runs 4 *substeps*, one for each direction in the order of right, down, left, and up. During our stepping process, we'll implicity keep track of the cells we've already visited by maintaining values for *xLeft*, *xRight*, *yTop*, and *yBottom*. After each *substep*, we'll increment or decrement the appropriate variable. We'll know when we're done iterating when the number of elements in the output list is equal to the number of elements in the input matrix.

To summarize the algorithm
1. Initialize the *solutionList*.
1. Initialize variables to keep track of boundaries:
    * **xLeft**: set to *0*, indicating the leftmost position.
    * **xRight**: set to *matrix[0].length - 1*, indicating the rightmost position.
    * **yTop**: set to *0*, indicating the topmost position.
    * **yBottom**: set to *matrix.length - 1*, indicating the bottommost position.
1. While *true* (we'll have break conditions within this loop):
    1. Move from *xLeft* to *xRight* and add each item *matrix[x][yTop]* to *solutionList*. This is a move from the top left to the top right.
    1. Increment *yTop*, indicating that we've processed the first row.
    1. Exit if we've added all items in the matrix, proceed otherwise.
    1. Move from *yTop* to *yBottom* and add each item *matrix[xRight][y]* to *solutionList*. This is a move from the top right to the bottom right.
    1. Decrement *xRight*, indicating that we've processed the right column.
    1. Exit if we've added all items in the matrix, proceed otherwise.
    1. Repeat those steps for the bottom row and leftmost columns.
1. Return *solutionList*.

## Space and Time Complexity
The space complexity, including the output list itself, is *O(n)*, as the output list will contain every element, and we will only have a constant number of other variables not dependend upon the input size. The time complexity is *O(n)*, as we will be touching each element in the matrix exactly once.

## Additional Resources
1. [Introduction to Linked Lists](https://bytethisstore.com/articles/pg/reverse-linked-list)
1. [Discussion and Analysis on YouTube](https://youtu.be/54OFPHY_CiM)