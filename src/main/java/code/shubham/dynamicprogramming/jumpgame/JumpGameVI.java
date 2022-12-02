package code.shubham.dynamicprogramming.jumpgame;

import java.util.Deque;
import java.util.LinkedList;

public class JumpGameVI {
    class Solution {
        public int maxResult(int[] A, int k) {
            int n = A.length;
            Deque<Integer> dq = new LinkedList<>();
            dq.offer(0);
            for (int i = 1; i < n; ++i) {
                A[i] = A[dq.peekFirst()] + A[i];

                while (!dq.isEmpty() && A[dq.peekLast()] <= A[i])
                    dq.pollLast();

                if (!dq.isEmpty() && i - dq.peekFirst() >= k)
                    dq.pollFirst();

                dq.offerLast(i);
            }
            return A[n - 1];
        }
    }
}
