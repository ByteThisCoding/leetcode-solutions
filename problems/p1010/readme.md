# 1010. Pairs of Songs With Total Durations Divisible by 60
This is my solution for LeetCode's problem 1010: https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60

## Problem Analysis
We need to find all pairs of numbers that are divisible by 60. We could easily solve this using two loops: for each number, check every other number and see if it forms a pair, but this solution is inefficient. To make it more efficient, we can use an object to map a song duration, or its pair's duration, to the number of times it occurs.

However, we need to account for the fact that the pair's sum duration is divisible by 60, which means the duration itself can be 60, 120, 180, or any other multiple. Note that if two numbers **x** and **y** equal 60, then for any multiple **m**, **m(x * y)=m * 60**. Therefore, we can modulo each input number in **time** by 60 and check if subsequent duration pairs equal 60.

## Implementation Strategy
We'll record the number of pairs **numPairs** and create a map **partialPairs** (though our implementation uses an array to map values). For each **timeItem** in **time**:
1. Modulo **timeItem** by 60.
1. Update **numPairs** by adding **partialPairs[timeItem]** to it.
1. Set new variable **pairPartner** to **(60 - timeItem) % 60** to calcualte the current item's required pair value. Modulo by 60 in case **timeItem == 0** to account for that edge case.
1. Increment **partialPairs[pairPartner]** so we can increment **numPairs** properly if we find it's pair partner in the future.

At the end, return **numPairs**.

## Space and Time Complexity
The time complexity is *O(n)* where **n** is the number of items in **time**. This algorithm iterates through each item exactly once and **partialPair** lookups are in *O(1)* time. The space complexity is *O(1)* because the amount of space we use is bounded by the 60 seconds rule.