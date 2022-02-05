# 133. Clone Graph
This is my solution for LeetCode's problem 133: https://leetcode.com/problems/clone-graph/

## Problem Analysis
The problem gives us a graph node and asks us to perform a *deep* copy of the entire graph of the node, all of its connected nodes, their connected nodes, etc. Note that by *deep* copy, they indicate we must clone every node within the graph; we cannot return the same node as we are given, and we cannot return a graph where some nodes are cloned but others are not. To facilitate our analysis, we'll first look at a simpler problem: perform a deep copy of a *tree* starting with its root node. In this simpler problem, we can implement *cloneTree* to call itself recursively: it will clone the node it's given, then for each of its children, call itself and pass that child in as an argument. This is an example of a depth first search, and is similar to the approach we will take for the main problem. However, this approach will not work with all graphs, as it does not account for graphs which contain cycles. To account for this situation, we can use a *Set* to keep track of which nodes were visited so we can avoid visiting them again and thus avoid entering an infinite loop. Then, at each point in the traversal, clone each node only if it has not yet been cloned, and for each cloned node, append other cloned nodes in the same order as the original node's neighbors.

## Implementation Strategy
In our implementation, we'll take a similar approach, but we'll use breadth-first search instead of depth-first search. We'll modify the normal breadth-first search algorithm to facilitate the cloning process. Our algorithm will use a *hashMap* to keep a mapping of nodes and cloned nodes, so that at any point, if we encounter a node we've seen before, we can directly access the cloned version which was previously created. At each iteration in the search, we'll iterate over the neighbors of the current node, and for each, we'll create a clone if it doesn't exist already, then append each cloned neighbor to the clone of the current node. The full algorithm can be summarized as:
1. Create the map *clonedNodes* where the key is an original node and the value is a cloned node.
1. Clone the first node and add it to *clonedNodes*.
1. Create a queue *nodesToVisit* which will be used for the breadth-first search.
1. Add the original node to *nodesToVisit*.
1. While *nodesToVisit* is not empty:
    1. Dequeue from *nodesToVisit* and assign the node to *currentNode*.
    1. Get the clone of *currentNode* from *clonedNodes* and assign it to *clonedCurrentNode*.
    1. For each neighbor node *neighbor* of *currentNode*:
        1. Create a variable *clonedNeighbor*.
        1. If *neighbor* has already been cloned, assign the clone to *clonedNeighbor*.
        1. If not yet cloned:
            1. Create a new clone and assign it to *clonedNeighbor*.
            1. Add *neighbor* to *nodesToVisit*.
        1. Add *clonedNeighbor* to the list of neighbors in *clonedCurrentNode*.
1. Return the cloned node of the original input node.

## Space and Time Complexity
We will define the space and time complexity in terms of the number of vertices *v* and the number of edges *e*. The space complexity, including the cloned graph, is *O(v+e)*, as the cloned graph will consume *v*+*e* space, the clone map will contain *v* key-value pairs, and the queue used in the breadth-first search will take up to *v* entries. The time complexity is *O(v+e)*, which is the time complexity for any breadth-first search.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)
1. [Introduction to Queues](https://bytethisstore.com/articles/pg/queue)
1. [Graph Traversal Algorithms](https://bytethisstore.com/articles/pg/graph-algorithms-depth-breadth-search)
1. [Discussion and Analysis on YouTube](https://youtu.be/tKbwgdEupF0)