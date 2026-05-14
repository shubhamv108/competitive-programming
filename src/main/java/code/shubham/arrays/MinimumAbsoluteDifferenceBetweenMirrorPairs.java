package code.shubham.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MinimumAbsoluteDifferenceBetweenMirrorPairs {
    class Solution {
        public int minMirrorPairDistance(int[] A) {
            int n = A.length, result = Integer.MAX_VALUE;
            HashMap<Integer, List<Integer>> p = new HashMap<>();
            for (int i = 0; i < n; ++i)
                p.computeIfAbsent(A[i], e -> new ArrayList<>()).add(i);

            for (int i = 0; i < n; ++i) {
                int rev = reverse(A[i]);
                if (rev == A[i])
                    continue;
                result = Math.min(result, findNearestDist(p.get(rev), i));
            }

            return result == Integer.MAX_VALUE ? -1 : result;
        }

        static int findNearestDist(List<Integer> A, int t) {
            if (A == null)
                return Integer.MAX_VALUE;
            int l = 0, r = A.size() - 1, result = Math.abs(t - A.get(0));

            if (l == r)
                return result;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (m > t) {
                    r =  m - 1;
                    result = Math.min(result, Math.abs(t - A.get(m)));
                } else {
                    l = m + 1;
                }
            }
            return result;
        }

        static int reverse(int A) {
            int r = 0;
            while (A > 0) {
                r *= 10;
                r += A%10;
                A /= 10;
            }
            return r;
        }
    }

    void main() {
        var solution = new MinimumAbsoluteDifferenceBetweenMirrorPairs().new Solution();
        System.out.println(solution.minMirrorPairDistance(
                new int[] { 12,21,45,33,54 }));
    }
}
