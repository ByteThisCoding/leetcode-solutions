# 49. Group Anagrams
This is my solution for LeetCode's problem 49: https://leetcode.com/problems/group-anagrams/

## Problem Analysis
Given a list of strings, we need to return a list *of lists* of strings. Each sublist will contain all words which are anagrams of each other. Just as in many problems, we could write an algorithm whichs a nested / quadratic iteration, but this would be a time complexity of *O(n^2)*, so we will focus on creating a more efficient solution. In order to prevent the need for this redundant iteration, we'll need to use a data structure which will help keep track of results from previous iterations which we can refer to at each step. We'll also need to account for the following cases:

1. The input is an empty list.
1. The input has no anagrams.
1. Everything in the input is an anagram.
1. Normal cases: mix of anagrams and non anagrams.

## Implementation Strategy
To make our solution as efficient as possible, we will use a *HashMap* to help keep track of strings we've already found. While we iterate over the input strings, we'll *sort* the characters of the string, then use that sorted string as a key for the map. The value will be the index of the sublist in our output list where we must return the string. If the map doesn't have any such value, we've found a string which we haven't found an anagram for yet. The full algorithm can be summarized in the steps below:
1. Initialize the **anagrams** list which we'll return at the end.
1. Initialize **sortedAnagramMap** which will map *sorted* strings to indices in **anagrams**.
1. For each string **str**:
    1. Sort the string characters and store in **sortedStr**.
    1. If **sortedAnagramMap** has a key **sortedStr**: get the value from the map and insert **str** into that sublist index in **anagrams**.
    1. Otherwise, create a new list with just **str** and add it to **anagrams**.
1. Return **anagrams**.

## Space and Time Complexity
The space complexity is *O(n)*, as we will be keeping our *HashMap* with up to *n* key value pairs. The time complexity is *O(n)* **if** we don't include the cost of sorting each string's characters. *O(n)* is due to the fact that we iterate over each string exactly one time, and other operations, such as *HashMap* lookups are constant time. If we **do** include sorting, the time complexity is *O(n⋅log(s))*, where *s* is the length of each string.

## Additional Resources
1. [Introduction to Hash Maps](https://bytethisstore.com/articles/pg/implement-hash-table)
1. [Discussion and Analysis on YouTube](https://youtu.be/b35rax6GQkM)