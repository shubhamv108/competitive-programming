package code.shubham.binarysearch;

import java.util.stream.IntStream;

public class KokoEatingBananas {
    class Solution {
        public int minEatingSpeed(int[] A, int h) {
            int l = 0, r = IntStream.of(A).max().getAsInt(), m;
            while (l < r) {
                m = l + ((r - l) >> 2);

                int t = 0;
                for (int a: A)
                    t += Math.ceil(((double) a/m));


                if (t > h)
                    l = m + 1;
                else
                    r = m - 1;
            }

            return l;
        }
    }

    public static void main(String[] args) {
        System.out.println(new KokoEatingBananas().new Solution().minEatingSpeed(new int[] {10, 20, 30, 40}, 1));
    }
}
