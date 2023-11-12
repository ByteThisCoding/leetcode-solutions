# 48. Rotate Image
This is my solution for LeetCode's problem 48: https://leetcode.com/problems/rotate-image/description/

## Problem Analysis
The problem states that we can't allocate another matrix to store the values in, so the update must be in-place. To determine the algorithm for rotation, let's make a few observations:
1. The corners of matrix always rotate into each other.
2. On a matrix with odd number **n**, the center value always stays the same.

That hints as to how we can implement this. Let's start with the 2nd observation above and iteratively build up an understanding of the rotation process.
1. For a 1x1 matrix, there is nothing to do.
2. For a 2x2 matrix, all corners rotate into each other.
3. For a 3x3 matrix, the corners rotate into each other, the other cells on the outer ring rotate into each other, and the center stays the same.
4. For a 4x4 matrix, the corners rotate into each other, the other three cells of the outer ring form a similar pattern of rotation, and the inner 2x2 matrix follows the same rules as point 2 listed above.

With this, we have built up an inductive procedure, which means we can recursively apply the rotation process starting from outer rings and moving inward.

## Implementation Strategy
We apply the rotation by working on one ring at a time, starting with the outer rings and working inward. For each cell in each ring, a pattern of 4 cells emerges that rotate into each other, such as the corners. The ring length is calculated by subtracting the matrix length by double the current ring index minus one, so in a 5x5 matrix for example, the first ring has a size of 4 and the second ring has a size of 2. Then, iterate from 0 to the ring size, calculate the matrix coordinates of all cells to be rotated into each other, and update the values. A temp variable is needed to hold one of the values while the assignments occur.

## Space and Time Complexity
The time complexity is **O(n^2)**, as most or all cells are updated one time. The space complexity is *O(1)* because no additional space is used. Technically, this implementation uses tail recursion, but Java isn't optimized for tail recursion, so the space complexity is actually *O(n)* due to the call stack. This could be reduced to *O(1)* either by using a language that has tail recursion or by changing the recursive call to an iterative loop.