package code.shubham.linkedlist;

public class ReorderList {
    class Solution {
        ListNode newCur, head;
        public void reorderList(ListNode head) {
            newCur = this.head = head;
            f(head);
        }

        ListNode f(ListNode node) {
            if (node.next == null)
                return node;

            ListNode n = f(node.next);
            if (n == null || newCur == n || newCur.next == n)
                return null;

            ListNode temp = newCur.next;
            newCur.next = n;
            n.next = temp;
            newCur =  temp;
            node.next = null;
            return node;
        }
    }

    class Solution2 {
        public void reorderList(ListNode head) {
            ListNode middle = middle(head);
            ListNode head2 = middle.next;
            middle.next = null;

            mergeAlternate(head, reverse(head2));
        }

        void mergeAlternate(ListNode A, ListNode B) {
            ListNode tempA, tempB;
            while (B != null) {
                tempA = A.next;
                tempB = B.next;

                A.next = B;
                B.next = tempA;

                A = tempA;
                B = tempB;
            }
        }

        ListNode reverse(ListNode A) {
            ListNode prev = null, next = A;
            while (A != null) {
                next = A.next;
                A.next = prev;
                prev = A;
                A = next;
            }
            return prev;
        }

        ListNode middle(ListNode head) {
            ListNode slow = head, fast = head.next;
            while(fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }

    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);
        list.next.next.next = new ListNode(4);
        list.next.next.next.next = new ListNode(5);
        new ReorderList().new Solution2().reorderList(list);
        list.printList();
    }
}
