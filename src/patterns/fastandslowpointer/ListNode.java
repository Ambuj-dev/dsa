package patterns.fastandslowpointer;

public class ListNode {
    public int value;
    public ListNode next;
    public ListNode(int value){
        this.value = value;
        this.next = null;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
