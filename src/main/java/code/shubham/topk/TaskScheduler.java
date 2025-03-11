package code.shubham.topk;

public class TaskScheduler {

    class Solution {
        public int leastInterval(char[] A, int n) {
            // A-> 3, B -> 3
            // probably (n+1 * maxFreq-1) + lastdiff when unique char <= n+1

            int[] f = new int[26];
            for (char a : A)
                ++f[a - 65];

            int maxFreq = f[0];
            for (int a : f)
                maxFreq = Math.max(maxFreq, a);

            int lastDiff = 0;
            for (int a : f)
                if (maxFreq == a)
                    ++lastDiff;

            return Math.max(A.length, ((n+1) * (maxFreq-1)) + lastDiff);
        }
    }

}
