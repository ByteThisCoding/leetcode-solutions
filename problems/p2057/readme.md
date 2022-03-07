# 2057. Smallest Index With Equal Value
This is my solution for LeetCode's problem 2057: https://leetcode.com/problems/smallest-index-with-equal-value/

## Problem Analysis
We are given an array of ints **nums**. We need to find the first index **i** where *i % 10 == nums[i]*, or -1 if there is no index. The modulus "%" operator will, given two values *a* and *b*, return the remainder of dividing *a* by *b*. If we think about the division of numbers **55** and **10**:
* *55 / 10 == 5* when using *integer division*.
* *55 % 10 == 5* when using the modulus operator.

## Implementation Strategy
Since the problem is asking us to find the first index such that *i % 10 == nums[i]*, we can write a method to search the array starting from the first element, then return immediately after fiding the first index which satisfies the condition above. We will do so using a *for* loop. Within the for loop itself, we'll check that condition for each index, and if it is satisfied, include a *return* statement right within the for loop. If the return statement is triggered, the rest of the for loop will not execute and the method will end right there. If the for loop iterates through all items and doesn't find anything which satisfies the condition, we'll have another return statement at the end of the method to return *-1*.

## Space and Time Complexity
The space complexity is *O(1)*, as we are only using a constant amount of extra space. In other words, we don't have any arrays, lists, etc, which grows as the input **nums** does. The time complexity is *O(n)* because we will need to visit up to **nums.length** items in the worst case scenario, which is the scenario where -1 is returned.