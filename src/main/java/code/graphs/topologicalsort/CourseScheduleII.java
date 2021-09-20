package code.graphs.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class CourseScheduleII {

    class Solution {
        public int[] findOrder(int n, int[][] pre) {
            int[] result = new int[n];
            int[] indegree = new int[n];
            HashMap<Integer, ArrayList<Integer>> m = new HashMap<>();
            for (int i = 0; i < pre.length; i++) {
                indegree[pre[i][0]]++;
                ArrayList<Integer> l = m.get(pre[i][1]);
                if (l == null) m.put(pre[i][1], l = new ArrayList<>());
                l.add(pre[i][0]);
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < indegree.length; i++) {
                if (indegree[i] == 0) {
                    q.offer(i);
                }
            }

            int k = 0;
            while (!q.isEmpty()) {
                int t = q.poll();
                result[k++] = t;
                ArrayList<Integer> l = m.get(t);
                if (l != null) {
                    for (int i : l) {
                        if (--indegree[i] == 0) {
                            q.offer(i);
                        }
                    }
                }
            }
            if (k == n) {
                return result;
            }
            return new int[0];
        }
    }

}
