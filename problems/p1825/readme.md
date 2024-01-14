# 1825. Finding MK Average
This is my solution for LeetCode's problem 1825: https://leetcode.com/problems/finding-mk-average/

## Problem Analysis
We need to write a program that will take in a stream of integers, and at any point in the stream, calculate the average of the last **m** inputs excluding the smallest **k** and largest **k** integers. The solution needs to be efficient enough to work on very large inputs and many calculations of **mkAverage**.

We need to efficiently:
* Keep track of the last **m** integers, or otherwise ensure integers beyond that aren't included in the calculations.
* Keep track of the smallest **k** and largest **k** integers, or otherwise ensure they aren't included in the calculations.

## Implementation Strategy
To keep track of the last **m** elements, we will use a circular buffer. This is a fixed-size array data structure that stores a certain number of elements, which is **m** in this case, and begins to remove the oldest elements first once its capacity has been reached. This is similar to a queue or linked list, but we don't need to use node objects to store each value.

We will also maintain a binary search tree to sort the integers as we receive them. We will internally use a **TreeMap** to store a mapping between the unique integer values the stream receives and the number of occurences.

As the stream receives inputs, we will keep a running sum of all of the inputs. When items are removed from the circular buffer, we will subtract their values from the running sum.

When **calculateMKAverage** is called, we will iterate over the first **k** and last **k** inputs of the binary search tree and calculate their sums, subtract them from the running sum, and divide by the number of elements, which is **m-2k**.

## Space and Time Complexity
The time complexity for insertion is *O(log n)*. The insertion into the circular buffer is *O(1)* but the insertion to the binary search tree is *O(log n)*. The time complexity for **calculateMKAverage** is *O(k)*, as we iterate over **2k** elements in the binary search tree.

The space complexity is *O(m)*, as we store two copies of **m** elements from the stream.

Note, we could make the sum calculation more efficient if we directly implemented our own balanced binary search tree. We could update sums as we insert elements instead of iterating each time **calculateMKAverage** is called. The time complexity for **calculateMKAverage** would be *O(1)* instead of *O(k)*.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to Circular Buffers](https://bytethisstore.com/articles/pg/circular-buffer)
1. [Introduction to Binary Search Trees](https://bytethisstore.com/articles/pg/binary-search-tree(footer:april22))