# 2100. Find Good Days to Rob the Bank
This is my solution for LeetCode's problem 2100: https://leetcode.com/problems/find-good-days-to-rob-the-bank/

## Problem Analysis
We need to find a way of identifying indices in the input array where:
1. There are at least *time* items in the array to the left and to the right of the current index.
1. All positions up to, and including, *time* indices to the left, and to the right, are greater than or equal to the value at the current index.

This is similar to solving the problem of finding local minima and local maxima, but the "or equal to" part of this requirement means we cannot directly use an algorithm to find the local minima and maxima. However, we can re-use the concept behind that algorithm, which we will call the *minMax* algorithm for the rest of this discussion, to help us with this algorithm. In the minMax algorithm, we iterate at each point to check if the current value is either:

* Less than the previous and less than the next: local minima.
* Greater than the previous and greater than the next: local maxima.

In the page linked under *additional resources*, we also include some logic for dealing with equal values in minima, but we will not consider this here. Instead, we'll observe how this plays with the current problem. We are looking for indices which are similar to, but not exactly, local minima, in that we can also include duplicate sequential values (which are equal to eachother). If we consider a simplified situation where *time* is 0, then we have something that looks almost exactly like finding local minima, which we'll call *local minima-like*. If we set *time* to 1, we'll have the additional requirement that the *valley* be of size 3: 1 for the index itself and one additional index on each side to account for *time = 1*. If *time=2*, the valley must be of size 5, etc. In other words, we're looking form *local minima-like valleys* of *2⋅time+1* size. We'll return a list of indices of the center of each such valley.

## Implementation Strategy
In our implementation, we'll use two lists to help us find these valleys:

1. A list **numDaysBefore** to map indices in the input to the number of days preceeding it which are greater than or equal to the value at that index *within the current valley*. If a value is found which violates this, subsequent values will be reset.
1. A list **numDaysAfter** to map indices in the input to the number of days superceeding it which are greater than or equal to the value at that idnex *within the current valley*.

The table below outlines what these arrays would look like given the input from the first example provided by LeetCode:

| List          | Values                |
|---------------|-----------------------|
| Input         | [5, 3, 3, 3, 5, 6, 2] |
| NumDaysBefore | [0, 1, 2, 3, 0, 0, 1] |
| NumDaysAfter  | [0, 4, 3, 2, 1, 0, 0] |

When *n=2*, the solution, as provided by LeetCode, is *[2, 3]*. Notice that for each of those indices in **numDaysBefore** and **numDaysAfter**, the value is equal to, or greater than, *time*. For any index where the value is not less than time *in both arrays simultaneously*, we've found an index corresponding to "a good day to rob the bank".

## Space and Time Complexity
The space complexity is *O(n)*, since we need to initialize two seperate arrays of the same size as the input array. The time complexity is *O(n)*, as we'll be iterating over the input 3 times: once to calculate the values for **numDaysBefore**, once for **numDaysAfter**, and once to check how the values line up.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Algorithm to Find Local Minima and Maxima in an Array](https://bytethisstore.com/articles/pg/find-min-max-list)
1. [Discussion and Analysis on YouTube](https://youtu.be/n_vY766hT98)