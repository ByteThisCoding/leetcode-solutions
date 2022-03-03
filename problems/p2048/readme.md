# 2048. Next Greater Numerically Balanced Number
This is my solution for LeetCode's problem 2048: https://leetcode.com/problems/next-greater-numerically-balanced-number/

## Problem Analysis
An integer *n* is considered to be **numerically balanced** if, for every distinct digit *d* in *n*, there are exactly *d* instances of that digit in *n*. This means numbers such as 22 and 3133 are balanced numbers, whereas 21 and 30331 are not. To check if any given number is numerically balanced, we can count the number of occurences of each digit, then check if each digit *d* occurs *d* times based on what we've counted. Since there is a maximum of 9 distinct digits which can appear in a number, we can use an int array to map each digit (index) to its count (value), then iterate from 0-9 to check if the condition is satisfied. With this knowledge, we could implement an algorithm to loop over integers, starting from *n+1*, and check each value until we find the next numerically balanced one. However, the naive version of this approach would redundantly re-count digits. In almost all cases, most digit counts will remain the same at each increment. With this in mind, we can modify the approach to re-use counts from previous iterations.

## Implementation Strategy
We will be using a **linked list** to hold each digit of a given integer. The root node of the linked list will contain the digit to the right, and each subsequent node will contain digits to the left. In other words, the linked list holds the digits in reverse. We'll also have the head node store how many occurences of each digit occur in an array, where the index is the digit and the value is the number of occurences of that digit. The algorithm can be summarized as:

1. Create the custom *linked list* **numAsList** and initialize it with *n + 1*:
    * The linked list object will recursively create one node for each digit in the input number.
1. Check if the number is a balanced number:
    1. Iterate over the digit counts array in **numAsList**. For each number, if it is not zero and not equal to the index in the array, then the number is not a balanced number.
    1. If no violations have been found, it is a balanced number.
1. If it is a balanced number, return the int value of **numAsList** immediately and do not proceed to the steps below.
1. Increment the value of **numAsList**:
    1. The head node will increment its digit value. If it's digit value is 9:
        1. Set it to 0.
        1. Call increment on the *next* node.
    1. If not, increment the current digit.

At each iteration, we'll check the conditions to see if it's a balanced number, and if not, increment the value represented by the linked list. The linked list implementation will recursively update its nodes to properly set the digits and update the overall digit counts.

## Space and Time Complexity
The space complexity is *O(log n)*, as the linked list will take *floor(log10(n))* nodes to store each digit in the input number. The time complexity is *O(log n)*, as it will take a constant number of iterations to find each balanced number, and each iteration requires roughly *floor(log10(n))* operations.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Linked Lists](https://bytethisstore.com/articles/pg/linked-list)