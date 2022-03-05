# 1705. Maximum Number of Eaten Apples
This is my solution for LeetCode's problem 1705: https://leetcode.com/problems/maximum-number-of-eaten-apples/

## Problem Analysis
We are given to int arrays, **apples** and **days**. In the problem statement: the *i*th entry of each array corresponds to the number of apples grown by an apple tree on that day, and how many days those apples will survive before rotting. The problem also states, in the form of a pun, that we will be eating **at most** one apple a day *to keep the doctors away*. We need to return the maximum amount of apples we could possibly eat that are not rotten. We will need to iterate over the number of days, but we must keep in mind that such an iteration corresponds to time elapsed in days, so if we are on even the 2nd day (2nd iteration), some apples may have already expired. Furthermore, at each point, when apples are created, they will expire *days[i]* days after the current date, which is *days[i] + i*. We must also account for the fact that we can continue eating apples on days after the initial arrays have been iterated over, as some apples may not have yet rotted.

## Implementation Strategy
We will use a *priority queue* to hold data on when apples will expire and how many will expire at that day. The priority queue will hold *AppleBasket* objects, where AppleBasket is a class which holds the expiration date and the number of apples at that date. Note that more than one apple basket can exist for a given expiration date. Each time we peek or dequeue from the queue, we are guaranteed to have the basket with the smallest expiration date. At each iteration, we'll insert a new apple basket, remove all rotten apples, and eat an apple if one is available. Afterwords, we'll keep eating and removing until the queue is empty.

## Space and Time Complexity
The space complexity is *O(n)*. The priority queue will have at most *n* elements, and only constant additional space is used. The time complexity is *O(n logn)*. Each element is iterated over once, then up to n more iterations to account for non rotten apples after we've performed our initial iteration. Each insertion into the queue has a complexity of *O(log n)*.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Binary Heaps](https://bytethisstore.com/articles/pg/binary-heap): priority queues are implemented using binary heaps.