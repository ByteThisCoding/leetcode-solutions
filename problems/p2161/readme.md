# 2161. Partition Array According to Given Pivot
This is my solution for LeetCode's problem 2161: https://leetcode.com/problems/partition-array-according-to-given-pivot/

## Problem Analysis
We are given an input array and a *pivot* value. We need to re-order the array so that:

1. All values less than *pivot* appear to the left of the first instance of *pivot*.
1. All values greater than *pivot* appear to the right of the last instance of *pivot*.
1. All values equal to pivot appear together in the center of the input.
1. The relative ordering of each value within its group is unchanged.

The last restriction, which requires the relative ordering to be preserved, prevents us from taking certain approaches, such as using two pointers to swap elements until we reach the pivot point. We will need to be careful to account for this in our implementation.

## Implementation Strategy
We will use two arrays to help keep track of which items belong on the left and right sides of the array: **leftPivotValues** and **rightPivotValues**. We'll also use a counter **numPivotValues** to count how many items are equal to the pivot value. We'll iterate over the entire input, and for each item:
1. If the item is less than the pivot, add it to **leftPivotValues**.
1. If the item is greater than the pivot, add it to **rightPivotValues**.
1. If equal to the pivot, increment **numPivotValues**.

Once these arrays and values are in place, we'll iterate over each one to replace the values in the original input *nums*:
1. Iterate over **leftPivotValues** and set *nums[i]* to *leftPivotValeus[i]*.
1. Update **numPivotValues** entries immediately after the entries added in the previous step and set them all to the pivot value.
1. Update the remaining values to the values in **rightPivotValues**.

This approach will allow us to implicity "sort" the input array based on the pivot, using additional space, while maintaining the relative ordering of the input values.

## Space and Time Complexity
This has a space complexity of *O(n)*, as the two arrays combined will contain up to *nums.length* entries. The has a time complexity of *O(n)*, as we will be performing *2n* operations total.

## Additional Resources
The links below outline some of the points discussed above in more detail.
1. [Discussion and Analysis on YouTube](https://youtu.be/qnpj7hXLRM0)