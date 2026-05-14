package code.shubham.arrays;

public class FindTheSmallestBalancedIndex {

    class Solution {
        public int smallestBalancedIndex(int[] A) {
            int n = A.length, result = -1;

            long[] sum = new long[n];
            for(int i = 1; i < n; ++i)
                sum[i] = sum[i - 1] + A[i - 1];

            long p = 1;
            for (int i = n - 1; i >= 0; --i) {
                if (sum[i] == p)
                    result = i;

                if (i > 0) {
                    if (p > 1e14 / A[i])
                        p = (long) 1e18;
                    else
                        p *= A[i];
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new FindTheSmallestBalancedIndex().new Solution().smallestBalancedIndex(new int[] {999,818,984,995,841,822,984,978,960,997,896,926,759,961,1000,562,1,1,1,87,4,1,40}));
    }
}
