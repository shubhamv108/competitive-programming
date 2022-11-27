package code.shubham.contestpractice.oa.microsoft;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumEmployeesToBeInvitedToAMeeting {
    class Solution {
        public int maximumInvitations(int[] favorite) {
            int n = favorite.length;
            int[] inDegree = new int[n];
            for (int i = 0; i < n; i++)
                inDegree[favorite[i]]++;

            Queue<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[n];
            for (int i = 0; i < n; ++i)
                if (inDegree[i] == 0) {
                    visited[i] = true;
                    q.offer(i);
                }

            int[] dp = new int[n];
            while (!q.isEmpty()) {
                int i = q.poll(), j = favorite[i];
                dp[j] = Math.max(dp[j], dp[i] + 1);
                if (--inDegree[j] == 0) {
                    visited[j] = true;
                    q.offer(j);
                }
            }

            int result = 0;
            int result2 = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i] == false) {
                    int length = 0;
                    for (int j = i; visited[j] == false; j = favorite[j]) {
                        visited[j] = true;
                        length++;
                    }
                    if (length == 2)
                        result2 += (2 + dp[i] + dp[favorite[i]]);
                    else
                        result = Math.max(result, length);
                }
            }
            return Math.max(result, result2);
        }
    }
}
