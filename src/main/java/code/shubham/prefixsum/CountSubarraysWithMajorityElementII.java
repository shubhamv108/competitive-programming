package code.shubham.prefixsum;

public class CountSubarraysWithMajorityElementII {
    class Solution {
        public long countMajoritySubarrays(int[] A, int T) {
            int n = A.length;
            int[] pre = new int[n * 2 + 1];
            pre[n] = 1;
            int c = n;
            long result = 0, preSum = 0;
            for (int i = 0; i < n; ++i) {
                if (A[i] == T) {
                    preSum += pre[c];
                    ++c;
                    ++pre[c];
                } else {
                    --c;
                    preSum -= pre[c];
                    ++pre[c];
                }
                result += preSum;
            }
            return result;
        }
    }

    void main() {
        System.out.println(new Solution().countMajoritySubarrays(new int[] { 2, 1, 2, 3, 4, 2, 2 }, 2));
    }
}
