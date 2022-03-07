package problems.p1209;

import java.lang.reflect.Array;
import java.util.Stack;

public class Solution {

    /**
     * Use a stack to keep track of previous character counts
     */
    public String removeDuplicates(String s, int k) {
        CharLinkedNode charNode = strToList(s);

        // use a stack to keep track of consecutive counts
        Stack<Integer> kCount = new Stack<>();

        boolean isProcessing = true;
        while (isProcessing && charNode != null) {
            boolean advance = true;
            // if the previous char is different (or at the first char), add to the stack
            if (charNode.prev == null || charNode.value != charNode.prev.value) {
                // this is the first time a different char was found
                kCount.push(1);
            } else {
                // if here, the char before is the same
                // check the current count including the current char
                int prevCount = kCount.pop() + 1;
                if (prevCount == k) {
                    // delete k chars up to and including the current one
                    for (int i = 0; i < k; i++) {
                        CharLinkedNode[] charNodes = removeNode(charNode);
                        if (charNodes[0] == null) {
                            advance = false;
                            charNode = charNodes[1];
                            break;
                        } else {
                            charNode = charNodes[0];
                        }
                    }
                } else {
                    kCount.push(prevCount);
                }
            }
            if (advance) {
                if (charNode.next == null) {
                    isProcessing = false;
                } else {
                    charNode = charNode.next;
                }
            }
        }

        return listToStr(charNode);
    }

    /**
     * Create a doubly linked list representation of a string
     */
    private CharLinkedNode strToList(String s) {
        CharLinkedNode headNode = new CharLinkedNode(s.charAt(0));

        CharLinkedNode lastNode = headNode;
        for (int i = 1; i < s.length(); i++) {
            CharLinkedNode newNode = new CharLinkedNode(s.charAt(i));

            // link in both directions
            newNode.prev = lastNode;
            lastNode.next = newNode;

            // update working ref
            lastNode = newNode;
        }

        return headNode;
    }

    /**
     * Delete a node and return the previous, or next ref, if prev is null.
     * Return [prev, next]
     */
    private CharLinkedNode[] removeNode(CharLinkedNode node) {
        CharLinkedNode prev = node.prev;
        CharLinkedNode next = node.next;

        if (next != null) {
            next.prev = prev;
        }
        if (prev != null) {
            prev.next = next;
        }
        CharLinkedNode[] nodes = (CharLinkedNode[]) Array.newInstance(CharLinkedNode.class, 2);
        nodes[0] = prev;
        nodes[1] = next;

        return nodes;
    }

    /***
     * Create a string from the doubly linked list representation
     */
    private String listToStr(CharLinkedNode charNode) {
        // if list is empty, return empty str
        if (charNode == null) {
            return "";
        }

        // rewind to first char
        while (charNode.prev != null) {
            charNode = charNode.prev;
        }

        StringBuilder sb = new StringBuilder();
        while (charNode != null) {
            sb.append(charNode.value);
            charNode = charNode.next;
        }

        return sb.toString();
    }
}
