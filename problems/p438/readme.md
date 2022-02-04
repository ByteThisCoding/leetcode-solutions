# 438. Find All Anagrams in a String
This is my solution for LeetCode's problem 438: https://leetcode.com/problems/find-all-anagrams-in-a-string/

## Problem Analysis
Given a string *s* and string *p*, we need to find all occurences of all **palindromes** of *p* within *s*. We can consider this as an extension of finding all substrings of *p* in *s* where the substring can be any ordering of the characters in *p*. With this in mind, there are a few base cases and edge cases we need to account for:
1. The string *p* is longer than the string *s*.
1. There are no occurences of a palindrome of *p* in *s*.
1. There are close matches of *p* in *s*, but no direct matches.
1. There are multiple indices of matches where their difference is smaller than the size of *p*.
    * For example: *s=abababab* and *p=abab*, we can see matches on index 0 and index 2 even though the length of *p* is 4.

## Implementation Strategy
A simple approach might be to iterate over every *p* length substring in *s*, and for each substring, directly compare it against *p* to check if all of the characters are present, and if so, add the index to a list of indices. Though this will work, it will perform redundant string iteration and checking, as each *p* length substring will intersect with *p.length - 1* substrings. In our implementation, we will take a more efficient approach which will only iterate over each string once. First, we will iterate over *p* to check how many occurences of each character exists in *p*. Then, we will iterate over *s* and check against a map to see which characters are needed and use a circular buffer of the same size as the length of *p* to "reset state" after each iteration.

This algorithm will use four variables:
1. An integer **remainingCharsCount** which is initialized to the string length of *p*.
1. A *HashMap* **remainingChars** to keep track of how many of each individual character from *p* is required.
1. A *CircularBuffer* **foundChars** to keep track of characters found so far. This will have a buffer size equal to the string length of *p*.
1. An *ArrayList** **foundIndices** to return at the end of the method.

The algorithm itself can be summarized with the steps below:1
1. Check the base case that *s.length < p.length*. If it is, return an empty list and do not proceed to the steps below.
1. Initialize *remainingCharsCount* to *p.length*.
1. Initialize *foundChars* with a buffer size of *p.length*.
1. Initialize the map *remainingCharsCount* which maps a character to an integer.
1. Iterate through the chars in *p*. For each char *c*: increment the value of *c* in *remainingCharsCount*, or set to 1 if it isn't yet in the map.
1. Iterate for each character *c* in *s*:
    1. Push *c* to *foundChars*.
    1. If *remainingChars* has a key of *c* (in other words, if *c* appears in *p*):
        1. Get the value from the map and store in *existingRemainingCount*.
        1. Update the value in the map to *existingRemainingCount + 1*.
        1. If *existingRemainingCount > 0*
            1. Decrement *remainingCharsCount*
            1. If *remainingCharsCount = 0*, we've found a palindrome of *p*, add the index to *foundIndices*.
1. Return *foundIndices*.

## Space and Time Complexity
We will describe the space and time complexities in terms of *ls*, which is the length of *s*, and *lp*, which is the length of *p*. The space complexity is *O(lp)*, as we have a map with up to *lp* key-value pairs and a circular buffer of size *lp*. The time complexity is *O(lp + ls)*, as we iterate through each string once.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Circular Buffers](https://bytethisstore.com/articles/pg/circular-buffer)
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)