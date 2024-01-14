# 1110. Snapshot Array
This is my solution for LeetCode's problem 1110: https://leetcode.com/problems/delete-nodes-and-return-forest

## Problem Analysis
We're given the root node of a single binary tree called **root** and a list of values called **to_delete**. For each value in that list, we need to delete the node corresponding to that value. Once we delete nodes, we'll have multiple binary trees, and we need to return the root node of each of those trees.

Let's consider just the count for a moment: how many trees would exist given the requested deletions? If a leaf node is deleted, no new trees would be created. If any other node is deleted, we would create one tree per child of that deleted node. Therefore, we can find the number of trees by counting the number of children of each deleted node.

Expanding upon this, we can solve our problem by doing a depth-first traversal over the tree. For each node, if its value is in **to_delete**, we can add its children to our list of new root nodes, then update its parent node to remove it as a reference.

## Implementation Strategy
We will first make a set **deletionSet** based on the values of **to_delete**. This will speed up performance by letting us directly check if a value should be deleted instead of having to traverse an array each time. We'll create a list **rootNodes** to keep track of all new roots. If **root** doesn't have a value in **deletionSet**, we'll add it to **rootNodes**.

Then, we'll recursively traverse each child node in a method **processNodes**. This will return *true* if the node has been deleted and *false* otherwise. For each **node**:
1. For its left and right children, call **processNode**. For each child, iff it returns true, update **node.left** and/or **node.right** respectively.
1. If this node's value is in **deletionSet**, add each of its children to **rootNodes** and return *true*.
1. If not, return *false*.

## Space and Time Complexity
There are two inputs, **root** and **to_delete**. We'll consider **n** to be the number of nodes that exist in the tree represented by **root**. The size of **to_delete** is bound by **n**, so we'll do our analysis in terms of **n**.

The time complexity is *O(n)*, as we visit each node exactly once. Since we're using a set for **to_delete**, each access is in *O(1)* time (if we didn't use a set, this would be *O(n^2)). The space complexity is *O(n)*, as we use a set to represent **to_delete** and our list of root nodes can approach **n** elements.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)
1. [Graph Traversal Algorithms](https://bytethisstore.com/articles/pg/graph-algorithms-depth-breadth-search)
