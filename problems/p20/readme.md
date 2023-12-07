# 20. Valid Parentheses
This is my solution for LeetCode's problem 20: https://leetcode.com/problems/valid-parentheses

## Problem Analysis
We need to validate that the three types of parenthesis open and close correctly. The constraints are:
* Counts of openers and closers must be consistent for all chars
* Inner pairs must be closed before outer pairs are closed

Therefore, we cannot simply keep track of the counts of openings and closings, we must keep track of the order of chars as well.

## Implementation Strategy
We'll use a stack to record each opening parenthesis we encounter. Then, whenever we encounter a closing parenthesis, we'll check the stack to see if it matches the expected opening parenthesis. If it does, we'll pop the stack and repeat the process. This also covers nested parenthesis correctly, such as `[[()]]`. At the end, we'll check that the stack is emtpy, and if it is, we return that the string is valid.

## Space and Time Complexity
The time complexity is *O(n)*, as we iterate over the string exactly once and since stack operations are constant time. The space complexity is *O(n)* as the size of the stack is proportional to the size of the input string.

## Additional Resources
1. [Introduction to Stacks](https://bytethisstore.com/articles/pg/intro-to-stacks)