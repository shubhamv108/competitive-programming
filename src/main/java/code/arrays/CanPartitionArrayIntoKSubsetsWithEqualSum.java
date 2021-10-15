package code.arrays;

public class CanPartitionArrayIntoKSubsetsWithEqualSum {

    class Solution {
        boolean solve(int[] A, int kSubsets) {
            int sum = 0;
            for (int n : A) sum += n;
            if (kSubsets <= 0 || sum % kSubsets != 0) return false;
            int target = sum / kSubsets;
            return this.canPartition(A, 0, 0, 0, target, new boolean[A.length], kSubsets);
        }

        boolean canPartition(int[] A, int idx, int sum, int digitCount, int target, boolean[] visited, int kSubsets) {
            if (kSubsets == 1)
                return true;
            if (sum > target)
                return false;
            if (sum == target && digitCount > 0)
                return this.canPartition(A, 0, 0, 0, target, visited, kSubsets-1);
            for (int i = idx; i < A.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    if (this.canPartition(A, idx+1, sum + A[i], digitCount+1, target, visited, kSubsets))
                        return true;
                    visited[i] = false;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        CanPartitionArrayIntoKSubsetsWithEqualSum object = new CanPartitionArrayIntoKSubsetsWithEqualSum();
        Solution solution = object.new Solution();
        System.out.println(
                solution.solve(new int[] { 4, 3, 2, 3, 5, 2, 1 }, 4)
        );
    }

}
