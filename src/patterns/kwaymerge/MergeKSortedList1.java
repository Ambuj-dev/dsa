package patterns.kwaymerge;

import patterns.fastandslowpointer.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeKSortedList1 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);

        System.out.println(mergeKLists(new ListNode[]{node1, node2, node3}));
        System.out.println(mergeKListsOptimised(new ListNode[]{node1, node2, node3}));
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        int min_index = 0;
        ListNode head = new ListNode(0);
        ListNode h = head;
        while (true) {
            boolean isBreak = true;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    if (lists[i].value < min) {
                        min_index = i;
                        min = lists[i].value;
                    }
                    isBreak = false;
                }

            }
            if (isBreak) {
                break;
            }
            ListNode a = new ListNode(lists[min_index].value);
            h.next = a;
            h = h.next;
            lists[min_index] = lists[min_index].next;
        }
        h.next = null;
        return head.next;
    }

    public static ListNode mergeKListsOptimised(ListNode[] lists) {
        int min_index = 0;
        ListNode head = new ListNode(0);
        ListNode h = head;

        while (true) {
            boolean isBreak = true;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    if (lists[i].value < min) {
                        min_index = i;
                        min = lists[i].value;
                    }
                    isBreak = false;
                }

            }
            if (isBreak) {
                break;
            }
            h.next = lists[min_index];
            h = h.next;
            lists[min_index] = lists[min_index].next;
        }
        h.next = null;
        return head.next;
    }



}
