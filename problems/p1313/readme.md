# 1313.  Decompress Run-Length Encoded List
This is my solution for LeetCode's problem 1313: https://leetcode.com/problems/decompress-run-length-encoded-list

## Problem Analysis
Each input list **nums** has an even number of elements. Each even index (starting from 0) and the index to its right form a pair. We need to return an output list, and each pair describes a number and the amount of consecutive occurences of that number in the output. We need to fill in the output list with those numbers with the correct counts.

## Implementation Strategy
We will iterate over **nums** once to calculate the size of the output. Then, we will initialize the **output** list with that size and iterate over **nums** again. For each pair, fill in the portion of the array as described by that pair.

## Space and Time Complexity
The time complexity is *O(n)* as we iterate over the list exactly twice. The space complexity is *O(n)* because the size of the output is proportional to the size of the input.