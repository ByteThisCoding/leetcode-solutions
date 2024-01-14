# 2186. Minimum Number of Steps to Make Two Strings Anagram II
This is my solution for LeetCode's problem 2186: https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram-ii/

## Problem Analysis
We need to make the two words anagrams of each other by appending characters. The problem states we can only append characters, not remove them. Therefore, we only need to determine which characters from each string are missing in the other string and how many. That count is equal to the number of steps it would take to make the two words anagrams of each other.

## Implementation Strategy
We will use a map to track the number of letter differences between each word. Iterate over the chars of each string and keep track of how many times each letter occurs. We'll use a single hash map and increment the map count when a letter occurs in **s** and decrement when it occurs in **t**. Then, we'll take the absolute values of each count and return their sum.

## Space and Time Complexity
The time complexity is *O(s+t)*, as we iterate over each string once, then iterate up to **s**+**t** to get the char counts. The space complexity is *O(s+t)* as the map will have up to **s**+**t** chars.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)