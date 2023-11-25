# 380. Insert Delete GetRandom O(1)
This is my solution for LeetCode's problem 380: https://leetcode.com/problems/insert-delete-getrandom-o1

## Problem Analysis
We need to create a custom set implementation that supports insertion, removal (retrieval), and random access, all in constant average time complexity. Commonly, a hash set or hash map is implemented using an array of linked lists, combined with a hashing function, to store elements in an array using the linked lists to handle the case that a hash function causes collisions (i.e. two elements hash to the same index). However, that approach will not allow us to access randomly in *O(1)* time because the lists will vary in size, so we won't be able to access truly randomly without iterating over thoses lists.

Instead, we can use extra space to record values and their positions in the array. When inserting and deleting, we can update the list and the mapped values, and upon random access, simply return a random number from 0 to the size of the array.

## Implementation Strategy
We'll start off by creating variables **items** and **valuesMap** to store the actual values inserted into the set and the mapping between values and their indices in **items**. We'll use **size** to track the number of elements in our set. When we have too many items, we will resize our **items** array. When we insert, if the item doesn't exist in the map:
1. We'll add an entry **item -> size** in the map.
1. Set **items[size] = item**
1. Increment **size++**.

When we remove items, if it exists in the set,:
1. We'll get the index of the value we're trying to remove from **valuesMap** and store in **index**.
1. Get the value of the last item in the array **items[size-1]** and store in **lastItem**.
1. Update the map so **lastItem -> index**.
1. Remove the deleted value from the **valuesMap**.
1. Set **items[index] = lastItem**.
1. Decrement **size--**.

With this, we have a flat array of all items, so we can call **Math.random() * size** to get a random element in constant time.

## Space and Time Complexity
The time complexity is *O(1)* since each operation is done in constant time, without the need to iterate over our arrays or maps. The space complexity is *O(n)* since we must store every element that is added.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)