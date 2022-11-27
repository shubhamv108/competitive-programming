package code.shubham.heapsmaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LRUCache {

    private class Solution {

        private class CacheEntry {
            private int k;
            private int v;
            private CacheEntry (int k, int v) {
                this.k = k;
                this.v = v;
            }
            private int value(int v) {
                int t = this.v;
                this.v = v;
                return t;
            }
            private int value() {
                return this.v;
            }
        }
        private Deque<CacheEntry> q;
        private LinkedList<CacheEntry> l;
        private Map<Integer, CacheEntry> m;
        private int capacity;

        public Solution (int capacity) {
            this.capacity = capacity;
            this.q = new LinkedList<>();
            l = (LinkedList) q;
            this.m = new HashMap<>();
        }

        public int get (int key) {
            if (!m.containsKey(key)) return -1;
            CacheEntry e = m.get(key);
            q.remove(e);
            q.offerFirst(e);
            return e.value();
        }

        public void set (int key, int value) {
            CacheEntry entry = null;
            if (m.containsKey(key)) {
                entry = m.get(key);
                entry.value(value);

                /** ToDo: Convert remove operation to O(1) */
                q.remove(entry);

                q.offerFirst(entry);
            } else {
                entry = new CacheEntry(key, value);
                if (this.capacity == q.size()) {
                    CacheEntry removed = q.pollLast();
                    m.remove(removed.k);
                }
                q.offerFirst(entry);
                m.put(key, entry);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int capacity = Integer.valueOf(line[1]);
        LRUCache.Solution cache = new LRUCache().new Solution(capacity);
        int i = 2;
        while (i < line.length - 1) {
            char operation = line[i++].charAt(0);
            if (operation == 'S') {
                int k = Integer.valueOf(line[i++]);
                int v = Integer.valueOf(line[i++]);
                cache.set(k, v);
            }
            if (operation == 'G') {
                int k = Integer.valueOf(line[i++]);
                System.out.println(cache.get(k));
            }
        }
    }

}

class LRUCache1 {
    private LinkedHashMap<Integer, Integer> map;
    private final int CAPACITY;
    public LRUCache1(int capacity) {
        CAPACITY = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > CAPACITY;
            }
        };
    }

    public int get(int key) { return map.getOrDefault(key, -1); }

    public void set(int key, int value) { map.put(key, value); }
}

class TestLRUCacheWithLinkedHashMap {

    public static void main(String[] args)
    {
        System.out.println("Going to test the LRU " + " Cache Implementation");
        LRUCache1 cache = new LRUCache1(2);
        cache.set(1, 10);
        cache.set(2, 20);
        System.out.println("Value for the key: 1 is " + cache.get(1));
        cache.set(3, 30);
        System.out.println("Value for the key: 2 is " + cache.get(2));
        cache.set(4, 40);
        System.out.println("Value for the key: 1 is " + cache.get(1));
        System.out.println("Value for the key: 3 is " + cache.get(3));
        System.out.println("Value for the key: 4 is " + cache.get(4));

    }
}

class LRU<K, V> {

    Node head = new Node();
    Node tail = new Node();
    Map<K, Node> nodeMap;
    int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>(capacity);
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        V result = null;
        Node node = nodeMap.get(key);
        if (node != null) {
            remove(node);
            add(node);
            result = node.value;
        }
        return result;
    }

    public void put(K key, V value) {
        Node node = nodeMap.get(key);
        if (node != null) {
            node.value = value;
            remove(node);
            add(node);
        } else {
            if (nodeMap.size() == capacity) {
                nodeMap.remove(tail.prev.key);
                remove(tail.prev);
            }
            Node newNode = new Node(key, value);
            add(newNode);
            nodeMap.put(key, newNode);
        }
    }

    private void add(Node node) {
        Node headNext = head.next;
        head.next = node;
        node.prev = head;
        node.next = headNext;
        headNext.prev = node;
    }

    private void remove(Node node) {
        Node nextNode = node.next;
        Node prevNode = node.prev;
        nextNode.prev = prevNode;
        prevNode.next = nextNode;
    }

    class Node {
        K key;
        V value;
        Node prev;
        Node next;
        Node() {}
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + key + "," + value + "]";
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node temp = head;
        while (temp != null) {
            builder.append(temp.toString()).append("->");
            temp = temp.next;
        }
        builder.append("\n").append(nodeMap);
        return builder.toString();
    }

    public static void main(String[] args) {
        LRU<Integer, Integer> cache = new LRU<>(0);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache);
    }

}
