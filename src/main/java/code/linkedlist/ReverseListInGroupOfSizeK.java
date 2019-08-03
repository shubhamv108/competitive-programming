package code.linkedlist;

public class ReverseListInGroupOfSizeK {

    public class IterativeSolution {
        public ListNode reverse (ListNode A, int B) {
            ListNode resultHead = null;
            ListNode resultTail = null;
            ListNode curHead = null;
            ListNode curTail = A;
            ListNode temp;
            if (B <= 1) return A;
            int k = B;
            while (A != null) {
                k--;
                temp = A.next;
                A.next = curHead;
                curHead = A;
                A = temp;
                if (A== null || k == 0) {
                    if (resultHead == null)
                        resultHead = curHead;
                    curTail.next = A;
                    k = B;
                    if (resultTail != null)
                        resultTail.next = curHead;
                    resultTail = curTail;
                    if (A == null) break;
                    curTail = A;
                }
            }
            return resultHead;
        }
    }

    public class RecursiveSolution {

        public ListNode reverseK (ListNode head, int K) {
            if (head == null || head.next == null || K < 2) return head;
            ListNode current = head;
            ListNode prev = null;
            ListNode tmp;

            int count = K;
            while (count-- > 0 && current != null) {
                tmp = current.next;
                current.next = prev;
                prev = current;
                current = tmp;
            }

            head.next = reverseK(current, K);

            return prev;
        }

    }

    public static ListNode createAndGetNewLN (int... n) {
        if (n.length == 0) return null;
        ListNode h = new ListNode(n[0]);
        ListNode l = h;
        for (int i = 1; i < n.length; i++) {
            l.next = new ListNode(n[i]);
            l = l.next;
        }
        return h;
    }

    public static void print (ListNode h) {
        while (null != h) { System.out.print(h.val + " "); h = h.next; }
    }

    public static void main(String[] args) {
        ListNode head = createAndGetNewLN(6, 10, 0, 3, 4, 8, 9, 8);
        int K = 1;
        head = new ReverseListInGroupOfSizeK().new RecursiveSolution().reverseK(head, K);
        print(head);
    }

}
