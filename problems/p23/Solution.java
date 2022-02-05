package problems.p23;

import java.util.PriorityQueue;

public class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        // store the output in a new list
        ListNode mergedListHead = null;
        ListNode mergedListCurrentNode = null;

        // use a heap to sort each of the beginning elements
        PriorityQueue<HeapNodeValue> firstItemsHeap = new PriorityQueue<>(
                (HeapNodeValue a, HeapNodeValue b) -> a.value - b.value);

        // initially put every first item into the list
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                firstItemsHeap.add(new HeapNodeValue(lists[i].val, i));
            }
        }

        // remove the first item and set the head + first node ref
        // if no items, the list is empty, so return null
        if (firstItemsHeap.size() == 0) {
            return null;
        }

        mergedListHead = processSingleElement(firstItemsHeap, lists);
        mergedListCurrentNode = mergedListHead;

        // while the heap has items left, keep adding and removing
        while (firstItemsHeap.size() > 0) {
            // remove the item at the top and add to our output list
            ListNode nextNode = processSingleElement(firstItemsHeap, lists);
            mergedListCurrentNode.next = nextNode;
            mergedListCurrentNode = nextNode;
        }

        return mergedListHead;
    }

    /**
     * Handle a single insertion into the priority queue and return the new node ref
     */
    private ListNode processSingleElement(PriorityQueue<HeapNodeValue> firstItemsHeap, ListNode[] lists) {
        // get the smallest value from heap
        HeapNodeValue smallestValue = firstItemsHeap.poll();

        // update the list reference to point to next node
        lists[smallestValue.linkedListIndex] = lists[smallestValue.linkedListIndex].next;
        if (lists[smallestValue.linkedListIndex] != null) {
            firstItemsHeap
                    .add(new HeapNodeValue(lists[smallestValue.linkedListIndex].val, smallestValue.linkedListIndex));
        }

        return new ListNode(smallestValue.value);
    }
}
