package code.datastructures;

import java.util.Random;

public class Skiplist {

    class Node {
        int value;
        Node next, down;
        Node(Integer value, Node next, Node down) {
            this.value = value;
            this.next = next;
            this.down = down;
        }
    }

    Node head = new Node(-1, null, null);

    public Skiplist() {

    }

    public boolean search(int target) {
        Node current = head;
        while (current != null) {
            while (current.next != null &&  current.next.value < target) {
                current = current.next;
            }
            if (current.next != null && current.next.value == target) return true;
            current = current.down;
        }
        return false;
    }

    public void add(int num) {
        Stack<Node> stack = new Stack();
        Node current = head;
        while (current != null) {
            while (current.next != null && current.next.value < num) {
                current = current.next;
            }
            stack.push(current);
            current = current.down;
        }

        boolean insert = true;
        Node down = null;
        while (insert && !stack.isEmpty()) {
            current = stack.pop();
            down = current.next = new Node(num, current.next, down);
            insert = isHead();
        }
        if (insert) head = new Node(-1, null, head);
    }

    boolean isHead() {
        return (System.nanoTime() & 1) == 1;
    }

    public boolean erase(int num) {
        boolean isFound = false;
        Node current = head;
        while (current != null) {
            while (current.next != null && current.next.value < num) {
                current = current.next;
            }
            if (current.next != null && current.next.value == num) {
                if (!isFound) isFound = true;
                current.next = current.next.next;
            }
            current = current.down;
        }
        return isFound;
    }

}

