# 1575. Count All Possible Routes
This is my solution for LeetCode's problem 1575: https://leetcode.com/problems/count-all-possible-routes/

## Problem Analysis
We need to be able to solve this problem in an efficient manner (i.e. without repeated computations). It must use an approach that traverses all possible paths, and when an invalid path is found, exits as early as possible.

## Implementation Strategy
We'll perform a depth first search using memoization to store previously found results. The memo will be an array of maps where the first index is the start position, the second index is the fuel remaining, and the value is the number of routes. If a memo value is not present and we have fuel remaining, iterate through all the locations, and for each, calculate the distance, subtract it from the fuel remaining (in the recursive call), and add it to the count.


## Space and Time Complexity
The complexity analysis for this algorithm is non trivial compared to other LeetCode solutions. Consider the "worst case scenario," an input of size **n** where each element **n[i]** = **i**, the start and finish are any valid number, and the initial fuel is **f**. The traversal yields a graph where the top level has one node (the start node), the next level down has **n-1** nodes (you don't stay on the same node), and each level down has **n-1** from the first node. The depth of this tree is **f**, so there are **(n-1)^f+1** nodes. Therefore, the time complexity is *O(n^f)*.

For the space complexity, we have a memo of size **n*f** and a stack size of **f**, so the space complexity is *O(n\*f)*

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)
1. [Graph Traversal Algorithms](https://bytethisstore.com/articles/pg/graph-algorithms-depth-breadth-search)