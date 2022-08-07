# 1961. Check If String Is a Prefix of Array
This is my solution for LeetCode's problem 1961: https://leetcode.com/problems/check-if-string-is-a-prefix-of-array/

## Problem Analysis
Given a string *s* and an array of strings *words*, we need to see if we can take one or more entries from *words* in order to recreate *s*. For example:
* s="ByteThis"
* words=["By", "te", "Thi", "s", "LeetCode"]

In this example, we can combine the first four entries of *words* to form *s*. Note that if *s* was "Byte This" with the space, we then could not, because the space character was not included in any of the words in *words*.

Before implementing the code, we need to ensure our implementation account for a few edge cases as we're writing the code:
* There are not enough words / characters to recreate the string, even if the words we do have recreate part of the string. For example: *s=abcdefg* and *words=['abc', 'def']* is missing "g".
* Part of a word extends past the remaining characters in *s*. For example: *s=abc* and *words=['ab', 'cd']*.
* *s* is empty or *words* is empty.

Lastly, we do not want to do any type of algorithm which re-creates substrings of *s* or iterates over *words* more than once, as we can implement a more efficient algorithm to satisfy the problem.

## Implementation Strategy
We will loop over each word, and as we're doing so, keep track of which part of *s* we're working with. When we hit edge cases as described above, we'll exit the loop. The full algorithm can be described as:
1. Create a boolean *violationFound* to keep track of whether or not we've found a reason why *s* isn't a prefix string. Initially set it to *false*.
1. Create an int *sIndex* so we can keep track of which part of *s* we need to work with.
1. For each *word*:
    1. Get the start and end indices of the substring of *s* we're checking using the length of *word* and *sIndex*.
    1. If the length of *s* is less than the end of that substring, set *violationFound* to *true* and exit.
    1. If that substring of *s* is not equal to *word*, set *violationFound* to *true* and exit.
    1. Add the length of *word* to *sIndex*.
    1. If *sIndex* extends beyond the length of *s*, exit the loop.
1. If *sIndex* is less than the length of *s*, set *violationFound* to *true*.
1. Return *!violationFound*.

## Space and Time Complexity
This has a time complexity of **O(s + w)**, where *s* is the length of the input string and *w* is the number of words. The space complexity is **O(1)**.