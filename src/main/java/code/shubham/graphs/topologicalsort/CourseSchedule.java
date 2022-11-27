package code.shubham.graphs.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule {

    // TopologicalSort
    class Solution {
        public boolean canFinish(int n, int[][] pre) {
            int[] inDegree = new int[n];
            HashMap<Integer, ArrayList<Integer>> m = new HashMap<>();
            for (int i = 0; i < pre.length; i++) {
                inDegree[pre[i][0]]++;
                ArrayList<Integer> l = m.get(pre[i][1]);
                if (l == null)
                    m.put(pre[i][1], l = new ArrayList<>());
                l.add(pre[i][0]);
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < inDegree.length; i++)
                if (inDegree[i] == 0)
                    q.offer(i);

            int k = 0;
            while (!q.isEmpty()) {
                int t = q.poll();
                k++;
                ArrayList<Integer> l = m.get(t);
                if (l != null)
                    for (int i : l)
                        if (--inDegree[i] == 0)
                            q.offer(i);
            }

            if (k == n)
                return true;

            return false;
        }
    }

    class Solution2 {
        public boolean canFinish(int n, int[][] pre) {
            if (n == 0)
                return true;

            int[] outDegree = new int[n];
            for (int i = 0; i < pre.length; i++)
                outDegree[pre[i][1]]++;

            boolean flag = true, visited[] = new boolean[pre.length];
            while (flag) {
                flag = false;
                for (int i = 0; i < pre.length; i++)
                    if (!visited[i])
                        if (outDegree[pre[i][0]] == 0) {
                            visited[i] = true;
                            outDegree[pre[i][1]]--;
                            flag = true;
                        }
            }

            for (int i = 0; i < n; i++)
                if (outDegree[i] != 0)
                    return false;

            return true;
        }
    }

    public static void main(String[] args) {

    }
}
