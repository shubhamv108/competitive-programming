package code.caching;

import java.util.HashMap;

public class LFUCacheTLE {

    class Node {
        int key;
        int value;
        int frequency = 1;
        Node previous, next;

        Node(int frequency, Node previous, Node next) {
            this.frequency = frequency;
            this.previous = previous;
            this.next = next;
        }

        Node(int key, int value, Node previous, Node next) {
            this.key = key;
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        Node remove() {
            previous.next = next;
            next.previous = previous;
            previous = null;
            next = null;
            return this;
        }
    }

    int capacity;

    HashMap<Integer, Node> map;
    Node head = new Node(Integer.MAX_VALUE, null, null);
    Node tail = new Node(Integer.MIN_VALUE, head, null);

    public LFUCacheTLE(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        head.next = tail;
    }

    public int get(int key) {
        Node n = map.get(key);
        if (n == null) return -1;
        int freq = ++n.frequency;
        Node t = n.previous;
        while (t.frequency <= freq) {
            t = t.previous;
        }

        if (t != n.previous) {
            n.remove();
            n.previous = t;
            n.next = t.next;
            t.next.previous = n;
            t.next = n;
        }
        return n.value;
    }

    public void put(int key, int value) {
        if (capacity < 1) return;
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            get(key);
            return;
        }

        if (map.size() == capacity) {
            evict();
        }

        Node newNode = new Node(key, value, tail.previous, tail);
        tail.previous.next = newNode;
        tail.previous = newNode;
        map.put(key, newNode);
        get(key);
    }

    private Node evict() {
        Node removed = tail.previous.remove();
        return map.remove(removed.key);
    }
}