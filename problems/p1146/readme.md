# 1146. Snapshot Array
This is my solution for LeetCode's problem 1146: https://leetcode.com/problems/snapshot-array

## Problem Analysis
We need to maintain the revision history for an array of numbers. This doesn't mean we need to copy the entire array each time **snap** is called. We can proceed by only keeping a revision history for each index. However, we need to ensure that maintaining the revision history isn't just as expensive, in terms of time and space, as maintaining each individual snapshot array.

## Implementation Strategy
We will use a few structures to hold the data and revision history:
1. **elements** will keep track of the current state of the array.
1. **currentSnap** will keep track of the current snapshot id.
1. **elementSnapshotChanges** will, for each index in the array, keep track of the snapshots in which the value changed (technically, the last index *before* the value changed).
1. **revHistory**, a list of maps where each element in the list corresponds to a snapshot index, to keep track of indices that were changed.

When setting values:
1. Record the old value and update **elements[index]** to the new value.
1. If we're not in the first snapshot and **revHistory** doesn't contain a record of **index** in the previous snap:
    1. Add **index -> oldValue** in **revHistory[currentSnap - 1]**
    1. Add **currentSnap - 1** to **elementSnapshotChanges[index]**

When getting values:
1. Use binary search to find the smallest snapshot update index for **index** that is greater than or equal to **snap_id** and store in **mostRecent**.
1. If that value is found, get the map from **revHistory[mostRecent]** and return it's value for **index**.
1. If not, return **elements[index]**, as this means there was no update made since **snap_id**

When getting a snapshot:
1. Add a new map to **revHistory**
1. Increment **currentSnap**
1. Return the prevous value of **currentSnap**

## Space and Time Complexity
We will refer to **n** and **s** to refer to the input size and number of snapshots. The space and time for each operation is:
* **set**
    * The time complexity is *O(1)* because each operation is constant: updating **elements**, checking **revHistory**, and updating **revHistory**.
    * The space complexity is *O(1)* because the operation itself uses a constant number of variables.
* **get**
    * The time complexity is *O(log n)* because the binary search operation runs in *O(log n)* and everything else runs in constant time.
    * The space complexity is *O(1)* because it uses a constant number of variables.

Note that each operation has a space complexity of *O(1)*, but the data structure itself has a space complexity of *O(n * s)*, because in the worst case scenario, every index will have a different value at every snapshot.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Introduction to HashMaps](https://bytethisstore.com/articles/pg/implement-hash-table)
1. [Binary Search](https://bytethisstore.com/articles/pg/binary-search)