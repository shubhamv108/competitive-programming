package code.shubham.intervals;

import java.util.Arrays;

public class MaximumTeamSizeWithOverlappingIntervals {
    class Solution {
        public int maximumTeamSize(int[] S, int[] E) {
            int n = S.length;
            Integer[] A = new Integer[n];
            for (int i = 0; i < n; ++i)
                A[i] = i;

            Arrays.sort(A, (x, y) -> S[x] == S[y] ? E[x] - E[y] : S[x] - S[y]);

            int result = 0, c = 1, end = E[A[0]];
            for (int i = 1; i < n; ++i) {
                int prev = A[i-1], cur = A[i];

                if (end < S[cur]) {
                    result = Math.max(result, c);
                    c = 1;
                    end = E[cur];
                } else {
                    ++c;
                    end = Math.max(end, E[cur]);
                }
            }

            result = Math.max(result, c);

            return result;
        }
    }

    void main() {
        System.out.println(
            new Solution().maximumTeamSize(
                new int[] { 48, 44, 30, 13, 5, 22 },
                new int[] { 51, 48, 47, 21, 37, 24 }));
    }
}
