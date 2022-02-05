# 1971. Find if Path Exists in Graph - Breadth First Search Approach
This is my solution for LeetCode's problem 1971: https://leetcode.com/problems/find-if-path-exists-in-graph/

## Problem Analysis
This problem is using a list of edges to define the graph's vertices and edges. This **edge-list** explicity defines the edges in the graph and implicitly defines the vertices via edge declarations. Each edge definition is unique, but a vertex value will appear at least twice. With this type of graph declaration, there are a few types of cases we need to be sure to account for:
1. A vertex may only be connected to one other vertex.
1. A vertex may have connections to more than one vertex.
1. Cycles may exist in the graph, so we must ensure we do not get caught in an infinite loop.
1. The graph may be *disconnected*, meaning that the graph will contain subgraphs which are not connected.

Points 1 and 2 above may be useful to keep in mind when reading the edge-list. Point 3 should be kept in mind when we consider how to traverse the graph. For the last point, let's consider what will happen if the graph is connected vs when it is not:
* **If the graph is connected**: by definition, for any two vertices *v* and *u*, some path will exist which connects them.
* **If the graph is not connected**: there will be at least one vertex pair *v* and *u* which is not connected.

Therefore, we can solve this problem by writing an algorithm to determine if the graph is connected or disconnected:
* If the graph is connected, there definitely exists a path for any input *v* and *u*.
* If the graph is disconnected, we can find the subgraphs which are themselves connected:
    * If *v* and *u* are on the same subgraph, there exists a path from *v* to *u*.
    * If they are on different subgraphs, no such path exists.

## Implementation Strategy
Based on the points outline above, we will take the following approach:
1. Create a *Map* which will map a vertex value to a *Set* of vertices it is connected to.
1. For each edge in the input list which connects *v* and *u*:
    1. Add an entry in the hashmap where *v* is the key and add the value of *u* to the set associated with *v*, or create a new set if one doesn't exist.
    1. Perform the same operation, except use *u* as the key and append *v* as the value. This will ensure the connection is added both ways.
1. Perform a graph traversal starting with the *source* node:
    1. If the *source* is connected to the *destination*, the traversal will find the node, at which point we return *true*.
    1. Otherwise, it will not, even though it does exist in the graph, so we return *false*.

Note that the use of a *HashSet* will give us *O(1)* lookup time, whereas an *ArrayList* would need *O(n)* for the same operation. For the traversal algorithm, we will use breadth-first search to find the path between the two vertices. Also, looking at the problem statement, all node values will be something between *0* and *n-1*, where *n* is one of the input parameters. Therefore, instead of using a *HashMap* for our key value mapping, we can directly use an *ArrayList* and use the indices as keys.

## Space and Time Complexity
We will define the space and time complexity in terms of the number of vertices *v* and the number of edges *e*. The space complexity is *O(v+e)*, as we are using a *HashMap* to map each vertex with its connected vertices; thus, we will have one key per vertex and one entry in each value set per edge.
We will also be using an additional *HashSet* and *Stack* for the graph traversal.
The time complexity is *O(v+e)*, as we will need to iterate once over the edges to create the HashMap, then the complexity of the traversal algorithm itself is *O(v+e)*. 

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)
1. [Graph Traversal Algorithms](https://bytethisstore.com/articles/pg/graph-algorithms-depth-breadth-search)
1. [Discussion and Analysis on YouTube](https://youtu.be/X6RkyPFE6wg)