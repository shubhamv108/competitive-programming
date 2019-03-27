package code.graphs;

import java.util.*;

public class SmallestMultipleWith0And1 {

    class Solution {
        public String multiple (int A) {
            Queue<String> q = new LinkedList<>();
            q.offer("1");
            String t = null;
            Set<Integer> rS = new HashSet<>();
            while (!q.isEmpty()) {
                t = q.poll();
                int rem = rem (t, A);
                if (rem == 0) break;
                if (!rS.contains(rem)) {
                    rS.add(rem);
                    q.offer(t + '0');
                    q.offer(t + '1');
                }
            }
            return t;
        }

        private int rem (String s, int A) {
            int r  = 0;
            for (int i = 0; i < s.length(); i++) {
                r = (r * 10) + (s.charAt(i) - '0');
                r = r % A;
            }
            return r;
        }
    }

    public static void main(String[] args) {
        System.out.println(new SmallestMultipleWith0And1().new Solution().multiple(33667));
    }

}
