package code.shubham.design.datastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class TimeMap {

    HashMap<String, TreeMap<Integer, String>> store = new HashMap<>();

    public TimeMap() {

    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> timestamps = store.get(key);
        if (timestamps == null)
            store.put(key, timestamps = new TreeMap<>());
        timestamps.put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> timestamps = store.get(key);
        if (timestamps == null)
            return "";
        Map.Entry<Integer, String> floor = timestamps.floorEntry(timestamp);
        if (floor == null)
            return "";
        return floor.getValue();
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */