package code.contestpractice.oa.microsoft;

import java.util.Arrays;

public class PartitionArrayWithNSubsetsWithEqualSum {

    class Solution {
        public boolean canPartitionKSubsets(int[] nums, int k) {
            int sum = 0;
            for (int n : nums) sum += n;
            if (k <= 0 || sum % k != 0) return false;
            int target = sum / k;
            Arrays.sort(nums);
            this.reverse(nums);
            return this.canPartition(nums, 0, 0, 0, target, new boolean[nums.length], k);
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

}
