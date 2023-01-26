package code.shubham.linkedlist;

public class OddEvenLinkedList {
    class Solution {
        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null)
                return head;

            ListNode oddHead = head, evenHead = head.next, even = evenHead;
            while (even != null && even.next != null) {
                head.next = even.next;
                head = head.next;
                even.next = head.next;
                even = head.next;
            }
            head.next = evenHead;
            return oddHead;
        }
    }
}
