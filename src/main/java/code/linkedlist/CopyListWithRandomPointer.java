package code.linkedlist;

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
            if (head == null) return null;
            Node n = head;
            while (n != null) {
                Node t = n.next;
                n.next = new Node(n.val);
                n.next.next = t;
                n = t;
            }

            n = head;
            while (n != null) {
                if (n.random != null)
                    n.next.random = n.random.next;
                n = n.next.next;
            }

            n = head;
            Node copyHead = head.next;
            while (n != null) {
                Node t = n.next;
                n.next = t.next;
                if (t.next != null)
                    t.next = n.next.next;
                n = n.next;
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
