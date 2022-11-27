package code.shubham.sort.bucket;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopKFrequentElements {

    // nlogn
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> freq = Arrays.stream(nums).boxed().collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
            PriorityQueue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>((x, y) -> y.getValue() - x.getValue());
            for (Map.Entry<Integer, Integer> e : freq.entrySet()) q.offer(e);
            int[] result = new int[k];
            int r = 0;
            while (!q.isEmpty() && r != k) {
                result[r++] = q.poll().getKey();
            }
            return result;
        }
    }

    // nlogk
    class Solution2 {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> freq = Arrays.stream(nums).boxed().collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
            PriorityQueue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>(k, (x, y) -> x.getValue() - y.getValue());
            for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
                if (q.size() == k) {
                    if (q.peek().getValue() < e.getValue()) {
                        q.poll();
                        q.offer(e);
                    }
                } else {
                    q.offer(e);
                }
            }

            int[] result = new int[k];
            int r = 0;
            while (!q.isEmpty())
                result[r++] = q.poll().getKey();

            return result;
        }
    }

    class Solution3 {
        public int[] topKFrequent(int[] nums, int k) {
            HashMap<Integer, Integer> freq = new HashMap<>();
            for (int num : nums)
                freq.put(num, freq.getOrDefault(num, 0) + 1);

            TreeMap<Integer, HashSet<Integer>> buckets = new TreeMap<>();
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                HashSet<Integer> n = buckets.get(entry.getValue());
                if (n == null)
                    buckets.put(entry.getValue(), n = new HashSet<>());
                n.add(entry.getKey());
            }

            int result[] = new int[k], r = 0;
            for (Map.Entry<Integer, HashSet<Integer>> entry : buckets.descendingMap().entrySet()) {
                for (Integer n : entry.getValue()) {
                    if (r == k)
                        break;
                    result[r++] = n;
                }
            }
            return result;
        }
    }

    // n
    class Solution4 {
        public int[] topKFrequent(int[] nums, int k) {
            HashMap<Integer, Integer> freq = new HashMap<>();
            for (int num : nums)
                freq.put(num, freq.getOrDefault(num, 0) + 1);

            int maxFreq = 0;
            List<Integer>[] bucket = new List[nums.length + 1];
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                if (bucket[entry.getValue()] == null)
                    bucket[entry.getValue()] = new LinkedList<>();
                bucket[entry.getValue()].add(entry.getKey());

                maxFreq = Math.max(maxFreq, entry.getValue());
            }

            int result[] = new int[k], r = 0;
            for (int i = maxFreq; i > 0; i--) {
                if (bucket[i] != null) {
                    for (int n : bucket[i]) {
                        result[r++] = n;
                        if (r == k)
                            return result;
                    }
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        int[] result = new TopKFrequentElements().new Solution2().topKFrequent(new int[] {1,1,1,2,2,3}, 2);
        Arrays.stream(result).forEach(System.out::println);
    }
}
