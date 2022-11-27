package code.shubham.caching;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache {
    int capacity, min = -1;
    HashMap<Integer, Integer> entries = new HashMap<>();
    HashMap<Integer, Integer> keyFrequency = new HashMap<>();
    HashMap<Integer, LinkedHashSet<Integer>> frequencyKeys = new HashMap<>();
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.frequencyKeys.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        Integer value = entries.get(key);
        if (value == null)
            return -1;

        int curFreq = keyFrequency.get(key);
        LinkedHashSet<Integer> list = frequencyKeys.get(curFreq);
        list.remove(key);
        if (curFreq == min && list.size() == 0)
            min++;

        keyFrequency.put(key, curFreq+1);
        list = frequencyKeys.get(curFreq+1);
        if (list == null)
            frequencyKeys.put(curFreq+1, list = new LinkedHashSet<>());
        list.add(key);
        return value;
    }

    public void put(int key, int value) {
        if (capacity < 1)
            return;

        if (entries.containsKey(key)) {
            entries.put(key, value);
            get(key);
            return;
        }

        if (entries.size() == capacity) {
            LinkedHashSet<Integer> list = this.frequencyKeys.get(min);
            int toEvict = list.iterator().next();
            list.remove(toEvict);
            entries.remove(toEvict);
        }
        entries.put(key, value);
        keyFrequency.put(key, 1);
        min = 1;
        frequencyKeys.get(1).add(key);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        lfu.get(1);      // return 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        lfu.get(2);      // return -1 (not found)
        lfu.get(3);      // return 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        lfu.get(1);      // return -1 (not found)
        lfu.get(3);      // return 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        lfu.get(4);      // return 4
        // cache=[4,3], cnt(4)=2, cnt(3)=3
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
