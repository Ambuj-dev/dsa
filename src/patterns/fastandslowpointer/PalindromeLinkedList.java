package patterns.fastandslowpointer;

/*
* Given the head of a Singly LinkedList, write a method to check if the LinkedList is a palindrome or not.

Your algorithm should use constant space and the input LinkedList should be in the original form once the algorithm is finished. The algorithm should have O(N) time complexity where N is the number of nodes in the LinkedList.

Example 1:
Input: 2 -> 4 -> 6 -> 4 -> 2 -> null
Output: true
Example 2:
Input: 2 -> 4 -> 6 -> 4 -> 2 -> 2 -> null
Output: false
As we know, a palindrome LinkedList will have nodes values that read the same backward or forward. This means that if we divide the LinkedList into two halves, the node values of the first half in the forward direction should be similar to the node values of the second half in the backward direction. As we have been given a Singly LinkedList, we can’t move in the backward direction. To handle this, we will perform the following steps:

1.We can use the Fast & Slow pointers method similar to Middle of the LinkedList to find the middle node of the LinkedList.
2.Once we have the middle of the LinkedList, we will reverse the second half.
3.Then, we will compare the first half with the reversed second half to see if the LinkedList represents a palindrome.
4.Finally, we will reverse the second half of the LinkedList again to revert and bring the LinkedList back to its original form.
* */
public class PalindromeLinkedList {

    public boolean isPalindromicLinkedList(ListNode head){
        if( head == null || head.next == null){
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;

        while( fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode headSecondHalf = reverse(slow);
        ListNode copyHeadSecondHalf = headSecondHalf;
        while(head != null && headSecondHalf != null){
            if(head.value != headSecondHalf.value){
                break;
            }
            head = head.next;
            headSecondHalf = headSecondHalf.next;
        }
        reverse(copyHeadSecondHalf);

        if(head == null || headSecondHalf == null){
            return true;
        }

        return false;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        PalindromeLinkedList palindromeLinkedList = new PalindromeLinkedList();
        ListNode head = new ListNode(20);
        head.next = new ListNode(4);
        head.next.next = new ListNode(15);
        head.next.next.next = new ListNode(10);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(15);
        head.next.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next.next = new ListNode(20);
        System.out.println(palindromeLinkedList.isPalindromicLinkedList(head));
        System.out.println(head);
        head.next.next.next.next.next.next.next.next = new ListNode(30);
        System.out.println(palindromeLinkedList.isPalindromicLinkedList(head));

    }
}
