package patterns.fastandslowpointer;

import org.w3c.dom.NodeList;

public class RearrangeLinkedlist {
    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);
        //rearrange(head);
        //System.out.println(head);
        head.next.next.next.next.next.next = new ListNode(15);
        rearrange(head);
        System.out.println(head);
    }
    private static void rearrange(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode headSecondHalf = reverse(slow);
        ListNode headFirstHalf = head;
        while(headFirstHalf != null && headSecondHalf != null){
            ListNode temp = headFirstHalf.next;
            headFirstHalf.next = headSecondHalf;
            headFirstHalf = temp;

            temp = headSecondHalf.next;
            headSecondHalf.next = headFirstHalf;
            headSecondHalf = temp;
        }
        if(headFirstHalf != null){
            headFirstHalf.next = null;
        }
    }
    private static ListNode reverse(ListNode head){
        ListNode prev = null;
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    private static void printNode(NodeList head){

    }
}
