package code.shubham.graphs.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class OpenTheLock {

    class Solution {
        int result = Integer.MAX_VALUE;
        public int openLock(String[] D, String target) {
            recurse(new char[] {'0', '0', '0', '0'}, 0, new HashSet<>(Arrays.asList(D)), target.toCharArray(), 0);
            return result == Integer.MAX_VALUE ? -1 : result;
        }

        void recurse(char[] state, int si, HashSet<String> D, char[] pc, int level) {
            if (si > 3)
                return;

            if (level > result)
                return;

            if (isEqual(state, pc)) {
                result = Math.min(level, result);
                return;
            }

            if (D.contains(new String(state)))
                return;

            for (char i = 0; i <= 4; ++i) {
                state[si] = i;
                recurse(state, si + 1, D, pc, level + 1);
            }
        }

        boolean isEqual(char[] a, char[] b) {
            for (int i = 0; i < a.length; ++i)
                if (a[i] != b[i])
                    return false;

            return true;
        }
    }

    class Solution2 {
        public int openLock(String[] deadends, String target) {
            HashSet<String> D = new HashSet<>(Arrays.asList(deadends));
            if (D.contains("0000"))
                return -1;

            LinkedList<String> q = new LinkedList<>();
            q.add("0000");
            for (int steps = 0; !q.isEmpty(); ++steps) {
                for (int i = q.size(); i > 0; --i) {
                    String p = q.poll();
                    if (p.equals(target))
                        return steps;

                    for (String next : neighbors(p)) {
                        if (D.contains(next))
                            continue;
                        D.add(next);
                        q.offer(next);
                    }
                }
            }
            return -1;
        }

        ArrayList<String> neighbors(String s) {
            StringBuilder state = new StringBuilder(s);
            ArrayList<String> result = new ArrayList<>();
            for (int i = 0; i < 4; ++i) {
                int x = state.charAt(i) - '0';
                for (int diff = -1; diff <= 1; diff += 2) {
                    int y = (x + diff + 10) % 10;
                    result.add(state.substring(0, i) + ("" + y) + state.substring(i + 1));
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new OpenTheLock().new Solution().openLock(new String[] { "0201","0101","0102","1212","2002" }, "0202"));
    }
}
