# 2483. Minimum Penalty for a Shop
This is my solution for LeetCode's problem 2483: https://leetcode.com/problems/minimum-penalty-for-a-shop

## Problem Analysis
We need find an efficient way of determining the minimum penalty given two conditions that lead to penalties:
1. there is no customer when the store is open.
1. there is a customer when the store is closed.

We'll refer to these as penalties #1 and #2.

Imagine iterating over a single input and counting the number of *N*s and *Y*s. At the **i**th position, you already know how many *N*s have occured so far, but you don't know how many *Y*s are yet to occur. This means you can calculate the #1 penalty given the information you have, but you cannot seem to calculate the #2 penalty since you haven't traversed the entire string yet.

What if we consider the number of *Y*s as a negative number representing the total number of *Y* minus the current number? For example, in the input **[Y, N, Y, Y, Y, N, Y]**, at index 2, we've found 2 *Y*s, so the number of penalty points for penalty #2 is the number of *Y*s in total minus 2. With this strategy, at any given time, the total penalty points is: **(yCountTotal - yCount) + nCount**. We can rearrange this as **yCountTotal + nCount - yCount**. Notice the inner part **nCount - yCount**; regardless of how many *Y*s there are (i.e. **yCountTotal**), **nCount - yCount** will be a relative indicator of which positions offer the least penalties.

With that, we can ignore **yCountTotal** and find the index of the smallest penalty simply by finding the min value of **nCount - yCount** at each index.

## Implementation Strategy
Setup variables **smallestNyCount** and **smallestNyCountIndex** to represent the smallest penalty found so far. Setup **nCount** and **yCount** to keep track of the number of *N*s and *Y*s so far. Then, for each char in the input string:
* Create **thisNyCount = nCount - yCount**
* If **thisNyCount** is smaller than **smallestNyCount**, update to reflect we've found a new minimum.
* Update **nCount** or **yCount** depending on the char found.

After, do one last check to see if we should close the store at the very end. Then, return **smallestNyCountIndex**.

## Space and Time Complexity
The time complexity is *O(n)* because we only iterate over the input once. The space complexity is *O(1)* because we only use a few variables that do not grow with the input size.