package code.shubham.graphs.bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class MinimumJumpsToReachHome {
    class Solution {
        public int minimumJumps(int[] forbidden, int a, int b, int x) {
            Set<Integer> f = Arrays.stream(forbidden).boxed().collect(Collectors.toSet());

            Queue<Integer> q = new LinkedList<>();
            q.offer(0);
            int result = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    int p = q.poll();
                    if (p == x)
                        return result;

                    if (!f.contains(p + a))
                        q.offer(p+a);
                    if (p-b > -1 && !f.contains(p-b))
                        q.offer(p-b);
                }
                result++;
            }
            return -1;
        }
    }
}
