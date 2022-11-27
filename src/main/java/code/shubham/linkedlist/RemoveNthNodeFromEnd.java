package code.shubham.linkedlist;

public class RemoveNthNodeFromEnd {

    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode slow = new ListNode(), fast = slow;
            slow.next = head;
            head = slow;

            while (n-- > 0)
                fast = fast.next;

            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }

            slow.next = slow.next.next;

            return head.next;
        }
    }
}
