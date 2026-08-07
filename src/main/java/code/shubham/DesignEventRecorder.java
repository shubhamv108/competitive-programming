package code.shubham;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DesignEventRecorder {
    public class Solution {
        private final int capacity;
        private final HashMap<String, NavigableMap<Integer, UserData>> userInfo = new HashMap<>();
        private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        private int size;
        private int oldTimestamp = Integer.MAX_VALUE;

        public Solution(int capacity) {
            this.capacity = capacity;
        }

        public void recordEvent(String userid, int timestamp, String eventType) {
            lock.writeLock().lock();
            try {
                if (size == capacity)
                    evict();

                if (oldTimestamp > timestamp)
                    oldTimestamp = timestamp;

                userInfo.computeIfAbsent(userid, e -> new TreeMap<>())
                        .computeIfAbsent(timestamp, e -> new UserData())
                        .addEvent(eventType, 1);
                ++size;
            } finally {
                lock.writeLock().unlock();
            }
        }

        public int getEventCount(String userId, int start, int end, String eventType) {
            lock.readLock().lock();
            try {
                return Optional.ofNullable(userInfo.get(userId))
                        .orElse(Collections.emptyNavigableMap())
                        .subMap(start, true, end, true)
                        .values()
                        .stream()
                        .map(p -> p.getCount(eventType))
                        .mapToInt(i -> i)
                        .sum();
            } finally {
                lock.readLock().unlock();
            }
        }

        public int getTopKEventTypesTotalFrequency(String userId, int start, int end, int k) {
            lock.readLock().lock();
            try {
                NavigableMap<Integer, UserData> timestamps = userInfo.get(userId);
                if (timestamps == null)
                    return 0;

                Map<String, Integer> frequency = new HashMap<>();
                timestamps.subMap(start, true, end, true)
                        .values()
                        .forEach(userData ->
                            userData.eventTypes.forEach(
                                    (eventType, count) -> frequency.merge(eventType, count, Integer::sum)));

                return frequency.values()
                        .stream()
                        .sorted(Collections.reverseOrder())
                        .limit(k)
                        .mapToInt(i -> i)
                        .sum();
            } finally {
                lock.readLock().unlock();
            }
        }

        private void evict() {
            int newOldTimestamp = Integer.MAX_VALUE;
            for (Map.Entry<String, NavigableMap<Integer, UserData>> entry : userInfo.entrySet()) {
                NavigableMap<Integer, UserData> timestamps = entry.getValue();
                UserData p = timestamps.remove(oldTimestamp);
                if (p != null) {
                    size -= p.totalCount;
                    timestamps.remove(oldTimestamp);
                    if (timestamps.isEmpty())
                        userInfo.remove(entry.getKey());
                    else
                        newOldTimestamp = Math.min(newOldTimestamp, timestamps.firstKey());
                }
            }
            oldTimestamp = newOldTimestamp;
        }

        private final class UserData {
            private final HashMap<String, Integer> eventTypes = new HashMap<>();
            private int totalCount;

            public void addEvent(String eventType, int count) {
                if (eventType == null)
                    totalCount += count;
                else {
                    eventTypes.merge(eventType, count, Integer::sum);
                    totalCount += count;
                }
            }

            public int getCount(String eventType) {
                if (eventType == null)
                    return totalCount;
                return eventTypes.getOrDefault(eventType, 0);
            }
        }
    }

    class Solution2 {
        private final int capacity;
        private int totalEvents;

        private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        private final Map<String, UserData> userInfo = new HashMap<>();
        // Global index for O(log n) "evict the oldest timestamp bucket".
        private final TreeMap<Integer, Map<String, Pair>> timeline = new TreeMap<>();

        Solution2(int n) {
            this.capacity = n;
        }

        public void recordEvent(String userId, int timestamp, String eventType) {
            lock.writeLock().lock();
            try {
                if (totalEvents == capacity) {
                    evacuate();
                }
                Pair pair = userInfo.computeIfAbsent(userId, u -> new UserData())
                        .recordAndGetPair(timestamp, eventType);
                timeline.computeIfAbsent(timestamp, t -> new HashMap<>()).put(userId, pair);
                ++totalEvents;
            } finally {
                lock.writeLock().unlock();
            }
        }

        public int getEventCount(String userId, int start, int end, String eventType) {
            lock.readLock().lock();
            try {
                UserData data = userInfo.get(userId);
                return data == null ? 0 : data.rangeCount(start, end, eventType);
            } finally {
                lock.readLock().unlock();
            }
        }

        public int getTopKEventTypesTotalFrequency(String userId, int start, int end, int k) {
            lock.readLock().lock();
            try {
                UserData data = userInfo.get(userId);
                return data == null ? 0 : data.topKFrequency(start, end, k);
            } finally {
                lock.readLock().unlock();
            }
        }

        // Evicts the single globally-oldest timestamp bucket across all users.
        // O(log n) to find it, O(m) to remove it where m = users sharing that
        // timestamp (previously this required an O(users) scan every time).
        private void evacuate() {
            Map.Entry<Integer, Map<String, Pair>> oldest = timeline.pollFirstEntry();
            if (oldest == null) return;

            for (Map.Entry<String, Pair> e : oldest.getValue().entrySet()) {
                String userId = e.getKey();
                Pair pair = e.getValue();
                totalEvents -= pair.totalCount;

                UserData data = userInfo.get(userId);
                if (data != null) {
                    data.removeTimestamp(oldest.getKey());
                    if (data.isEmpty()) {
                        userInfo.remove(userId);
                    }
                }
            }
        }
    }

    /** Per-user write path + lazy read-cache holder. */
    private final class UserData {
        private final NavigableMap<Integer, Pair> events = new TreeMap<>();
        // null == stale/uninitialized; rebuilt on first read after a write.
        private volatile SortedSnapshot cache;

        Pair recordAndGetPair(int timestamp, String eventType) {
            Pair pair = events.computeIfAbsent(timestamp, t -> new Pair());
            pair.addEvent(eventType, 1);
            cache = null; // invalidate; next read rebuilds lazily
            return pair;
        }

        void removeTimestamp(int timestamp) {
            if (events.remove(timestamp) != null) {
                cache = null;
            }
        }

        boolean isEmpty() {
            return events.isEmpty();
        }

        int rangeCount(int start, int end, String eventType) {
            return snapshot().rangeCount(start, end, eventType);
        }

        int topKFrequency(int start, int end, int k) {
            return snapshot().topK(start, end, k);
        }

        // Double-checked lazy rebuild. Safe: mutation of `events` only ever
        // happens under the outer write lock, which is exclusive of any
        // read lock holder currently inside this method.
        private SortedSnapshot snapshot() {
            SortedSnapshot snap = cache;
            if (snap == null) {
                synchronized (this) {
                    snap = cache;
                    if (snap == null) {
                        snap = SortedSnapshot.build(events);
                        cache = snap;
                    }
                }
            }
            return snap;
        }
    }

    private final class Pair {
        private final HashMap<String, Integer> eventTypes = new HashMap<>();
        private int totalCount;

        public void addEvent(String eventType, int count) {
            if (eventType != null)
                eventTypes.merge(eventType, count, Integer::sum);
            totalCount += count;
        }

        public int getCount(String eventType) {
            if (eventType == null) return totalCount;
            return eventTypes.getOrDefault(eventType, 0);
        }
    }

    /**
     * Immutable, binary-searchable view of one user's events at a point in
     * time. Rebuilt from scratch (O(n)) only when a write invalidates it;
     * every read against a warm cache is O(log n) for getEventCount and
     * O(log n + distinct types in range) for top-K.
     */
    private static final class SortedSnapshot {
        private final int[] timestamps;
        private final int[] totalPrefix;     // totalPrefix[i] = sum of counts over [0, i)
        private final Pair[] pairs;           // aligned with timestamps
        // Per-event-type prefix arrays, built on demand and memoized.
        private final Map<String, int[]> typePrefixCache = new ConcurrentHashMap<>();

        private SortedSnapshot(int[] timestamps, int[] totalPrefix, Pair[] pairs) {
            this.timestamps = timestamps;
            this.totalPrefix = totalPrefix;
            this.pairs = pairs;
        }

        static SortedSnapshot build(NavigableMap<Integer, Pair> events) {
            int n = events.size();
            int[] ts = new int[n];
            Pair[] pairs = new Pair[n];
            int[] totalPrefix = new int[n + 1];

            int i = 0;
            for (Map.Entry<Integer, Pair> e : events.entrySet()) {
                ts[i] = e.getKey();
                pairs[i] = e.getValue();
                totalPrefix[i + 1] = totalPrefix[i] + e.getValue().totalCount;
                i++;
            }
            return new SortedSnapshot(ts, totalPrefix, pairs);
        }

        int rangeCount(int start, int end, String eventType) {
            int lo = lowerBound(start);
            int hi = upperBound(end);
            if (lo >= hi) return 0;

            if (eventType == null) {
                return totalPrefix[hi] - totalPrefix[lo];
            }
            int[] typePrefix = typePrefixCache.computeIfAbsent(eventType, this::buildTypePrefix);
            return typePrefix[hi] - typePrefix[lo];
        }

        int topK(int start, int end, int k) {
            int lo = lowerBound(start);
            int hi = upperBound(end);
            if (lo >= hi || k <= 0)
                return 0;

            // Aggregating arbitrary event types over an arbitrary range can't
            // avoid touching every bucket in range in the general case, but
            // this is still a flat-array scan (better cache locality, no
            // red-black tree pointer chasing) vs. the original TreeMap walk.
            Map<String, Integer> freq = new HashMap<>();
            for (int i = lo; i < hi; i++) {
                pairs[i].eventTypes.forEach((type, count) -> freq.merge(type, count, Integer::sum));
            }
            return freq.values().stream()
                    .sorted(Collections.reverseOrder())
                    .limit(k)
                    .mapToInt(Integer::intValue)
                    .sum();
        }

        private int[] buildTypePrefix(String eventType) {
            int[] prefix = new int[timestamps.length + 1];
            for (int i = 0; i < timestamps.length; i++) {
                prefix[i + 1] = prefix[i] + pairs[i].getCount(eventType);
            }
            return prefix;
        }

        private int lowerBound(int key) {
            int lo = 0, hi = timestamps.length;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (timestamps[mid] < key) lo = mid + 1;
                else hi = mid;
            }
            return lo;
        }

        private int upperBound(int key) {
            int lo = 0, hi = timestamps.length;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (timestamps[mid] <= key)
                    lo = mid + 1;
                else
                    hi = mid;
            }
            return lo;
        }
    }


    void main() {
        var solution = new Solution(10);
        solution.recordEvent("U1", 100, "click");
        solution.recordEvent("U1", 200, "click");
        solution.recordEvent("U1", 150, "passage");
        solution.recordEvent("U3", 300, "passage");
        solution.recordEvent("U2", 120, "click");
        solution.recordEvent("U2", 180, "click");
        System.out.println(solution.getEventCount("U1", 100, 200, null));
        System.out.println(solution.getEventCount("U1", 100, 200, "click"));
        System.out.println(solution.getEventCount("U3", 100, 200, "click"));
    }
}


