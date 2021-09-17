package code.priorityqueue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {

    class Solution {
        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> count = new LinkedHashMap<>();
            for (String word : words)
                count.put(word, count.getOrDefault(word, 0) + 1);
            PriorityQueue<Map.Entry<String, Integer>> q = new PriorityQueue<>(
                    (a, b) -> a.getValue() == b.getValue()
                            ? b.getKey().compareTo(a.getKey())
                            : a.getValue() - b.getValue());
            for (Map.Entry<String, Integer> entry : count.entrySet()) {
                if (q.size() < k || q.peek().getValue() <= entry.getValue()) {
                    q.offer(entry);
                    if (q.size() > k)
                        q.poll();
                }
            }
            List<String> result = new LinkedList<>();
            while (!q.isEmpty())
                result.add(0, q.poll().getKey());
            return result;
        }
    }

    class Solution2 {
        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> count = new LinkedHashMap<>();
            for (String word : words)
                count.put(word, count.getOrDefault(word, 0) + 1);
            PriorityQueue<Map.Entry<String, Integer>> q = new PriorityQueue<>(
                    (a, b) -> a.getValue() == b.getValue()
                            ? a.getKey().compareTo(b.getKey())
                            : b.getValue() - a.getValue());
            for (Map.Entry<String, Integer> entry : count.entrySet())
                q.offer(entry);
            List<String> result = new ArrayList<>();
            while (!q.isEmpty() && k-- > 0)
                result.add(q.poll().getKey());
            return result;
        }
    }

}
