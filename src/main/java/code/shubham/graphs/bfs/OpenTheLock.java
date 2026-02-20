package code.shubham.graphs.bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class OpenTheLock {

    class Solution {
        int result = 0;
        public int openLock(String[] deadends, String T) {
            if ("0000".equals(T))
                return 0;

            HashSet<String> D = new HashSet<>(Arrays.asList(deadends));
            if (!D.add("0000"))
                return -1;
            Queue<String> q = new LinkedList<>();
            q.offer("0000");

            for (int result = 0; !q.isEmpty(); ++result) {
                for (int i = q.size(); i > 0; --i) {
                    String p = q.poll();
                    for (int pos = 0; pos < 4; ++pos) {
                        int n = p.charAt(pos) - '0';
                        for (int d = -1; d <= 1; d += 2) {
                            char ch = (char) (((n + d + 10) % 10) + '0');
                            String next = p.substring(0, pos) + ch + p.substring(pos + 1);
                            if (next.equals(T))
                                return result + 1;
                            if (D.add(next))
                                q.offer(next);
                        }
                    }
                }
            }

            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new OpenTheLock().new Solution().openLock(new String[] { "0201","0101","0102","1212","2002" }, "0202"));
        System.out.println(new OpenTheLock().new Solution().openLock(new String[] { "8888" }, "0009"));
    }
}
