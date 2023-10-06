package code.shubham.arrays;

import java.util.Arrays;
import java.util.HashMap;

public class MinimumOperationsToReduceXToZero {
    class Solution {
        int result = Integer.MAX_VALUE;
        public int minOperations(int[] A, int x) {
            int target = Arrays.stream(A).sum() - x;
            if (target == 0)
                return A.length;

            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            int sum = 0;
            int res = Integer.MIN_VALUE;

            for (int i = 0; i < A.length; ++i) {

                sum += A[i];
                Integer idx = map.get(sum - target);
                if (idx != null)
                    res = Math.max(res, i - idx);

                map.put(sum, i);
            }

            return res == Integer.MIN_VALUE ? -1 : A.length - res;
        }
    }

    class Solution2 {
        public int minOperations(int[] A, int x) {
            int sum = Arrays.stream(A).sum() - x;
            if (sum == 0)
                return A.length;
            if (sum < 0)
                return -1;

            int l = 0, r = 0, cur = 0, result = -1;
            for (; r < A.length; r++) {
                cur += A[r];

                while (l <= r && cur > sum)
                    cur -= A[l++];

                if (cur == sum)
                    result = Math.max(result, r - l + 1);
            }

            return result == -1 ? -1 : A.length - result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinimumOperationsToReduceXToZero().new Solution().minOperations(
                new int[] {1,1,2,3,4},
                5
        ));
    }
}
