package code.dp;

import java.util.Arrays;

public class ClimbStairs {
    class Solution2 {
        int sum = 0;
        public int climbStairs(int A) {
            climb(A);
            return sum;
        }

        private void climb (int A) {
            if (A < 0) return;
            if (A == 0) sum++;
            climb(A-1);
            climb(A-2);
        }
    }

    class Solution1 {
        public int climbStairs (int a) {
            if (a < 1) return 0;
            if (a < 3) return a;
            int[] cache = new int[a];
            cache[0] = 1;
            cache[1] = 2;
            for(int i = 2 ; i < a ; i++) cache[i] = cache[i - 1] + cache[i - 2];
            Arrays.stream(cache).forEach(e -> System.out.print(e + " "));
            return cache[a-1];
        }
    }

    class Solution {
        public int climbStairs(int A) {
            if (A < 0) return 0;
            if (A < 3) return A;
            int a = 1, b = 2;
            for (int i = 2; i < A; i++) {
                b = b + a;
                a = b - a;
            }
            return b;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ClimbStairs().new Solution().climbStairs(4));
    }
}
