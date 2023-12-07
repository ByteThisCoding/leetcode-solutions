# 2101. Detonate the Maximum Bombs
This is my solution for LeetCode's problem 2101: https://leetcode.com/problems/detonate-the-maximum-bombs

## Problem Analysis
We need to find the maximum chain of bomb detonations given an initial detonation of one of the bombs provided in the input array. Bombs have different radii and start positions. Let's assess the nature of detonations and chains:
* If bomb A detonates bomb B, that doesn't necessarily mean bomb B detonates bomb A.
* A chain can be a subset of another chain. For example, a chain of 5 detonations can be triggered by a bomb with a large radius, increasing the chain size to 6.
* In the 2D graph, chains can take any shape: line, circle, oval, zig-zag, etc, so we can't make any assumptions about the coordinates and radii.

If we consider the detonation chains as a graph of nodes, where each node is a bomb, this problem reduces to finding the largest connected subgraph.

## Implementation Strategy
This algorithm is split into three parts:
1. Determining if any given bomb detonates any other given bomb.
1. Finding each bomb that another bomb can detonate itself (1st level, not any farther).
1. Use those 1st level connections to do a breadth first search to find the largest connected subgraph. Those 1st level connections represent node connections in a graph.

The first step above can use a simple distance formula, but since the input positions and radii can be large, we will use BigIntegers to ensure we don't run into overflow issues.

In the second step, we will loop over each bomb, then have a nested iteration over each other bomb. If the two bombs intersect, add it to the list of intersections. Then, perform a BFS search for each bomb, ignoring bombs that were already visited.

## Space and Time Complexity
The time complexity for the initial traversal is *O(n^2)*, where **n** is the number of bombs, due to the nested traversal when determining the 1st level detonations. The breadth first search is *O(n+e)* where **e** is the number of bomb connections. In the worst case scenario, each bomb can blow up each other bomb, so each bomb would have **n-1** connections, so there would be **n(n-1)** edges. The total time complexity is *O(n^2+n(n-1))* which reduces to *O(n^2)*.

The space complexity is *O(n^2)* because there may be up to **n(n-1)** edges to record.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Graph Traversal Algorithms](https://bytethisstore.com/articles/pg/graph-algorithms-depth-breadth-search)