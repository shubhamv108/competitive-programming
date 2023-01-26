package code.shubham.linkedlist;

public class RemoveDuplicatesFromSortedListII {
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode resultHead = new ListNode(0), prev = resultHead, cur = head;
            while (cur != null) {
                if (cur.next != null && cur.val == cur.next.val) {
                    ListNode temp = cur;
                    while (cur != null && temp.val == cur.val)
                        cur = cur.next;
                    continue;
                }

                prev.next = cur;
                prev = cur;
                cur = cur.next;
            }

            prev.next = cur;
            return resultHead.next;
        }
    }
}
