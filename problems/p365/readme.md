# 365. Water and Jug Problem
This is my solution for LeetCode's problem 365: https://leetcode.com/problems/water-and-jug-problem/

## Problem Analysis
....
Let's break down the procedure for the first example, where **x=3**, **y=5**, and **z=4**:
```
1. Buckets start empty: x=0/3 | y=0/5
2. Add +3:              x=3/3 | y=0/5
3. Transfer:            x=0/3 | y=3/5
4. Add +3:              x=3/3 | y=3/5
5. Transfer:            x=1/3 | y=5/5
6. Remove -5:           x=1/3 | y=0/5
7. Transfer:            x=0/3 | y=1/5
8. Add +3:              x=3/3 | y=1/5
```

Notice the additions and removals: +3, +3, -5, and +3. Adding those yields 4. We can rephrase this as **ax-by=z**. We can rephrase this buckets problem as finding values of **a** and **b** that satisfies this. There can be infinitely many values for **a** and **b**, but they will all be multiples of some smallest values of **a** and **b**.

For two buckets and a target, we can reach the target if the greatest common divisors of **a** and **b** evenly divides the target.

## Implementation Strategy
We will implement a **gcd** method. For two numbers **a** and **b**, assuming **a>=b** (or flip inputs)
1. Subtract **b** from **a** until **a < b**. This is equivalent to **a % b**.
1. Recursively call with **gcd(b, a % b)**.

## Space and Time Complexity
To assess the time complexity of **gcd**, we need to find the worst case scenarios of **a** and **b**. The crucial operation in **gcd** is the **a % b** operation, so the worst case must be when **a % b == a - b**. In the worst case, **gcd(a,b)=1**, otherwise we would be skipping operations and this would not be the worst case. We can work backward to find the worst case values:
1. We know **b=1**. We should pick **a=2** to ensure we work up as slowly as possible, representing the worst case scenario.
1. **gcd(2, 1) -> gcd(3, 2)** when working backwards.
1. **gcd(3, 2) -> gcd(5, 3)** when working backwards.
1. **gcd(5, 3) -> gcd(8, 5)** when working backwards.

This represents the sequence `[1, 2, 3, 5, 8]` which is the Fibonnaci sequence (excluding the original 1 entry). **Fib(n) = Fib(n-1) + Fib(n-2)**. We can determine the complexity by observing the rate of growth of fibonacci numbers. I cannot provide a direct mathematical growth formula, but I've observed that as the sequence grows, its terms are bounded by ϕ^x, where ϕ is "phi," or "the golden ratio." This relates to the Fibonacci sequence by the fact that dividing two successive terms approaches that value, and more precisely as values become larger.

| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|---|---|---|---|---|---|---|---|---|--|
| 1 | 2 | 3 | 5 | 8 | 13 | 21 | 34 | 55 | 89 |
| 1.62 | 2.62 | 4.24 | 6.85 | 11.09 | 17.94 | 29.03 | 46.98 | 76.01 | 122.99


Therefore, the big O complexity is *O(log n)*, since the inverse operation has the inverse of an exponential growth rate.

The space complexity is *O(1)* since we use a constant number of variables that do not scale with input size.