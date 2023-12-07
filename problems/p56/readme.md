# 56. Merge Intervals
This is my solution for LeetCode's problem 56: https://leetcode.com/problems/merge-intervals

## Problem Analysis
Given a list of intervals, we need to return a list that merges all intervals that intersect in the original list. The output must be non-overlapping intervals. We want to avoid quadratic iteration (loop inside of a loop). Note that if we sort the input by order of increasing start (i.e. sort by interval[0]), we can figure out which intervals are intersecting using only one iteration through the input. For each interval, if its start value is inbetween the previous start value and maximum end value found so far, it belongs to the same interval.

## Implementation Strategy
We will initially allocate an arraylist **mergedIntervals** to store combined intervals as we find them. Then, we will sort the input array and create new variables **minLeft** and **maxRight** to keep track of the merged interval as we go. We will initially set them to the start and end of the first interval. Then, for each interval starting with the 2nd (i=1):
1. Set variables **left** and **right** to the values in **intervals[i]**.
1. If the left intersects: **left >= minLeft && left <= maxRight**, set **maxRight** to the max value of **right** and **maxRight**.
1. If not:
    1. Add **[minLeft,maxRight]** to **mergedIntervals**
    1. Set **minLeft** and **maxRight** to values **left** and **right**.

That last step sets-up the remaining iteration for this next non-overlapping interval. When the loop is concluded, you need to add the last interval to **mergedIntervals** before returning.

## Space and Time Complexity
The time complexity is *O(n log n)* because we spend some initial time sorting, then iterate through the values exactly one. The space complexity is *O(n)* because we need to store the output intervals.