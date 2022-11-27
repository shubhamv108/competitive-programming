package code.shubham.design.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TimeMap2 {

    class Entry {
        int timestamp;
        String value;
        Entry(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    HashMap<String, ArrayList<Entry>> store = new HashMap<>();

    public TimeMap2() {

    }

    public void set(String key, String value, int timestamp) {
        ArrayList<Entry> timestamps = store.get(key);
        if (timestamps == null)
            store.put(key, timestamps = new ArrayList<>());
        timestamps.add(new Entry(timestamp, value));
    }

    public String get(String key, int timestamp) {
        ArrayList<Entry> timestamps = store.get(key);
        if (timestamps == null)
            return "";

        int l = 0, r = timestamps.size(), mid = 0;
        while (l < r) {
            mid = (l + r) / 2 ;
            if (timestamps.get(mid).timestamp <= timestamp)
                l = mid + 1;
            else
                r = mid;
        }

        if (r == 0)
            return "";

        return timestamps.get(r-1).value;
    }

    static class A {
        Integer A;
        A(int a) {
            A = a;
        }
    }

    public static void main(String[] args) {
        Set<A> set = new HashSet<>();
        set.add(new A(2));
        set.add(new A(2));
        System.out.println(set.size());
    }
}