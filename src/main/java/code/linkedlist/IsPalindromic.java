package code.linkedlist;

public class IsPalindromic {

    private boolean equals(ListNode A, ListNode B) {
        while (B != null) {
            if (A.val != B.val)
                return false;
            B = B.next;
            A = A.next;
        }
        return true;
    }

    public int lPalin(ListNode A) {
        // left = A;
        // if (isPalindrome(mid(A))) return 1;
        if (equals(A, reverse(mid(A)))) return 1;
        return 0;
    }

    private ListNode reverse (ListNode A) {
        ListNode r = null;
        ListNode temp;
        while (A != null) {
            temp = A.next;
            A.next = r;
            r = A;
            A = temp;
        }
        return r;
    }

    // private ListNode mid (ListNode A) {
    //     ListNode mid = A;
    //     int count = 0;
    //     while (A != null) {
    //         if (count != 0 && (count & 1) == 0)
    //             mid = mid.next;
    //         count++;
    //         A = A.next;
    //     }
    //     return mid.next;
    // }

    private ListNode mid (ListNode A) {
        ListNode slow = A;
        ListNode fast = A;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast == null) break;
            slow = slow.next;
        }
        return slow.next;
    }

    public static void main(String[] args) {
        ListNode l            = new ListNode(1);
        l.next                = new ListNode(2);
        l.next.next           = new ListNode(3);
        l.next.next.next      = new ListNode(2);
        l.next.next.next.next = new ListNode(1);
        System.out.println(new IsPalindromic().lPalin(l));
    }

}
