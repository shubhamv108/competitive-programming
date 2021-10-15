package code.linkedlist;

public class MergeLinkedListInDescendingOrder {

    private static class Node {
        int data;
        Node next;

        Node (int d) {
            data = d;
            next = null;
        }
    }

    Node mergeResult(Node node1, Node node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        if (node1.data > node2.data) {
            Node temp = node1;
            node1 = node2;
            node2 = temp;
        }
        Node next = null;
        while (node1 != null && node2 != null) {
            Node temp = null;
            if (node1.data < node2.data) {
                temp = node1.next;
                node1.next = next;
                next = node1;
                node1 = temp;
            } else {
                temp = node2.next;
                node2.next = next;
                next = node2;
                node2 = temp;
            }
        }
        while (node1 != null) {
            Node temp = node1.next;
            node1.next = next;
            next = node1;
            node1 = temp;
        }
        while (node2 != null) {
            Node temp = node2.next;
            node2.next = next;
            next = node2;
            node2 = temp;
        }
        return next;
    }

}
