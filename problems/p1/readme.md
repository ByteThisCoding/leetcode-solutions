# 1. Two Sum
This is my solution for LeetCode's problem 1: https://leetcode.com/problems/two-sum

## Problem Analysis
Given a list of **nums** and a **target**, we need to see if the sum any two elements in **nums** equals **target**. Furthermore, we want an efficient implementation, so we don't want to iterate through the list once per element. Instead, we can use a map to store values we've already found, then for each subsequent item, check if the map contains the difference between target and that item.

## Implementation Strategy
We will iterate over the list exactly once and check our progress as we go. First, we'll create a map **values** that maps between values in the list and their indices. Then, we'll loop over the list, and for each item and index **i**:
1. Subtract the item from **target** and store in variable **diff**.
1. Check if **values** contains **diff**.
1. If it does, we can return `[**i**, **values[diff]**]`, an array containing the indices of the two items that sum to **target**.
1. If not, continue through the rest of the items.

If no sum is found, return an empty array.

## Space and Time Complexity
The time complexity is *O(n)*, where **n** is the size of **nums**, because we iterate over each item exactly once, and because hashmap lookups are in constant time. The space complexity is *O(n)* because, in the worst case scenario, **values** will contain every element in **nums**.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)