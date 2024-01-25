# 252. Meeting Rooms
This is my solution for LeetCode's problem 252: https://leetcode.com/problems/meeting-rooms/

## Problem Analysis
If there is any overlap in the meetings, a single person cannot attend all of them. If the end time of any meeting is the same as the start time of another meeting, that person can still attend both. There are multiple ways of solving this, but we want to make it as efficient as possible. For example, we don't want to do a quadratic iteration, where for each meeting, we check if any other meeting conflicts with it.

If we sorted the input array by start time, we would just need to iterate once through the sorted input, and for each meeting, check if the meeting after it starts before the current meeting ends. We can do this because, if there is a meeting conflict, it will be at least with the meeting immediately before / after it (or at the same time).

We can further optimize this by sorting as we iterate, and at each point, check if there is a meeting conflict. In this way, we can stop sorting immediately after a conflict is found, and the entire list will only be sorted if there are no conflicts.

## Implementation Strategy
We will implement a data structure **IntRangeCollection** to keep track of ranges. This will use a **TreeMap** to map start and end times for each meeting. The tree map will keep keys (start times) sorted as we make insertions. At each point we insert, we'll check if there is a conflict between meetings.

A conflict exists if there is a meeting that starts before the one to be inserted and ends after its start time, or if there is a meeting that starts after the one to be inserted but also starts before its end time. We can check both of these conditions by finding the latest start time that is before the end time of the meeting to insert. If it exists, and it either starts after this one, or it ends after this one starts, then we have a conflict.

## Space and Time Complexity
The time complexity is *O(n log n)*. Each insertion is *O(log n)*, and in the worst case scenario (no meeting conflicts), we'll insert **n** meetings. The space complexity is *O(n)*, as we may insert up to **n** meetings.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Binary Search Trees](https://bytethisstore.com/articles/pg/binary-search-tree(footer:april22))