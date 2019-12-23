package code.datastructures;

public class DoublyLinkedList {

    Node head = null;
    Node tail = null;

    void add(int n) {
        addLast(n);
    }

    void remove(int n) {
        removeFirst();
    }


    void addFirst(int n) {
        Node temp = new Node(n);
        temp.next = head;
        if (head != null) head.prev = temp;
        head = temp;
        if (tail == null) tail = head;
    }

    void addLast(int n) {
        Node temp = new Node(n);
        if (tail == null) {
            tail = head = temp;
        } else {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
    }

    void removeFirst() {
        if (head == null) return;
        if (head.next == null) {
            head = tail = null;
        } else {
            head.next.prev = null;
            head = head.next;
        }
    }

    void removeLast() {
        if (tail == null) return;
        if (tail.prev == null) {
            tail = head = null;
        } else {
            tail.prev.next = null;
            tail = tail.prev;
        }
    }

    void print() {
        while (head != null) {
            System.out.printf("%d ", head.val);
            head = head.next;
        };
    }

    class Node {
        int val;
        Node next;
        Node prev;
        Node (int val) {
            this.val = val;
        }
    }


    public static void main(String[] args) {
        DoublyLinkedList q = new DoublyLinkedList();
        q.addFirst(1);
        q.addLast(1);
        q.removeFirst();
        q.removeLast();
        q.addLast(1);
        q.print();
    }

}
