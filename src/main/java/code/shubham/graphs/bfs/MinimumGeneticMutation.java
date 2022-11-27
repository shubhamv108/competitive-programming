package code.shubham.graphs.bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class MinimumGeneticMutation {
    class Solution {
        public int minMutation(String start, String end, String[] bank) {
            HashSet<String> B = new HashSet<>(Arrays.asList(bank));
            LinkedList<String> q = new LinkedList<>();
            q.offer(start);

            int steps = 0, size, i;
            String p, next;
            char P[], original;

            while (!q.isEmpty()) {
                size = q.size();
                while (size-- > 0) {
                    p = q.remove();
                    if (p.equals(end))
                        return steps;

                    P = p.toCharArray();

                    for (char c : new char[] { 'A', 'C', 'G', 'T' }) {
                        for (i = 0; i < P.length; i++) {
                            original = P[i];
                            P[i] = c;
                            next = new String(P);
                            if (B.contains(next)) {
                                q.offer(next);
                                B.remove(next);
                            }
                            P[i] = original;
                        }
                    }
                }
                steps++;
            }
            return -1;
        }
    }
}
