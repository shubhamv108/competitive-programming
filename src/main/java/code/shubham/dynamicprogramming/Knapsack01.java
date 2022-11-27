package code.shubham.dynamicprogramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Knapsack01 {

    class Solution {
        int[] A;
        int W;
        Integer[][] dp;
        Solution(int[] weights, int W) {
            this.A = weights;
            this.W = W;
            this.dp = new Integer[weights.length][W+1];
        }

        int solve() {
            return recurse(0, 0);
        }

        int recurse(int startPos, int w) {
            if (w > W) return Integer.MIN_VALUE;
            if (startPos == A.length) return w;
            if (dp[startPos][w] != null) return dp[startPos][w];
            int result = w;
            for (int i = startPos; i < A.length; i++) {
                int r = recurse(i + 1, w + A[i]);
                result = Math.max(r, result);
            }
            return dp[startPos][w] = result;
        }
    }

    class Solution2 {
        int getBellCount(int[] weights, int maxWeight) {
            int l  = weights.length, result = 0;
            HashMap<Integer, Integer> prev = new HashMap();
            HashMap<Integer, Integer> cur = new HashMap<>();
            for (int i = 0; i < l; i++) {
                if (weights[i] > maxWeight) continue;
                cur.put(weights[i], 1);
                for (Map.Entry<Integer, Integer> entry : prev.entrySet()) {
                    if (entry.getKey() + weights[i] <= maxWeight) {
                        cur.put(entry.getKey() + weights[i], entry.getValue() + 1);
                        result = Math.max(entry.getValue() + 1, result);
                    }
                }
                prev.putAll(cur);
            }
            return result;
        }
    }

    public class Solution3 {
        /**
         * @param weights: An array of n integers, where the value of each element weights[i] is the weight of each plate i
         * @param maxCapacity: An integer, the capacity of the barbell
         * @return: an integer denoting the maximum capacity of items that she can lift.
         */
        public int weightCapacity(int[] weights, int maxCapacity) {
            int l = weights.length, result = 0;
            Set<Integer> s = new HashSet<>();
            Set<Integer> t = new HashSet<>();
            for (int i = 0; i < l; i++) {
                if (weights[i] > maxCapacity) continue;
                t.add(weights[i]);
                result = Math.max(result, weights[i]);
                for (int n : s) {
                    int sum = weights[i] + n;
                    if (sum < maxCapacity) {
                        t.add(sum);
                        result = Math.max(result, sum);
                    }
                }
                s.addAll(t);
            }
            return result;
        }

        public int weightCapacity(List<Integer> weights, int maxCapacity) {
            int l = weights.size(), result = 0;
            Set<Integer> s = new HashSet<>();
            Set<Integer> t = new HashSet<>();
            for (int i = 0; i < l; i++) {
                Integer weight = weights.get(i);
                if (weight > maxCapacity) continue;
                t.add(weight);
                result = Math.max(result, weight);
                for (int n : s) {
                    int sum = weight + n;
                    if (sum < maxCapacity) {
                        t.add(sum);
                        result = Math.max(result, sum);
                    }
                }
                s.addAll(t);
            }
            return result;
        }
    }

    public class Solution4 {
        /**
         * @param weights: An array of n integers, where the value of each element weights[i] is the weight of each plate i
         * @param maxCapacity: An integer, the capacity of the barbell
         * @return: an integer denoting the maximum capacity of items that she can lift.
         */
        public int weightCapacity(int[] weights, int maxCapacity) {
            boolean[] dp = new boolean[maxCapacity + 1];
            dp[0] = true;
            int result = 0;

            for (int i = 0; i < weights.length; i++) {
                int weight = weights[i];
                for (int j = maxCapacity; j >= weight; j--) {
                    if (dp[j - weight]) {
                        dp[j] = true;
                        result = Math.max(result, j);
                    }
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new Knapsack01().new Solution2().getBellCount(new int[] {2, 1, 30, 45, 40, 25, 1}, 50)
        );
    }

}
