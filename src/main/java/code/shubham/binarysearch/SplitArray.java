package code.shubham.binarysearch;

public class SplitArray {
    class Solution {
        public int splitArray(int[] A, int k) {
            int sum = 0, max = 0;
            for (int a : A) {
                sum += a;
                max = a;
            }

            int l = max, r = sum + k - 1 / k, result = 0;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (can(A, m, k)) {
                    result = m;
                    r = m - 1;
                } else
                    l = m + 1;
            }

            return result;
        }

        boolean can(int[] A, int m, int k) {
            int sum = 0;
            int c = 0;

            for (int a : A) {
                if (sum + a <= m)
                    sum += a;
                else {
                    sum = a;
                    ++c;
                }
            }

            return c + 1 <= k;
        }
    }

    public static void main(String[] args) {
        System.out.println(new SplitArray().new Solution().splitArray(new int[] { 7, 2, 5, 10, 8 }, 2));
    }
}
