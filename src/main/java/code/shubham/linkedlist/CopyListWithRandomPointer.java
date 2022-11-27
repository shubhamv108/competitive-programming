package code.shubham.linkedlist;

import java.util.Optional;

public class CopyListWithRandomPointer {

    private static class Node {
        int val;
        Node next;
        Node random;
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        @Override
        public String toString() {
            return "" + val;
        }

        public void print() {
            String s = "Node{" +
                    "val=" + val +
                    ", next=" + Optional.ofNullable(next).map(e -> e.val).orElse(null) +
                    ", random=" + Optional.ofNullable(random).map(e ->e.val).orElse(null) +
                    '}';
            System.out.println(s);
            if (next != null) {
                next.print();
            }
        }
    }

    class Solution {
        public Node copyRandomList(Node head) {
            if (head == null)
                return null;

            Node h = head, next;
            while (h != null) {
                next = h.next;
                h.next = new Node(h.val);
                h.next.next = next;
                h = next;
            }

            h = head;
            while (h != null) {
                if (h.random != null)
                    h.next.random = h.random.next;
                h = h.next.next;
            }

            h = head;
            Node copyHead = head.next, copy = copyHead;
            while (h != null) {
                h.next = h.next.next;
                if (h.next != null)
                    copy.next = copy.next.next;
                h = h.next;
                copy = copy.next;
            }

            return copyHead;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node2.random = node;
        node3.random = node5;
        node4.random = node3;
        node5.random = node;
        Node copy = new CopyListWithRandomPointer().new Solution().copyRandomList(node);
        copy.print();
    }

}
