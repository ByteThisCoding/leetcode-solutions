# 2458. All Divisions With the Highest Score of a Binary Array
This is my solution for LeetCode's problem 2458: https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries

## Problem Analysis
We are given a root node of a tree **root** and a set of **queries**. The response of each query will be the height of the tree if the node with value **queries[i]** were not a part of the tree. We don't know the size of the tree ahead of time beacuse we initially only have access to the root node. To determine the number of nodes and other information about the tree, we would need to traverse it.

Each query is independent of the other queries, so a "deletion" of a node in one query does not hold in the subsequent queries. Therefore, we should avoid taking an approach that actually removes nodes from the tree. Insted, we should collect information about the depth of each node and the max depth below the node, then use this information to find the response to each query without changing anything about the tree itself.

## Implementation Strategy
We will initially iterate the tree to collect information:
* We will record the depth of each node in a map **depths** that maps node values to their depths.
* For each node, we will determine the maximum depth of any path that has that node and store in the map **maxDepths** that maps node values to their maximum depths.
* For each node, we will store a reference to its parent node in map **parentNodes** that maps the nodes value to its parent.

The method *determineDepths* does this recursively:
1. Assign **maxDepth** with the value of **currentDepth** given as an argument.
1. For the left and right nodes (if they exist):
    1. Add an entry to **parentNodes** to link the child node to **startNode** given as an argument.
    1. Recursively call *determineDepths* to get the max depth of that child node and assign to **maxDepth** if it is larger than the existing value.
    1. Add **startNode.val -> maxDepth** in **maxDepths**
    1. Add **startNode.val -> currentDepth** in **depths**
    1. Return **maxDepth**.

This will recursively determine the maximum depth of each node and store it in the structures we created initially.

Then, for each **query** in queries:
1. Set **outputVal** to 0 (this will be the query response).
1. Find the query node's **parentNode** using our map **parentNodes**.
1. If the parentNode only has one child, set **outputVal** to **depths.get(parentNode)**. We do this because the parent node will no longer have children, so its depth will be it's new max depth.
1. If the parent node has two children, set **outputVal** to **maxDepths.get(childNodeValue)**, using the child that isn't the node to be deleted.
1. Iteratively move up the path to see if this deletion affects anything:
    1. Assign **parentNode** to its own parent.
    1. Check if the parent node's other child has a greater max depth. If it does, then this deletion doesn't affect the max depth of the tree, so return that immediately.
    1. If not, repeat this loop.

This traverses the path up, updating the **outputVal** with the max value found so far, and either returns that or an original max depth if nothing is affected.

## Space and Time Complexity
The time complexity of determining the depth values is *O(n)* where **n** is the number of nodes in the tree. To determine the depth values, this algorithm visits each node exactly once. Then, to determine the subtree removal, the time complexity is also *O(n)* as, in the worst case scenario, every node needs to be visited. In this operation, some or all nodes between the node to delete and root need to be traversed, and in the worst case scenario, all nodes will be in that path. In most cases, only a fraction of the nodes will be in that path

The space complexity is *O(n)* as we are storing information for each node.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)
1. [Introduction to Graph Traversal Algorithms](https://bytethisstore.com/articles/pg/graph-algorithms-depth-breadth-search)