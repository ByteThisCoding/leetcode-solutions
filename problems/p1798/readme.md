# 1798. Maximum Number of Consecutive Values You Can Make
This is my solution for LeetCode's problem 1798: https://leetcode.com/problems/maximum-number-of-consecutive-values-you-can-make/description/

## Problem Analysis
There are many types of algorithms that can be used, but most of them are inefficient. For example, you could use a loop, then a nested loop, to add up all the sums, sort them, and find the first non-consecutive number. To be more efficient, you could sum up all coins, initialize a boolean array to track if each **n**th sum can be calculated, iterate over the coins, and for each coin, check and increment the items in the array. However, these methods are inefficient.

To make the algorithm more efficient, you can remove some coins from consideration. Note that any coin that has a higher value than the first non-consecutive sum can be ignored. Consider a list of coins `[1, 2, 3, 7, 8]`. The largest consecutive value must be at least 3 since we see a run "1, 2, 3". From those three numbers, the highest consecutive value so far is actually 6, from adding up all values so far. The next coin is 7, and since all coins were used up already to get to 6, 7 cannot be reached and is the first non-consecutive integer.

If the coins were `[1, 2, 4]`, the same situation would be reached with the number 4. If the coins were `[1, 1, 2, 4]` or `[1, 2, 2, 4]`, it would not, since the extra number would provide the difference. If you sort the input coins, then do a similar operation, you can find the coins by iterating over the sorted list one time.

## Implementation Strategy
Sort the list of coins and keep track of a variable **consecutiveCount**, initially set to 1 (to include 0), that records the largest consecutive number found so far. Then, for each coin (in the now sorted list), if it is larger than the largest consecutive, stop searching and return the current value of **consecutiveCount**. If it is not larger, add its value to **consecutiveCount** and move to the next coin.

## Space and Time Complexity
The time complexity is *O(n log n)* because the input list is sorted for *O(n log n)* complexity, then is iterated over exactly once. The space complexity is *O(1)* since only a small constant number of variables is used.