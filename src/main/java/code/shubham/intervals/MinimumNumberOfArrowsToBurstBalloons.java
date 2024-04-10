package code.shubham.intervals;

import java.util.Arrays;

public class MinimumNumberOfArrowsToBurstBalloons {
    class Solution {
        public int findMinArrowShots(int[][] A) {
            if (A.length == 0)
                return 0;

            Arrays.sort(A, (a, b) -> a[1] - b[1]);
            int arrowPos = A[0][1];
            int result = 1;

            for (int i = 1; i < A.length; ++i) {
                if (arrowPos >= A[i][0] && arrowPos <= A[i][1])
                    continue;

                ++result;
                arrowPos = A[i][1];
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MinimumNumberOfArrowsToBurstBalloons()
                        .new Solution()
                        .findMinArrowShots(new int[][] {
                                {-2147483646,-2147483645},
                                {2147483646,2147483647}
                        })
                          );
    }
}
