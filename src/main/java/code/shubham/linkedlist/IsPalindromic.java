package code.shubham.linkedlist;

import java.util.Arrays;
import java.util.TreeMap;

public class IsPalindromic {



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



    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public boolean isPalindrome(ListNode head) {
            ListNode mid = mid(head);
            return equal(head, reverse(mid.next));
        }

        ListNode mid(ListNode slow) {
            ListNode fast = slow.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        boolean equal(ListNode A, ListNode B) {
            while (A != null && B != null) {
                if (A.val != B.val)
                    return false;
                A = A.next;
                B = B.next;
            }
            return true;
        }

        ListNode reverse(ListNode node) {
            ListNode prev = null, next;
            while (node != null) {
                next = node.next;
                node.next = prev;
                prev = node;
                node = next;
            }
            return prev;
        }

    }

    class Solution2 {
        ListNode cur;
        boolean isPal = false;
        public boolean isPalindrome(ListNode head) {
            this.cur = head;
            return this.isPalin(head);
        }

        public boolean isPalin(ListNode head) {
            if (head == null)
                return true;
            if (!isPalin(head.next))
                return false;
            if (isPal)
                return true;
            if (cur == head)
                return isPal = true;
            if (cur.val != head.val)
                return false;
            cur = cur.next;
            return true;
        }
    }

    public static void main(String[] args) {
        ListNode l            = new ListNode(1);
        l.next                = new ListNode(2);
        l.next.next           = new ListNode(3);
        l.next.next.next      = new ListNode(2);
        l.next.next.next.next = new ListNode(1);
//        System.out.println(new IsPalindromic().lPalin(l));
    }

}
