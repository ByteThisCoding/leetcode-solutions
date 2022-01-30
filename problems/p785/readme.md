# 785. Is Graph Bipartite
This is my solution for LeetCode's problem 785: https://leetcode.com/problems/is-graph-bipartite/

## Problem Analysis
The problem gives us a 2D array as input, where each row represents a vertex and each cell in that row indicates a vertex which it connects to. This is similar to an *adjacency matrix* in concept, but it does not include entries for vertex which each vertex does not connect to. The problem gives a definition of a **bipartite graph** as a graph where there are exactly 2 sets of vertices and a set of edges where *every* edge connects a vertex from the first set with one from the second set. Based on this definition, we can infer some additional properties of such as graph:
1. The two sets to not need to be the same size. For example, a bipartite graph can have one center vertex where every other vertex connects to it and only it.
1. Since *every* edge must connect a vertex from each set, we cannot have any edges which connect a node to another node in its own set.
1. As a consequence, every vertex *u* which is connected to a vertex *v* must belong in the other set.

Based on these observations, particularly the second point, we can determine whether a graph is bipartite or not based on whether or not it violates those conditions. When creating the implementation, we must account for a few edge cases:
* A graph with no nodes.
* A graph with only two nodes.
* A graph which is not connected.

## Implementation Strategy
We can use a breadth-first search algorithm to iterate over the nodes and add each node to its corresponding set. The entry node can be assigned to set *A*, then its connected nodes to *B*, their connected nodes to *A*, etc. In the case of disconnected graphs, we can take this action for each connected subgraph. If, during the traversal, we encounter a node which has already been visited, we can ensure the visited node is in the opposite set of the current node. If it is not, we can return *false* immediately. If this never happens, we can return *true*. We will implement this algorithm using a *HashMap* to record which set each vertex belongs to.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)
1. [Graph Traversal Algorithms](https://bytethisstore.com/articles/pg/graph-algorithms-depth-breadth-search)