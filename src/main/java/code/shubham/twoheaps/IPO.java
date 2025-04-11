package code.shubham.twoheaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class IPO {

    class Solution {

        public int findMaximizedCapital(int k, int w, int[] P, int[] C) {
            Integer[] sortByC = new Integer[P.length];
            for (int i = 0; i < C.length; ++i)
                sortByC[i] = i;

            Arrays.sort(sortByC, (x, y) -> C[x] - C[y]);

            PriorityQueue<Integer> q = new PriorityQueue<>((x, y) -> P[y] - P[x]);
            int i = 0;
            while (k-- > 0) {
                for (; i < C.length && C[sortByC[i]] <= w; ++i)
                    q.add(sortByC[i]);

                if (q.isEmpty())
                    break;

                w  += P[q.poll()];
            }

            return w;
        }
    }

}
