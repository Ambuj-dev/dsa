package patterns.fastandslowpointer;

public class FindCycle {

    public boolean findCycle(ListNode node){
        ListNode slow = node;
        ListNode fast = node;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                return true;
            }
        }
        return false;
    }
    public int findCycleLength(ListNode node){
        ListNode slow = node;
        ListNode fast = node;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                return calculateCycleLength(slow);
            }
        }
        return 0;
    }
    public int calculateCycleLength(ListNode node){
        int cycleLength = 1;
        ListNode curNode = node.next;

        while(curNode != node){
            cycleLength++;
            curNode = curNode.next;
        }

        return cycleLength;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        int cycleLength = 0;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                cycleLength =  calculateCycleLength(slow);
                break;
            }
        }
        if(cycleLength == 0) return null;
        return findStart(head, cycleLength);
    }

    private ListNode findStart(ListNode head, int cycleLength) {
        ListNode pointer1 = head;
        ListNode pointer2 = head;

        while(cycleLength > 0){
            pointer2 = pointer2.next;
            cycleLength--;
        }

        while(pointer1 != pointer2){
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        return pointer1;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(20);
        head.next = new ListNode(4);
        head.next.next = new ListNode(15);
        head.next.next.next = new ListNode(10);
       // head.next.next.next.next = head.next.next;

        FindCycle cycle = new FindCycle();
        System.out.println(cycle.findCycle(head));
        System.out.println(cycle.findCycleLength(head));
       // System.out.println(cycle.detectCycle(head).value);

        head.next.next.next.next = head.next;
        System.out.println(cycle.findCycle(head));
        System.out.println(cycle.findCycleLength(head));
        System.out.println(cycle.detectCycle(head).value);
    }
}
