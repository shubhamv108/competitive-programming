package code.shubham.contestpractice.oa.microsoft;

import java.util.Arrays;

public class PartitionArrayWithNSubsetsWithEqualSum {

    class Solution {
        public boolean canPartitionKSubsets(int[] N, int k) {
            int sum = 0;
            for (int n : N)
                sum += n;
            if (k <= 0 || sum % k != 0)
                return false;
            int target = sum / k;
            Arrays.sort(N);
            this.reverse(N);
            return this.canPartition(N, 0, 0, 0, target, new boolean[N.length], k);
        }

        boolean canPartition(int[] A, int idx, int sum, int digitCount, int target, boolean[] visited, int kSubsets) {
            if (kSubsets == 1)
                return true;
            if (sum == target && digitCount > 0)
                return this.canPartition(A, 0, 0, 0, target, visited, kSubsets-1);
            for (int i = idx; i < A.length; i++) {
                if (!visited[i] && sum + A[i] <= target) {
                    visited[i] = true;
                    if (this.canPartition(A, idx+1, sum + A[i], digitCount+1, target, visited, kSubsets))
                        return true;
                    visited[i] = false;
                }
            }
            return false;
        }

        void reverse(int[] A) {
            int l = 0, r = A.length - 1;
            while (l < r) {
                int t  = A[l];
                A[l++] = A[r];
                A[r--] = t;
            }
        }
    }

    class Solution1 {
        public boolean canPartitionKSubsets(int[] A, int k) {
            int n = A.length;
            int sum = 0;
            for (int i: A)
                sum += i;
            if (sum % k != 0)
                return false;
            Arrays.sort(A);
            int target = sum / k;
            return dfs(n-1, A, new int[k], k, target);
        }

        public boolean dfs(int index, int[] A, int[] sum, int k, int target) {
            if (index == -1)
                return true;

            for (int i = 0; i < k; i++) {
                if((sum[i] + A[index] > target) || (i > 0 && sum[i] == sum[i-1]))
                    continue;

                sum[i] += A[index];

                if (dfs(index-1, A, sum, k, target))
                    return true;

                sum[i] -= A[index];
            }
            return false;
        }
    }

}
