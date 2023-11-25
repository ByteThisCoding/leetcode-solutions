# 715. Range Module
This is my solution for LeetCode's problem 715: https://leetcode.com/problems/range-module/

## Problem Analysis
When we make insertions and removals, we need to ensure we don't introduce any anomalies into the data structure, such as distinct but overlapping ranges or ranges that don't have both a start and an end. We also want to ensure we keep ranges sorted as we insert so we can quickly retrieve them for querying.

For insertions, we need to account for the following cases:
* The range is totally seperate from existing ranges.
* The range is exactly equal to an existing range.
* The range partially encompasses one or more existing ranges.

In the last case above, we will need to ensure that all existing ranges that fall within the new range are merged and that we extend the range we're inserting to include any existing ranges that intersect with it.

For removals, we need to account for the same cases. In the last case, we will need to ensure that all ranges that are fully encompassed are removed and that any other ranges that intersect are truncated to not include any part of the range we're removing.

With these in place, you can query by getting the closest lowest range to the input query's **left** element and check if it extends at least as far as **right**.

## Implementation Strategy
We will use a **TreeMap** (a binary search tree) to store our ranges. This will let us retrieve ranges in *O(log n)* time because it will keep the ranges sorted numerically.

For insertions, we will keep track of two variables **entryLeft** and **entryRight** that will be initialized to **left** and **right**. As we go, we'll expand these so they encompass any existing ranges (i.e. [5, 10] -> [4, 11] if our structure already has some existing ranges that intersect). Then, we'll continuously check for overlapping ranges:
1. Get the highest range whose left element is smaller than **entryRight** and store in **rangeOverlapLeft**.
1. If that element doesn't exist or is smaller than **entryLeft**, then there are no overlapping ranges and we can stop.
1. Otherwise, set **entryLeft** to the minimum of **entryLeft** and **rangeOverlapLeft**. This is where we expand the range if we find an intersection.
1. Similarly, set **entryRight** to the maximum of **entryRight** and **ranges.get(rangeOverlapleft)**.
1. Remove **entryLeft** from ranges (this helps avoid overlapping ranges).
Once done, add **entryLeft -> entryRight** to **ranges**.

For removals, we will re-use the same algorithm for removing overlapping ranges, then:
1: If **entryLeft** is less than **left**, add **left -> entryLeft** in **ranges**.
1. If **entryRight** is greater than **right**, add **entryRight -> right** in **ranges**.

The last two steps preserve portions of ranges that did intersect with the range that was deleted but shouldn't be deleted themselves.

## Space and Time Complexity
We will refer to space in time complexity in terms of **n**, where **n** is the number of ranges that are encompassed in the structure at any given time.

The time complexity for queries is *O(log n)*, as queries are based on a single binary search operation. The time complexity for insertions is *O(n)*, because in the worst case, the algorithm would need to remove all ranges before inserting one. The same applies to removals.

The space complexity is *O(n)* as we store each range but we don't store any additional data.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Binary Search Trees](https://bytethisstore.com/articles/pg/binary-search-tree): tree maps use binary search trees internally to keep the keys sorted.