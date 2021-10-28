package code.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class RemoveZeroSumConsecutiveNodesFromLinkedList {

      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    class Solution {
        boolean flag = true;
        public ListNode removeZeroSumSublists(ListNode head) {
            ListNode result = head;
            while (flag) {
                flag = false;
                result = removeZeroSumSublists1(result);
            }
            // while (result != null) {
            // 	System.out.print(result.val + "->");
            // 	result = result.next;
            // }
            return result;
        }

        public ListNode removeZeroSumSublists1(ListNode head) {
            ListNode prev = new ListNode(-1);
            prev.next = head;
            ListNode result = prev;
            ListNode cur = head;
            HashMap<Integer, ListNode> m = new HashMap<>();
            m.put(0, prev);
            while (cur != null) {
                ListNode t = m.get(cur.val  -1);
                if (t != null) {
                    t.next = cur.next;
                    flag = true;
                    break;
                } else if (cur.val == 0) {
                    prev.next = cur.next;
                    cur = prev.next;
                    continue;
                }
                HashMap<Integer, ListNode> mNew = new HashMap<>();
                for (Map.Entry<Integer, ListNode> entry : m.entrySet()) {
                    mNew.put(entry.getKey() + cur.val, entry.getValue());
                }
                mNew.put(cur.val, prev);

                m = mNew;
                cur = cur.next;
                prev = prev.next;
            }
            return result.next;
        }
    }
    
}
