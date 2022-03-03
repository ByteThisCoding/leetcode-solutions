# 1493. Longest Subarray of 1's After Deleting One Element
This is my solution for LeetCode's problem 1493: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/

## Problem Analysis
We are given an array **nums** where every element is a 0 or 1. We need to find the length of the longest consecutive sequence of 1s where one element is removed. If **nums** does not have any element which is not a 1, we must still remove one of the elements and return the corresponding count, which would be *nums.length - 1*. The naive approach would be to use two loops, one of which is nested, find each non-1 element, and scan ahead. This would have a time complexity of *O(n^2)* and thus would be inefficient for this problem, since solutions in *O(n)* do exist. We can use some additional space to improve the time complexity.

An improved implementation, which uses *O(n)* space and time, is to iterate through the input array once, store the indices of each non-1 value in a separate array **nonOneIndices**, then iterate over that array starting with the item at index 2, compare the differences between every other element, and return the greatest difference. The algorithm can be summarized as:
1. Create the list of indices **nonOneIndices**.
1. Add *-1* as an index. This is not a real index but will be convenient to have for later steps.
1. Iterate through **nums**, and for each non-1 value, add that index to **nonOneIndicess**.
1. Add *nums.length* as an index to **nonOneIndices**. This is also not a real index but is done for convenience.
1. Initialize an int **bestDiff** to 0.
1. Iterate through **nonOneIndices** starting with *i=2*:
    1. Initialize an int **diff** to *nonOneIndices[i] - nonOneIndices[i - 2] - 2*.
    1. If *diff > bestDiff*, set **bestDiff** to **diff**.
1. Return **bestDiff**.

In the implementation above, the *-2* subtraction is done so we don't include the current index and the comparison index as part of the chain of 1s. We use *i* and *i-2* and skip over *i-1* to account for the deletion of one element. This is an improvement over the quadratic iteration approach, but we can simplify it further to reduce the space complexity to *O(1)*. We'll outline this process below.

## Implementation Strategy
Instead of creating a separate array of indices and iterating over that after the initial iteration, we can accomplish everything within the first iteration using two variables: **lastNonOneIndex** to record the previous index where the value is not 1, and **prevLastNonOneIndex** to record the previous index to that. Then, during iteration, when *nums[i] != 1*, perform the logic of taking the difference as described above and upate **bestDiff** if we've found something better. The full algorithm can be summarized as:
1. Initialize ints **lastNonOneIndex** and **prevLastNonOneIndex** and set both to -1.
1. Iterate through **nums**. For each value which is not 1:
    1. Initialize an int **diff** to *i - prevLastNonOneIndex - 2*.
    1. If *diff > bestDiff*, set **bestDiff** to **diff**.
    1. Set **prevLastNonOneIndex** to the value of **lastNonOneIndex**.
    1. Set **lastNonOneIndex** to the value of **i**.
1. Perform one last check to see if *nums.length - prevLastNonOneIndex - 2* is larger than **bestDiff**. Return the largest value.

These steps accomplish the same goal without the need of an array which can grow as the input grows.

## Space and Time Complexity
The space complexity is *O(1)* as we are only using a constant number of variables throughout the iteration. The time complexity is *O(n)* as we will be visiting each element in the input exactly once.
