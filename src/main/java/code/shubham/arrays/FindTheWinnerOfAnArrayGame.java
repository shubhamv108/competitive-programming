package code.shubham.arrays;

import java.util.Arrays;
import java.util.LinkedList;

public class FindTheWinnerOfAnArrayGame {
    class Solution {
        public int getWinner(int[] A, int k) {
            int[] win = new int[A.length];
            int a = 0, b = 0;
            int max = Arrays.stream(A).max().getAsInt();
            boolean allOnceChecked = false;
            LinkedList<Integer> l = new LinkedList<>();
            while (true) {
                if (allOnceChecked) {
                    b = l.poll();
                } else {
                    b++;
                    if (b == A.length - 1)
                        allOnceChecked = true;
                }

                if (A[b] > A[a]) {
                    l.add(a);
                    a = b;
                } else
                    l.add(b);


                win[a]++;
                if (win[a] == k || A[a] == max)
                    return A[a];
            }
        }
    }
}
