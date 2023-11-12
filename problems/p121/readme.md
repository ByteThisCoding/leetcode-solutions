# 121. Best Time to Buy and Sell Stock
This is my solution for LeetCode's problem 121: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

## Problem Analysis
The problem asks you to look over a list which corresponds to prices of a stock on each **n**th day. You need to look over the list and figure out the maximum profit you could get if you purchased the stock on one day and sold it on a different day in the future.

This is easy to do if you take an approach of looking at each stock price, then for each price, look at all prices in the future. However, this approach would be very inefficient for larger data sets. Let's break down the problem to see if we can do better.

Consider a value **b** which represents any day you would buy stock and value **s** which represents any day you would sell that stock. For any day after **s**, the difference of stock price between that day and **b** will only be greater if the price on that day is greater than **prices[s]**. For example, in **[2, 1, 2, 5, 3]**, if **b** is 0 and **s** is 3, the difference in prices is 3, and for any **s** greater than that (i.e. the last day), the difference will be less. This gets us part way there, since we know how to deal with incrementing values of **s**, but you may have noticed that starting with **b** equal to 1 will yield a higher profit.

To assess incrementing values of **b**, notice that when you increment **b**, the difference will only be greater if the new value of **b** is lesser than the old value. However, if you do find a lesser value, any larger values found in the past cannot be used to sell if you use this lower value to buy, since you cannot sell a stock before you buy it. Therefore, you would need to start over and only buy on this day if you find a better profit.

## Implementation Strategy
We will use two pointer variables to keep track of where we are in the array, one for the day we would buy the stock and another for the day we would sell it. We'll also keep track of the smallest value we've found so far and the best difference we've found so far. Every time we find a smaller minimum value, we'll set the left cursor to that day and the right cursor to the following day. Otherwise, we'll keep incrementing the right cursor to find the highest difference corresponding to the maximum profit.

## Space and Time Complexity
The time complexity is *O(n)* (where **n** is the size of the input), as each input element is only accessed at most twice. The space complexity is *O(1)* because we use a constant number of variables that does not change with the size of the input.