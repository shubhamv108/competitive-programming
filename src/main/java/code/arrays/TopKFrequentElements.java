package code.arrays;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopKFrequentElements {
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

    public static void main(String[] args) {
        int arr[] = { 3, 1, 4, 4, 5, 2, 6, 1 };
        int n = arr.length;
        int k = 2;
        System.out.println(Arrays.stream(new TopKFrequentElements().topKFrequent(arr, k)).boxed().collect(Collectors.toList()));
    }
}
