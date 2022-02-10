# 515. Find Largest Value in Each Tree Row
This is my solution for LeetCode's problem 515: https://leetcode.com/problems/find-largest-value-in-each-tree-row/

## Problem Analysis
Given the reference to the root node of a binary tree, we need to find the largest value in each row, or each depth, and store each largest value in a list. The 0th element will be the largest at depth 0, which is the root, the 1st element with the largest at depth 1, which are the child nodes of the root, the 2nd element will be the largest of the grandchildren of the root, etc. Our implementation should only visit each node once and should not use redundant space.

## Implementation Strategy
We will implement the solution by performing a breadth first search starting from the provided root node. While traversing, we will keep track of the distance from the root node for each node. The algorithm can be summarized as:
1. Create an empty list of integers *maxResults*.
1. Create an empty *Set* to keep track of nodes we've visited.
1. Create an empty *Queue* to keep track of nodes we've yet to visit. We'll also store the distance of each node from the root alongside the nodes themselves.
1. While the queue is not empty:
    1. Dequeue and store the result into *queueNode*.
    1. If *maxResults* doesn't have an entry for *queueNode.distanceFromRoot*, add a new entry with *queueNode.value*.
    1. Otherwise, set the value to the max of *queueNode.value* and the value already in the list.
    1. If *queueNode* has a left child, add it to the queue with *queueNode.distance + 1*.
    1. If *queueNode* has a right child, add it to the queue with *queueNode.distance + 1*.
1. Return *maxResults*.

**Note**: the same concept would also work with a depth-first search. We could use a *Stack* instead of a *Queue* to keep track of which nodes to visit next, or we could use a recursive implementation and pass in the distance as a method parameter.

## Space and Time Complexity
This has a space complexity of *O(1)* if we don't include the output list itself, or *O(n)* if we do. The time complexity is *O(n)*, as we visit each node one time.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Queues](https://bytethisstore.com/articles/pg/queue)
1. [Graph Traversal Algorithms](https://bytethisstore.com/articles/pg/graph-algorithms-depth-breadth-search)
1. [Discussion and Analysis on YouTube](https://youtu.be/0im1eLtWDBk)