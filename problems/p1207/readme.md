# 1207. Unique Number of Occurrences
This is my solution for LeetCode's problem 1207: https://leetcode.com/problems/unique-number-of-occurrences

## Implementation Strategy
We will create a map **countMap** to count the number of occurences of each input. Then, we'll iterate over the input array, and for each item:
* if it's not in **countMap**, add it and set its value to 1.
* if it's in **countMap**, increment its value by 1.

Then, we'll create a new set **counts** with **countMap.values** to filter out duplicate occurences (i.e. if more than one input had the same number of occurences). If these two objects are the same size, return true. Otherwise, return false.

## Space and Time Complexity
This has a time complexity of *O(n)* because we iterate over the input array once, **countMap** once (when creating the set), and because insertions into the map are of *O(1)* time. The space complexity is *O(n)* because we are storing up to **n** elements in **countMap**.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)