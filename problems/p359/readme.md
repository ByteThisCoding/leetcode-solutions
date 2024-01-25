# 359. Logger Rate Limiter
This is my solution for LeetCode's problem 359: https://leetcode.com/problems/logger-rate-limiter/

## Problem Analysis
We need some way to store messages that are not yet expired, or messages that should not be logged. The data structure we use needs to be efficient; lookups for expiration times needs to be constant. A hashmap is a good choice for this.

## Implementation Strategy
We will use a map to store a mapping between messages and their expiration times. At each call to **shouldPrintMessage**, we'll check the map to see if this message exists. If it does and the stored expiration time is higher than the current timestamp, we'll return false. Otherwise, we'll put the entry in the map for this message and its expiration time, then return true.

Note, messages are never deleted once added to the hashmap. In the problem constraints, there are only up to 30 messages so I did not include any cleanup. In a real-world scenario, we could introduce additional logic to clean up entries, such as by using a second map to map timestamp to a set of logs that need to be deleted, then periodically clean them up.

## Space and Time Complexity
The time complexity is *O(1)*, as hash map operations are constant time. The space complexity is *O(m)*, where **m** is the number of messages.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)