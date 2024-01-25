# 1136. Parallel Courses
This is my solution for LeetCode's problem 1136: https://leetcode.com/problems/parallel-courses/

## Problem Analysis
You can visualize a series of courses and their prerequisites as a series of acyclic graphs. They might not all be connected. The relationship between nodes is the path you would have to take for all prerequisites of each course. The minimum number of semesters needed is equal to the maximum path along one of these graphs. If there are any cycles, you can't take all of the courses.

## Implementation Strategy
We will:
1. Convert the courses to a list of list representing nodes on a graph and their connections.
1. Create a cache **visitedNodeDepths** to memoize previous traversals. That way, if we encounter a subgraph we've already traversed, we don't have to traverse it again.
1. Find the list of courses that have no prerequisites. This will help us find entry points to the graph.
1. For each entry point, find the maximum depth (path size). Record the maximum along the way.

## Space and Time Complexity
The time complexity is *O(n+r)*, since we take **n** and **r** operations to prepare the graph and another **n** to visit each node exactly one. The space complexity os *O(n+r)*, since we have a constant amount of objects that take **n** space and the collection of edges takes **r** space.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Depth First Search](https://bytethisstore.com/articles/pg/graph-algorithms-depth-breadth-search)