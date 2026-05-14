package code.shubham.topK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * TopKEventTracker maintains the top K most frequent event names
 * from a continuously incoming stream of events.
 *
 * Ranking Rules:
 * 1. Higher frequency comes first.
 * 2. If frequencies are equal, lexicographically smaller event name comes first.
 */
class TopKEventsTracker {

    private final int k;
    private final Map<String, Integer> f = new HashMap<>();
    private final TreeSet<String> set = new TreeSet<>((x, y) -> {
        int xc = f.getOrDefault(x, 0);
        int yc = f.getOrDefault(y, 0);
        return xc == yc ? y.compareTo(x) : xc - yc;
    });

    public TopKEventsTracker(int k) {
        this.k = k;
    }

    /**
     * Records one occurrence of the given event name.
     * If the event does not exist, it is added with frequency 1.
     * If it already exists, its frequency is incremented.
     *
     * @param eventName the name of the event to record
     */
    public void record(String eventName) {
        boolean exists = set.contains(eventName);
        if (exists)
            set.remove(eventName);

        int c = f.getOrDefault(eventName, 0) + 1;
        f.put(eventName, c);
        if (exists) {
            set.add(eventName);
        } else if (set.size() < k)
            set.add(eventName);
        else if (set.size() == k) {
            String first = set.stream().findFirst().get();
            int firstC = f.get(first);
            if (firstC < c) {
                set.remove(first);
                set.add(eventName);
            } else if (firstC == c && eventName.compareTo(first) < 0) {
                set.remove(first);
                set.add(eventName);
            }
        }
    }

    /**
     * Returns a list of the current top K event names ranked by:
     * 1. Higher frequency first
     * 2. Lexicographically smaller name if frequencies are equal
     *
     * If fewer than K unique events exist, all events are returned.
     *
     * @return a list of event names representing the top K events
     */
    public List<String> topK() {
        ArrayList<String> topK = new ArrayList<>(set);
        Collections.reverse(topK);
        return topK;
    }

    public static void main(String[] args) {
        TopKEventsTracker topK = new TopKEventsTracker(2);
        topK.record("a");
        topK.record("b");
        System.out.println(topK.topK());
        topK.record("b");
        System.out.println(topK.topK());
    }
}

/**
 * Your TopKEventTracker object will be instantiated and called as such:
 *
 * TopKEventTracker obj = new TopKEventTracker(k);
 * obj.record(eventName);
 * List<String> param_2 = obj.topK();
 */
