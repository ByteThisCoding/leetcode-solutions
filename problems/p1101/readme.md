# 1101. Parallel Courses
This is my solution for LeetCode's problem 1101: https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/

## Problem Analysis
We need to track friendships through a group of **n** people. At certain points in time, two people become friends, and when this happens, we can consider everybody in both friend groups to now be friends with each other (the problem lists them as aquaintances but it is simpler to consider it in this manner).

There are multiple ways of modeling these relationships. When I first solved this problem, I modeled it using a mapping between each **nth** person, and a set of everybody they are friends with. When people became friends, the two sets would merge, and the mapping for those two people would be updated to point to the new same set. This way solved the problem, but it isn't the most efficient solution.

There is a less-common data structure called the **Disjoint Union** structure. This models relationships between items, or in this case, integers. As we receive logs, we can register relationships between friends, and the data structure will automatically join the sets of friends together.

## Implementation Strategy
We'll implement the disjoint union data structure itself, only with the operations we need for this problem. This will keep track of the maximum union size.

After, in the main solution class, we'll first sort the logs to ensure they are in chronological order. Then, for each log, we'll register the "union" of the two individuals and check the maximum union size. If it is equal to the number of people, we'll return that timestamp.

## Space and Time Complexity
The time complexity is *O(n log n)*. The set **union** operation is *O(a(n))*, where **a(n)** is the inverse ackermann function, an incredibly slow-growing function that is practically constant time. This is eclipsed by the *O(n)* that it takes to traverse the logs and the *O(n log n)* it takes to sort the logs. The space complexity is *O(n)*.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Disjoint Union](https://www.geeksforgeeks.org/introduction-to-disjoint-set-data-structure-or-union-find-algorithm/)