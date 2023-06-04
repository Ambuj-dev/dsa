package patterns.inplacereversaloflinkedlist;

import org.w3c.dom.Node;
import patterns.fastandslowpointer.ListNode;

public class ReverseASubList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(reverseSubList(head, 2, 4));

    }
    private static ListNode reverseSubList(ListNode head, int p, int q){
        if(p == q) return head;

        ListNode current = head;
        ListNode previous = null;
        int i = 0;

        // after skipping 'p-1' nodes, current will point to 'p'th node
        while(current != null && i< p-1){
            previous = current;
            current = current.next;
            i++;
        }
        //we are interested in three parts of the LL,
        //1. the part before index p
        //2. the part between p and q
        //3. and the part after index q

        ListNode lastNodeOfFirstPart = previous; // points to the node at index 'p-1'
        // after reversing the LinkedList 'current' will become the last node of the sub-list
        ListNode lastNodeOfSubList = current;

        ListNode next; // will be used to temporarily store the next node
        i = 0;
        // reverse nodes between 'p' and 'q'
        while(current != null && i < q-p+1){
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            i++;
        }

        // connect with the first part
        if(lastNodeOfFirstPart != null){
            // 'previous' is now the first node of the sub-list
            lastNodeOfFirstPart.next = previous;
        }else{
            // this means p == 1 i.e., we are changing the first node (head) of the LinkedList
            head = previous;
        }
        // connect with the last part
        lastNodeOfSubList.next = current;
        return head;
    }
}
