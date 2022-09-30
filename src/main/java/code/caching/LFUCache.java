package code.caching;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    class Node {
        int key, val;
        long freq;
        Node prev, next;
        Node(long freq) {
            this.freq = freq;
        }
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        Node clear() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
            this.prev = null;
            this.next = null;
            return this;
        }

        Node insertNext(Node node) {
            node.prev = this;
            node.next = this.next;
            this.next.prev = node;
            this.next = node;
            return this;
        }
    }

    Map<Integer, Node> m;
    Node head = new Node(Long.MAX_VALUE), tail = new Node(Long.MIN_VALUE);
    int capacity;

    public LFUCache(int capacity) {
        this.m = new HashMap<>(capacity);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = m.get(key);
        if (node == null)
            return -1;

        node.freq++;
        Node prev = node.prev;
        while (prev.freq <= node.freq)
            prev = prev.prev;

        if (prev != node.prev)
            prev.insertNext(node.clear());

        return node.val;
    }

    public void put(int key, int value) {
        if (this.capacity < 1)
            return;

        Node node = m.get(key);
        if (node != null) {
            get(key);
            node.val = value;
            return;
        }

        evict();

        node = new Node(key, value);
        tail.prev.insertNext(node);
        m.put(key, node);
        get(key);
    }

    void evict() {
        if (this.m.size() == 0 || this.m.size() < this.capacity)
            return;
        Node removed = tail.prev.clear();
        this.m.remove(removed.key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */