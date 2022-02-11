# 200. Number of Islands
This is my solution for LeetCode's problem 200: https://leetcode.com/problems/number-of-islands/

## Problem Analysis
We are given an *m x n* grid of the characters *0* and *1*, where a value of *1* marks that the coordinate at *grid[m][n]* represents land, and a *0* represents water. An *island* is defined as a group of horizontally and/or vertically connected *1*s. Note that diagonal (corner) connections are not included. We need to determine how many seperate islands there are for any given input grid. We can think of the grid as if it were a graph of nodes, where each node has a value *0* or *1*, and the diagonal and vertical adjacent cells are conencted to eachother via edges. If we wanted to, we could even implement a graph class with nodes and represent it as such, but we will not do so here. From here, we can use a graph traversal algorithm to collect information on cells which are water vs land and count the number of islands. One option might be to traverse the matrix as a whole and take different actions based on whether a node is a 0 or 1. However, there is a simpler method: *ignore* all 0 nodes (water) and only traverse 1 nodes. With this, we will implicitly have a graph of only 1 nodes, and we will be able to count the number of islands by determining the number of components (connected subgraphs) within this new graph.

## Implementation Strategy
We will perform multiple *depth first search* traversals using the matrix to reference cell values. We'll keep track of cells we've visited already to avoid getting caught in loops. At each cell, we'll ignore it if it is out of bounds (outside of the grid) or if it is a 0 value (water). Otherwise, we'll add it to the list of cell's we've visited, the recursively call our search method with the cell above, below, to the left, and to the right. We'll need to perform one depth first search per component, so we'll be able to determine the number of islands based on the number of traversals we'll need to perform.

## Space and Time Complexity
The space complexity is *O(m x n)* as we'll need to use space to keep track of the cells we've visited. The time complexity is *O(m * n)*, as we'll be visiting each cell exactly once.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)
1. [Graph Traversal Algorithms](https://bytethisstore.com/articles/pg/graph-algorithms-depth-breadth-search)
1. [Discussion and Analysis on YouTube](https://youtu.be/lRWeVRNLe74)