package code.shubham.dynamicprogramming.strings;

/**
 * Partition a string into contiguous chunks such that the total processing cost
 * is minimized.
 *
 * Cost model:
 * 1. Every split between two partitions costs partitionTime.
 * 2. For each partition, every pair of identical characters contributes
 *    sameTime to the processing cost.
 *
 * Example:
 * String: "ababcc"
 * Partition: "ab" | "abcc"
 *
 * Cost:
 *   Split cost      = 4
 *   Duplicate pairs = 1 ("cc")
 *   Total           = 5
 *
 * Time Complexity : O(n²)
 * Space Complexity: O(n)
 */
public class MinimumProcessingTimeToPartitionString {
    class Solution {
        public int solve(String A, int sameTime, int partitionTime) {
            return recurse(A, 0, A.length(), sameTime, partitionTime, new Integer[A.length() + 1]);
        }

        /**
         * Returns the minimum cost to process chars[start...end].
         */
        int recurse(String A, int start, int n, int sameTime, int partitionTime, Integer[] dp) {
            if (start == n)
                return 0;

            if (dp[start] != null)
                return dp[start];

            int[] f = new int[26];
            int pairs = 0;


            int min = Integer.MAX_VALUE;
            for (int end = start; end < n; ++end) {
                int c = A.charAt(end) - 'a';
                pairs += f[c];
                ++f[c];
                int t = pairs * sameTime;
                if (end < n - 1)
                    t += partitionTime + recurse(A, end + 1, n, sameTime, partitionTime, dp);
                min = Math.min(min, t);
            }
            return dp[start] = min;
        }
    }

    void main() {
        System.out.println(new Solution().solve("abcaa", 1, 3)); // 3
        System.out.println(new Solution().solve("abcabcc", 1, 4)); // 5
    }
}
