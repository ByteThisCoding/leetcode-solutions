# 2402. Meeting Rooms III
This is my solution for LeetCode's problem 2402: https://leetcode.com/problems/meeting-rooms-iii/

## Problem Analysis
We need to assign **meetings.length** meetings to **n** rooms. We want to make sure meetings start with minimal delay, so we should assign them to rooms that are free or have the smallest delay. When there is a tie between multiple rooms, favor rooms with smaller room numbers.

Let's consider each condition in isolation. If we didn't care about the room number and only cared about booking meetings to minimize delay, we could use a *priority queue*. We would:
1. Add an association between rooms and meeting end times to that priority queue. Add all rooms and set end times to 0. We would sort based on end time only.
1. For each meeting in **meetings**, dequeue from the priority queue. If there's a delay, account for it. Make a new meeting entry in the priority queue and add it back.
1. Repeat until all meetings have been processed, keeping track of room usage along the way.

That would be sufficient to keep track of everything. However, if we want to have the room number be a tie breaker, we need to revamp this approach, because this approach would not successfully select the smallest room number. The problem lies in the fact that, as time elapses, it doesn't matter if an end time is smaller than another if both end times are considered to be "in the past." This would cause the internal sorting of the priority queue to change as time elapses, and it wouldn't be able to efficiently update itself in-place.

To solve these problems, we can use two priority queues: one for tracking available room numbers and one for tracking booked meetings. As time elapses, we can poll one or multiple times to remove all booked meetings that have expired, updating the available room numbers as we go.

## Implementation Strategy
We will use two priority queues: **availableRooms**, which keeps track of rooms that have no meetings and ensures that we get the smallest room number when we poll, and **bookedMeetings**, which keeps track of rooms that have meetings and their meetings' end times. Initially, **availableRooms** will contain an entry for every room.

Then, we'll iterate through all meetings. For each **meeting**:
1. Create int **meetingStartOffset** and set it to **meeting**'s start time. We might need to update this in the future.
1. While **bookedMeetings** isn't empty, and while the booked meeting at the top (via *peek*) ends at, or earlier than **meeting**'s start time, poll the meeting and add it's room number to **availableRooms**. This is the act of clearing out meetings that have already ended.
1. If **availableRooms** is empty, there are no rooms currently free:
    1. Poll the first entry from **bookedMeetings**.
    1. Indicate that the room to book is the room from that polled entry. This will be the first room with the earliest available start time.
    1. Set **meetingStartOffset** to the polled entry's end time.
1. If **availableRooms** is not empty, poll and set that to the room to book.
1. Increment the room usage count for this room.
1. Set **newEndTime** to **meetingStartOffset + meeting-start-time - meeting-end-time**. This accounts for the delay if there is one.
1. Add the new booked meeting to **bookedMeetings**.
1. Update max room usage counts.

## Space and Time Complexity
The time complexity is *O(mn log n)*, where **m** is the number of meetings. Let's breakdown each crucial operation that takes place while iterating over the meetings:
1. Poll **bookedMeetings** up to **n** times. This is *O(n log n)*.
1. Poll **availableRooms** zero or one times. This is *O(log n)*.
1. Insert a new meeting into **bookedMeetings**. This is *O(log n)*.

We also have an additional setup of **availableRooms** that is *O(n log n)* and an initial sorting of **meetings** that is *O(m log m)*.

The total time complexity is *O(m(n log n + log n + log n) + n log n + m log m)* which simplifies to *O(mn log n)*.

The space complexity is *O(n+m)*, since the priority queues have up to **n** elements and we use an array to count room usage for **m** meetings.


## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Binary Heaps](https://bytethisstore.com/articles/pg/binary-heap): priority queues are implemented using binary heaps.