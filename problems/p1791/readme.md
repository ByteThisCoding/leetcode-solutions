# 1791. Find Center of Star Graph
This is my solution for LeetCode's problem 1791: https://leetcode.com/problems/find-center-of-star-graph/

## Problem Analysis
The problem states that we are given a **star** graph, which is described as a graph with a center node and edge nodes where the center node connects to all edge nodes and the edge nodes themselves only connect to the center. In this analysis, we will call *edge* nodes *outer* nodes to avoid confusion with the term *edge*. Based on this definition, we can infer that:
* Each outer node is only connected to the center node; thus, it has *only* one edge.
* The center node has more than one edge.

The problem is using a list of edges to define the graph's vertices and edges. This **edge-list** explicity defines the edges in the graph and implicitly defines the vertices via edge declarations. Each edge definition is unique, but a node value will appear at least twice. Based on these facts, we can implement an efficient algorithm which uses node declarations and the observations above.

## Implementation Strategy
Based on the points above, we know that the center node is the only one which will have more than one connection. Therefore, while iterating over the list of edges, we can keep track of the number of connections each node has, and if at any point, we find one which has more than one, we can return that node immediately. During the traversal, our running edge counts for each node will be 0, 1, or 2: each node which has not been discovered will have 0 edges implicitly, each node which has been discovered will have 1 or 2, and when we find one with 2, we return immediately. Therefore, we can actually use *boolean* values to represent the number of edges implicitly, and along that line of though, we can use a *Set* to keep track of visited nodes, where a node existing in the set indicates 1 edge found, or *true*, and non-existence indicates 0 edges found, or *false*. Since our algorithm will return immediately upon finding a node with more than 1 edge, we don't need to store anything for that scenario.

## Space and Time Complexity
We will define the space and time complexity in terms of the number of vertices *v* and the number of edges *e*. The space complexity is *O(v)*, as we are using a *HashSet* which will store *v* number of values.
The time complexity is *O(e)*, as we will be iterating up-to *e* edges.

To summarize, our algorithm will:
1. Create a *HashSet* of integers, where values correspond to nodes which have been discovered and have 1 or more edges.
1. Iterate over the list of edges, and for each edge:
    1. If either node already exists in the hash set, return *true* immediately.
    1. Otherwise, add both nodes to the hash set.