package code.shubham.arrays;

import java.util.*;

public class RevealCardsInIncreasingOrder {
    class Solution {
        public int[] deckRevealedIncreasing(int[] A) {
            int n = A.length;
            int[] result = new int[n];

            Arrays.sort(A);

            Queue<Integer> q = new LinkedList<>();

            for (int i = n - 1; i > -1; --i) {
                if (q.size() > 0)
                    q.offer(q.poll());
                q.offer(A[i]);
            }

            for (int i = n - 1; i > -1; --i)
                result[i] = q.poll();
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new RevealCardsInIncreasingOrder().new Solution().deckRevealedIncreasing(new int[] {17,13,11,2,3,5,7})));
    }
}
