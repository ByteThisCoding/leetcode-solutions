# 1720. Partition Array According to Given Pivot
This is my solution for LeetCode's problem 1720: https://leetcode.com/problems/decode-xored-array

## Problem Analysis
An *xor* operation on boolean variables compares the two booleans and returns true if their values are different and false otherwise. An xor operation on numbers comparies each *n*th bit, and if they are different, sets the output number's **n**th bit to 1, or 0 otherwise. Consider what happens if you repeat this process. The following is a truth table that has two boolean variables, the result of an xor operation, and the result of a 2nd xor operation between the original xor and the original 2nd variable:

| A | B | A XOR B | (A XOR B) XOR B |
|---|---|---|---|
| 0 | 0 | 0 | 0 |
| 0 | 1 | 1 | 0 |
| 1 | 0 | 1 | 1 |
| 1 | 1 | 0 | 1 |

Notice that the first and last columns are the same. A repeated XOR operation in this manner always yields the original variable input. The same applies to xor operations on numbers.

## Implementation Strategy
Create a new list and add the first value in as given in the parameters. Then, loop over each encoded input and xor it with the last value (so far) in the new list and add it to the list.

## Space and Time Complexity
The time complexity is *O(n)* as we iterate through each input once. The space complexity is *O(n)* since we create a new list to store the output.