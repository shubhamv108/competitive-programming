package code.shubham.graphs.topologicalsort;

import java.util.ArrayList;
import java.util.Arrays;
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

    class Solution2 {
        private int top;
        public int[] findOrder(int n, int[][] A) {
            top = n - 1;

            int[] postList = new int[n];
            int[] nextIndex = new int[A.length];
            int[] nextNode = new int[A.length];

            Arrays.fill(postList, -1);

            int index = 0;
            for (int[] prerequisite : A) {
                nextIndex[index] = postList[prerequisite[1]];
                nextNode[index] = prerequisite[0];
                postList[prerequisite[1]] = index;
                index++;
            }

            int[] stack = new int[n];
            int[] visited = new int[n];

            for (int i = 0; i < n; ++i)
                if (visited[i] == 0)
                    if (!DFS(i, visited, postList, nextIndex, nextNode, stack))
                        return new int[0];
            return stack;
        }

        private boolean DFS(int n, int[] visited, int[] postList, int[] nextIndex, int[] nextNode, int[] stack) {
            visited[n] = 1;
            for (int i = postList[n]; i != -1; i = nextIndex[i]) {
                if (visited[nextNode[i]] == 1)
                    return false;

                if (visited[nextNode[i]] == 0)
                    if (!DFS(nextNode[i], visited, postList, nextIndex, nextNode, stack))
                        return false;
            }
            visited[n] = 2;
            stack[top--] = n;
            return true;
        }
    }

}
