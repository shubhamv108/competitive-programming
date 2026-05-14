package code.shubham.random;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class WeightedRandomPicker {

    class Solution {
        private String[] items;
        private double[] prefixSum;
        private Random random;

        public Solution(String[] items, double[] weights) {
            this.items = items;
            this.prefixSum = new double[weights.length];
            this.random = new Random();

            // Build prefix sum
            prefixSum[0] = weights[0];
            for (int i = 1; i < weights.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + weights[i];
            }

            // Normalize (optional, in case weights don't sum to 1)
            double total = prefixSum[prefixSum.length - 1];
            for (int i = 0; i < prefixSum.length; i++) {
                prefixSum[i] /= total;
            }
        }

        public String pick() {
            double r = random.nextDouble(); // 0 to 1

            int index = binarySearch(prefixSum, r);
            return items[index];
        }

        private int binarySearch(double[] A, double t) {
            int l = 0, r = A.length - 1, result = 0;

            while (l <= r) {
                int mid = l + ((r - l) >> 1);

                if (t > A[mid]) {
                    l = mid + 1;
                } else {
                    result = r;
                    r = mid - 1;
                }
            }
            return result;
        }
    }

    public class AliasMethod {
        private String[] items;
        private double[] prob;
        private int[] alias;
        private int n;
        private Random random;

        public AliasMethod(String[] items, double[] weights) {
            this.items = items;
            this.n = items.length;
            this.prob = new double[n];
            this.alias = new int[n];
            this.random = new Random();

            double[] scaled = new double[n];
            Queue<Integer> small = new LinkedList<>();
            Queue<Integer> large = new LinkedList<>();

            // Step 1: Scale weights
            double sum = 0;
            for (double w : weights)
                sum += w;
            for (int i = 0; i < n; ++i) {
                scaled[i] = (weights[i] * n) / sum;
                if (scaled[i] < 1.0)
                    small.add(i);
                else
                    large.add(i);
            }

            // Step 2: Build tables
            while (!small.isEmpty() && !large.isEmpty()) {
                int s = small.poll();
                int l = large.poll();

                prob[s] = scaled[s];
                alias[s] = l;

                scaled[l] = scaled[l] - (1.0 - scaled[s]);

                if (scaled[l] < 1.0)
                    small.add(l);
                else
                    large.add(l);
            }

            // Step 3: Remaining
            while (!large.isEmpty())
                prob[large.poll()] = 1.0;

            while (!small.isEmpty())
                prob[small.poll()] = 1.0;
        }

        public String pick() {
            int i = random.nextInt(n);
            double r = random.nextDouble();

            if (r < prob[i])
                return items[i];
            else
                return items[alias[i]];
        }
    }

    // Test
    public static void main(String[] args) {
        String[] A = {"hello", "world"};
        double[] B = {0.7, 0.3};

        var solution = new WeightedRandomPicker().new AliasMethod(A, B);

        Map<String, Integer> count = new HashMap<>();
        count.put("hello", 0);
        count.put("world", 0);

        for (int i = 0; i < 100000; i++) {
            String result = solution.pick();
            count.put(result, count.get(result) + 1);
        }

        System.out.println(count);
    }
}