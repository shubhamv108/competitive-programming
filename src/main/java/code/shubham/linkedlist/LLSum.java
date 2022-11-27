package code.shubham.linkedlist;

public class LLSum {

    /**
     * Definition for singly-linked list.
     * class ListNode {
     *     public int val;
     *     public ListNode next;
     *     ListNode(int x) { val = x; next = null; }
     * }
     */

    private static int carry;

    public static ListNode addTwoNumbers(ListNode A, ListNode B) {
        int la = size(A);
        int lb = size(B);
        int diff = la - lb;
        ListNode AA = null;
        if (diff > 0) {
            AA = movehead(A, diff);
        }
        if (diff < 0) {
            ListNode temp = A;
            A = B;
            B = temp;
            AA = movehead(A, diff);
        }
        carry = sum(AA, B);
        propogateCarry(A, AA);
        if (carry > 0) {
            ListNode C = new ListNode(carry);
            C.next = A;
            A = C;
        }
        return A;
    }

    private static void propogateCarry(ListNode A, ListNode AA) {
        if (A == AA || carry == 0) return;
        propogateCarry(A.next, AA);
        int s = carry + A.val;
        carry = s > 9 ? 1 : 0;
        A.val = s % 10;
    }

    private static int sum (ListNode A, ListNode B) {
        if (A == null) return 0;
        int s = sum(A.next, B.next) + A.val + B.val;
        int carry = s / 10;
        A.val = s % 10;
        return carry;
    }

    private static ListNode movehead (ListNode A, int diff) {
        diff = Math.abs(diff);
        while (diff-- > 0) A = A.next;
        return A;
    }

    private static int size (ListNode a) {
//        int count = 0;
//        while (a != null) {
//            count++;
//            a = a.next;
//        }
//        return count;
        if (a == null) return 0;
        int size = 1;
        while (a != null && a.next != null && a.next.next != null && a.next.next.next!= null) {
            a = a.next.next.next;
            size = size + 3;
        }
        if (a != null)
            if (a.next != null) {
                size++;
                if (a.next.next != null) size++;
            }
        return size;
    }


    public static ListNode addTwoNumbers2 (ListNode A, ListNode B) {
        int la = size(A);
        int lb = size(B);
        if (la < lb) {
            ListNode temp = A;
            A = B;
            B = temp;
        }
        ListNode head = A;
        int c = 0;
        int s;
        while (B != null) {
            s = c + A.val + B.val;
            c = s / 10;
            A.val = s % 10;
            A = A.next;
            B = B.next;
        }
        if (c > 0) {
            while (A != null && c > 0) {
                s = A.val + c;
                c = s / 10;
                A.val = s % 10;
                if (A.next == null) break;
                A = A.next;
            }
            if (c > 0) A.next = new ListNode(c);
        }
        return head;
    }

    public static ListNode addTwoNumbers3(ListNode A, ListNode B) {
        ListNode C = null;
        ListNode head = null;
        int c = 0;
        int s;
        while (A != null && B != null) {
            s = c + A.val + B.val;
            c = s / 10;
            ListNode temp = new ListNode(s % 10);
            if (C == null) { C      = temp; head = C;   }
            else           { C.next = temp; C = C.next; }
            A = A.next;
            B = B.next;
        }
        C.next = A != null ? A : B;
        while (C.next != null && c > 0) {
            s = C.next.val + c;
            c = s / 10;
            C.next.val = s % 10;
            C = C.next;
        }
        if (c > 0) C.next = new ListNode(c);
        return head;
    }

    public static void main(String[] args) {
        ListNode A = new ListNode(9);
        A.next = new ListNode(9);
        A.next.next = new ListNode(9);
        ListNode B = new ListNode(1);
        LinkedListFunctions.print(addTwoNumbers(B, A));
    }

}
