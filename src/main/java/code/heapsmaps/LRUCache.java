package code.heapsmaps;

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
                q.remove(entry);
                q.offerFirst(entry);
            } else {
                entry = new CacheEntry(key, value);
                if (this.capacity == q.size()) {
                    CacheEntry removed = l.remove(l.size() - 1);
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
