# 2265. Count Nodes Equal to Average of Subtree
This is my solution for LeetCode's problem 2265: https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/

## Problem Analysis
We need to assess the average of each subtree. We can use a recursive approach, but we must be careful; we cannot simply average the values of the left and right subtrees, since they may have different amounts of nodes. We can instead keep track of a running sum and number of nodes, then calculate the average when needed.

## Implementation Strategy
We will use a depth first search to determine the sums and number of nodes in each left and right subtree of each node, then combine those values with each node. Then, we will calculate the average for that node, and if it is equal to its value, increment a counter.

## Space and Time Complexity
The time complexity is *O(n)*, where **n** is the number of nodes. The space complexity is **O(log n)** because of the recursion stack.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Graph Traversal Algorithms](https://bytethisstore.com/articles/pg/graph-algorithms-depth-breadth-search)