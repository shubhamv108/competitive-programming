package hashing;

import java.util.HashMap;

public class CopyList {

    public class Solution {

        public RandomListNode copyRandomList (RandomListNode head) {
            if (head == null) return null;
            RandomListNode temp;
            RandomListNode n = head;
            while (n != null) {
                temp        = n.next;
                n.next      = new RandomListNode(n.label);
                n.next.next = temp;
                n           = n.next.next;
            }
            n = head;
            while (n != null) {
                n.next.random = n.random == null ? null : n.random.next;
                n = n.next.next;
            }
            n = head.next;
            head = n;
            while (n.next != null) {
                n.next = n.next.next;
                n = n.next;
            }
            return head;
        }

    }

    public class Solution2 {

        private HashMap<RandomListNode, RandomListNode> m = new HashMap<>();

        public RandomListNode copyRandomList (RandomListNode head) {
            if (head == null) return null;
            RandomListNode resultHead = copy(head);
            RandomListNode copy = resultHead;
            while (head != null) {
                copy.next   = copy(head.next);
                copy.random = copy(head.random);
                copy = copy.next;
                head = head.next;
            }
            return resultHead;
        }

        private RandomListNode copy (RandomListNode original) {
            if (original == null) return null;
            if (!m.containsKey(original)) {
                RandomListNode copy = new RandomListNode(original.label);
                m.put(original, copy);
            }
            return m.get(original);
        }
    }

    class Solution2Recursive {

        private HashMap<RandomListNode, RandomListNode> m = new HashMap<>();

        public RandomListNode copyRandomList (RandomListNode head) {
            if (head == null) return null;
            RandomListNode resultHead = null;
            RandomListNode resultTail = new RandomListNode(head.label);
            while (head != null) {
                resultTail = deepCopy(head);
                if (resultHead == null) resultHead = resultTail;
                head = head.next;
            }
            return resultHead;
        }

        private RandomListNode deepCopy (RandomListNode original) {
            if (original == null) return null;
            if (!m.containsKey(original)) {
                RandomListNode copy = new RandomListNode(original.label);
                copy.next   = deepCopy(original.next);
                copy.random = deepCopy(original.random);
                m.put(original, copy);
            }
            return m.get(original);
        }
    }

    public class Solution3 {

        public RandomListNode copyRandomList (RandomListNode head) {
            RandomListNode copyHead = new RandomListNode(head.label);
            HashMap<RandomListNode, RandomListNode> m = new HashMap<>();
            m.put(head, copyHead);
            RandomListNode original = head.next;
            RandomListNode copy     = copyHead;
            while (original != null) {
                copy.next = new RandomListNode (original.label);
                copy = copy.next;
                m.put(original, copy);
                original = original.next;
            }
            original = head;
            copy     = copyHead;
            while (original != null) {
                copy.random = m.get(original.random);
                copy = copy.next;
                original = original.next;
            }
            return copyHead;
        }

    }

    public static void main(String[] args) {
        RandomListNode l1 = new RandomListNode(1);
        RandomListNode l2 = new RandomListNode(2);
        RandomListNode l3 = new RandomListNode(3);
        RandomListNode l4 = new RandomListNode(4);
        l1.next   = l2;
        l1.random = l3;
        l2.next   = l3;
        l2.random = l1;
        l3.next   = l4;
        l3.random = l1;
        new CopyList().
                new Solution().
                    copyRandomList(l1).print();
    }

}
