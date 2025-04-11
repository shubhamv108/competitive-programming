package code.shubham.binarysearch;

import java.util.Arrays;

public class ThreeSumWithLessThaEqualToTarget {

    class Solution {
        int solve(int[] A, int t) {
            int result = 0;
            Arrays.sort(A);
            for (int i = 0; i < A.length - 2; ++i) {
                if (A[i] >= t)
                    break;

                int rem = t - A[i];
                int l = i + 1, r = A.length - 1;
                while (l < r) {
                    int m = l + (r - l) / 2;
                    if (A[m] < rem)
                        l = m + 1;
                    else
                        r = m;
                }

                for (int j = r; j > i + 1; --j) {
                    int remm = rem - A[j];
                    if (remm <= A[i])
                        continue;

                    int ll = i + 1, rr = j - 1;
                    while (ll < rr) {
                        int m = ll + (rr - ll) / 2;
                        if (A[m] < remm)
                            ll = m + 1;
                        else
                            rr = m;
                    }
                    result += rr - i;
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSumWithLessThaEqualToTarget().new Solution().solve(new int[] {1, 2, 3, 4, 5}, 9));
    }

}
