package patterns.inplacereversaloflinkedlist;

import patterns.fastandslowpointer.ListNode;

public class ReverseEveryKElementSublist {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        System.out.println(reverseEveryKElement(head, 3));

    }
    private static ListNode reverseEveryKElement(ListNode head, int k) {
        if (k <= 1 || head == null) return head;

        ListNode current = head;
        ListNode previous = null;
        while (true) {

            ListNode lastNodeOfPreviousPart = previous;
            // after reversing the LList 'current' will become the last node of the sub-list
            ListNode lastNodeOfSubList = current;

            ListNode next;// will be used to temporarily store the next node
            int i = 0;
            // reverse 'k' nodes
            while (current != null && i < k) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
                i++;
            }

            // connect with the previous part
            if (lastNodeOfPreviousPart != null) {
                // 'previous' is now the first node of the sub-list
                lastNodeOfPreviousPart.next = previous;
            } else {// this means we are changing the first node (head) of the LinkedList
                head = previous;
            }
            // connect with the next part
            lastNodeOfSubList.next = current;
            if(current == null) break;// break, if we've reached the end of the LinkedList
            // prepare for the next sub-list
            previous = lastNodeOfSubList;
        }
        return head;
    }
}
