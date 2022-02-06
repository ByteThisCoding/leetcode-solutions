# 1315. Sum of Nodes with Even-Valued Grandparents
This is my solution for LeetCode's problem 1315: https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/

## Problem Analysis
We need to collect a sum of value of nodes throught the input tree, but we can only include a node's value if it's grandparent's value is even. In the *TreeNode* class provided by the problem, there is no reference to the parent node, so we will not be able to traverse up two levels at each point to check if the grandparent's value is even. We'll need to find a way to check these values without traversing the tree more than once, as such an algorithm would be very inefficient. We cannot filter any subtrees entirely, because the value of any given node does not predict if its children or grandchildren will be even or odd, so we cannot infer anything useful about these subtrees.

## Implementation Strategy
Based on the contraints of the problem, we will implement a recursive depth first search method which takes in additional values of the parent's value and grandparent's value. We'll provide initial values of *-1* for the beginning of the traversal, as the root doesn't have any parent or grandparent nodes. Then, for each level of iteration, check if the grandparent's value is even, and if it is, include the current node's value in the sum. Then, recursively call the method for the left and right child nodes, if they are not null.

## Space and Time Complexity
The average space complexity is *O(log n)*, as the function call stack will need to record one entry per level of depth within the traversal. In the worst case, where each node in the binary tree only has a left node, or only a right node, throughout, the structure will behave as a linked list and will have a worst space complexity of *O(n)*. The time complexity is *O(n)*, as we will be visiting every node exactly one time.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Binary Trees](https://bytethisstore.com/articles/pg/binary-tree)
1. [Introduction to Depth First Search](https://bytethisstore.com/articles/pg/graph-algorithms-depth-breadth-search)
1. [Discussion and Analysis on YouTube](https://youtu.be/SnWnCOZTgaY)