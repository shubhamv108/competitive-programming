package code.shubham.priorityqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyTracker {
    private final Map<String, Integer> frequencyMap = new ConcurrentHashMap<>();
    private final ConcurrentSkipListMap<Date, CopyOnWriteArrayList<String>> timestampMap = new ConcurrentSkipListMap<>();

    public void record(final String word) {
        Date now = new Date();
        frequencyMap.merge(word, 1, Integer::sum);
        timestampMap.computeIfAbsent(now, __ -> new CopyOnWriteArrayList<>()).add(word);
    }

    public List<String> topK(final int k, final Date start, final Date end) {
        Map<String, Long> filteredFrequency = timestampMap.subMap(start, true, end, true)
                    .entrySet()
                    .stream()
                    .map(Entry::getValue)
                    .flatMap(List::stream)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        PriorityQueue<Map.Entry<String, Long>> minHeap = new PriorityQueue<>(Comparator.comparingLong(Entry::getValue));

        for (Map.Entry<String, Long> entry : filteredFrequency.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }

        Collections.reverse(result);
        return result;
    }

    // Main method for testing
    public static void main(String[] args) throws InterruptedException {
        WordFrequencyTracker tracker = new WordFrequencyTracker();

        // Multithreaded recording
        Runnable recordTask = () -> {
            int k  =10000;
            while (k-- > 0) {
                tracker.record("apple");
                tracker.record("banana");
                tracker.record("apple");
            }
        };

        Thread t1 = new Thread(recordTask);
        Thread t2 = new Thread(recordTask);

        t1.start();
        t2.start();
//        t1.join();
//        t2.join();

        // Simulate time delay
        Thread.sleep(1000);
        Date now = new Date();

//        // Another thread recording more words
//        tracker.record("cherry");
//        tracker.record("apple");

        // Query Top-K words in the last 2 seconds
        Date start = new Date(now.getTime() - 200000);
        Date end = new Date();
        List<String> topKWords = tracker.topK(2, start, end);
        System.out.println("Top K Words: " + topKWords);
    }
}
