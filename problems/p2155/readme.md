# 2155. All Divisions With the Highest Score of a Binary Array
This is my solution for LeetCode's problem 2155: https://leetcode.com/problems/all-divisions-with-the-highest-score-of-a-binary-array/

## Problem Analysis
We are given a list of *0*s and *1*s and are told that we can divide it into two groups: *numsLeft* and *numsRight*, either of which can be empty depending upon where we make the division. The *division score* of an index is the sum of the total number of 0s in *numsLeft* and total number of 1s in *numsRight* where the two groups are divided at that index. We need to return all indices which have the highest division score, meaning that if there are any ties, we must return all indices which are tied for highest score. We will want to implement an algorithm which can do this while avoid redundant iteration or space usage.

Notice that when we move from left to right, the number of 0s in *numsLeft* never decreases. Similarly, for *numsRight*, the number of 1s never increases, or if we move from right to left, never decreases. We can take advantage of this to implement a solution which can find the indices in linear time.

## Implementation Strategy
Conceptually, we can use a *sumsum array* approach to count the number of 0s and 1s, then create arrays:

* **leftZeroCounts** to count the number of 0s which occur before each position in the input array.
* **rightOneCounts** to count the number of 1s which occur after, or at, each position in the input array.

We would use two iterations total to populate these arrays. However, we can improve on this concept to use less space. Instead of creating arrays, we can keep a single int to count and increment/decrement the number of occurences for each while we're calculating the scores themselves. The steps can be summarized as follows:

1. Iterate the input once to count the total number of zeros. Store in **numZeros**.
1. Create a *HashMap* **iScores** to keep a map of each score calculated to a list of indices which have that score.
1. Initialize **oneRightCount** to 0. We'll update this in the next loop as we move through the input.
1. For each index **i** in reverse order starting with *nums.length*:
    1. If *i < nums.length* and *nums[i] == i*, increment **oneRightCount**.
    1. Otherwise, decrement **numZeros**.
    1. Calculate **score** for this *i* by adding **numZeros** and **oneRightCount**.
    1. If this score is better than the current best score, update that value.
    1. Add *i* as a value for the current score in **iScores**.
1. Return the indices of the best score found from the map.

## Space and Time Complexity
The space complexity is *O(n)* because the size of the hash map will grow as the input size grows. However, we could make a tradeoff to make this *O(1)*, not including the output list itself: instead of using a map, we can iterate once to find the best score, then iterate again to collect all of those scores into the output list. This would save space but force us to make one more full iteration. The time complexity is *O(n)*, as the algorithm iterates over the entire structure twice.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)
1. [Discussion and Analysis on YouTube](https://youtu.be/Zxmgp0tIO4k)