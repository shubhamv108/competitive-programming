package code.shubham.arrays;

import java.util.stream.IntStream;

public class MinAmountOfTimeToCollectGarbage {
    class Solution {
        public int garbageCollection(String[] G, int[] T) {
            int result = 0;
            boolean bm, bp, bg;
            int m = 0, p = 0, g = 0;
            for (int i = 0; i < G.length; ++i) {
                bm = bp = bg = false;
                result += G[i].length();
                for (int j = 0; j < G[i].length(); ++j) {
                    if (!bm && G[i].charAt(j) == 'M') {
                        result += IntStream.range(m, i).map(k -> T[k]).sum();
                        m = i;
                        bm = true;
                    }
                    if (!bp && G[i].charAt(j) == 'P') {
                        result += IntStream.range(p, i).map(k -> T[k]).sum();
                        p = i;
                        bp = true;
                    }
                    if (!bg && G[i].charAt(j) == 'G') {
                        result += IntStream.range(g, i).map(k -> T[k]).sum();
                        g = i;
                        bg = true;
                    }
                    if (bp && bm && bg)
                        break;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MinAmountOfTimeToCollectGarbage()
                        .new Solution()
                            .garbageCollection(
                                    new String[] {"G","P","GP","GG"},
                                    new int[] {2, 4, 3})
        );
    }
}
