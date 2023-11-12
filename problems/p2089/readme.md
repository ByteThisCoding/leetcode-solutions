# 2089. Find Target Indices After Sorting Array
This is my solution for LeetCode's problem 2089: https://leetcode.com/problems/find-target-indices-after-sorting-array/description/

## Problem Analysis
The problem asks us to sort the input list, then return the indices of all elements from that sorted list which are equal to the target. This could be done simply by using a built-in sorting algorithm to sort the input, then iterate over it once. Alternatively, you could hand-write a sorting algorithmn. However, you can find the list of indices without actually sorting the list at all.

## Implementation Strategy
Iterate over the input list and count the number of elements that are lower or higher than the target in two separate variables. Once you have the counts, you know that the first occurrence of the target must be the same as the number of smaller elements. The number of occurrences of the target is equal to the input length minus the number of occurences of lesser and greater elements, so you can calculate the total number of target inputs from that. Then, iterate and create a list of elements with the corresponding indices.

## Space and Time Complexity
The time complexity is *O(n)* as this iterates through the input list once and iterates at most *n* times again to create the output list. The space complexit is *O(n)* as this uses a constant number of variables for calculation, but then must have a list of up to *n* elements to return the requested output of the function.