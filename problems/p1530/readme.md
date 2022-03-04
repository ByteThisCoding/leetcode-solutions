# 1530. Number of Good Leaf Nodes Pairs
This is my solution for LeetCode's problem 1530: https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/

## Problem Analysis
We are given the **root** a binary tree of arbitrary size and an int **distance**. We define a pair of leaf nodes as **good** if the path between them is less than or equal to **distance**. We need to implement an algorithm to count the number of such pairs in any given binary tree. Note that the path is from a leaf to another leaf and a path does not necessarily need to move through the root of the entire tree. However, all paths will need to move up, reach some node, then move down. A path also cannot back track on itself or traverse an edge more than once. In other words, there is no direct sibling traversal, and since this is a tree and not a graph, we must move up, then down, and once we switch we cannot switch again. With this in mind, we can consider the problem as follows:
1. For each node:
    1. Count the number of pairs from this node's left subtree to this node's right subtree.
    1. Count the pairs from right to left.
    1. Propagate the total number of leaf nodes and their depths back to the calling method.

## Implementation Strategy
We will perform a recursive depth first search and count the number of pairs we find during each traversal. We will also keep track of the depths of each leaf node and how many leaf nodes there are for each depth value. For each recursive call, we'll determine the depth counts on each subtree, left and right, and check for the complimentary values for each on the opposite subtree. At each node, our recursive method will:
1. Initialize a Map **totalDepthMap** to record the depths and counts of each leaf node from the **root** provided to the method.
1. If **root** is null, return that empty map immediately and do not proceed.
1. Create a Map **leftDepthMap**, recursively call this method on *root.left*, and assign the result.
1. Create a Map **rightDepthMap**, recursively call this method on *root.right*, and assign the result.
1. Create a Set **rightExcludeValues**. This will help prevent counting pairs twice later on.
1. For each **depth** and **count** in **leftDepthMap**:
    1. Set **depthPlus** to *depth + 1* for convenience later on.
    1. Calculate the **compliment** as *distance - depthPlus*.
    1. Iterate over all values in **rightMap** between 0 and *compliment - 1*. For each **rightKey** and **rightValue**:
        1. Increment the total pair count by *rightValue⋅count*.
        1. Add **rightKey** to **rightExcludeValues**.
    1. Add **depthPlus** to **totalDepthMap** and set the value to **count** plus its existing value.
1. For each **depth** and **count** in **rightDepthMap**, do the same for all keys which do not exist in **rightExcludeValues**.
1. If this is a leaf node, put *0, 1* in **totalDepthMap**.
1. Return **totalDepthMap**.

For convenience, we'll keep track of the total pair count as an instance property. For performance, we'll use a *TreeMap*, which is a map where they keys are sorted. This will make getting the submap take *O(log n)* time instead of *O(n)* time.

## Space and Time Complexity
The space complexity is *O(n⋅log(n))*, as we'll need to keep track of *log(n)* methods on the call stack, and for each call, keep track of the depth of each leaf node, where there are up to *n/2* leaf nodes. The time complexity is *O(n⋅log(n))*, as we'll need to iterate over each node, and for each node, determine the compliment submaps, which takes *O(log(n))* since we are using *SortedMaps*.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Graph Traversal Algorithms](https://bytethisstore.com/articles/pg/graph-algorithms-depth-breadth-search)
1. [Introduction to Hash Tables](https://bytethisstore.com/articles/pg/implement-hash-table)
1. [Introduction to Binary Search Trees](https://bytethisstore.com/articles/pg/binary-search-tree): tree maps use binary search trees internally to keep the keys sorted.