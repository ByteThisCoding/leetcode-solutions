package problems.p1209;

/**
 * We'll represent the working string with a doubly linked list
 */
public class CharLinkedNode {

    public char value;
    public CharLinkedNode prev;
    public CharLinkedNode next;

    CharLinkedNode(char value) {
        this.value = value;
    }
}
