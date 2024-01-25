# 253. Meeting Rooms
This is my solution for LeetCode's problem 253: https://leetcode.com/problems/meeting-rooms-ii/

## Problem Analysis
We need to determine how many conference rooms are required, but we don't necessarily need to actually make those assignments. We only need to determine the maximum number of overlaps at any given time.

We can gain insight into the problem by visualizing the meetings in different ways. If you were to draw a line on a chart representing each meeting's duration, the maximum number of stacked lines represents the minimum number of conference rooms.

For example:
```
# [[0, 2], [1, 5], [2, 6], [4, 7]]
XX......
.XXXX...
..XXXX..
....XXX.
```

Now imagine that you made a series of dots in two rows, one for start times and one for end times, corresponding to when each meeting starts and ends. If there were no overlaps, each dot would appear alternating, one in the top row, then one in the bottom row, etc. 

```
# [[0, 2], [1, 5], [2, 6], [4, 7]]
XXX.X...
..X..XXX
```

Notice how there are two Xs on the top row before the first X on the bottom row. This means two meetings started before one concluded. Similarly, the last three Xs onthe bottom row indicates that three meetings ended after the last one began. These indicate conflicts.

We can count the number of conflicts by counting over the start and end points together. For each start point, add one to the count. For each end point, remove one to the count. Keep track of the max count at all times.


## Implementation Strategy
We will split the meetings into two seperate arrays representing **startTimes** and **endTimes**, then sort both of them. Then, keep track of two pointers **startIndex** and **endIndex**. While there are still start and end times we haven't visited:
1. Count all of the start times that are less than **endTimes[endIndex]** and update **numRooms** and **maxNumRooms** if it's greater. Increment **startIndex** as we go.
1. Count all of the end times that are less than or equal **startTimes[startIndex]** and subtract from **numRooms**. Increment **endIndex** as we go.

## Space and Time Complexity
The time complexity is *O(n log n)* due to the sorting operations. The space complexity is *O(n)* since we're storing start and end times seperately.