package code.shubham.graphs.dfs;

import java.util.HashMap;
import java.util.HashSet;

public class TimeNeededToInformAllEmployees {
    class Solution {
        public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
            HashMap<Integer, HashSet<Integer>> m = new HashMap<>();
            for (int e = 0; e < n; e++)
                m.computeIfAbsent(manager[e], a -> new HashSet<>()).add(e);

            return visit(headID, informTime, m);
        }

        int visit(int node, int[] informTime, HashMap<Integer, HashSet<Integer>> m) {
            int result = informTime[node];
            for (int e: m.get(node)) {
                result += visit(e, informTime, m);
                m.remove(e);
            }
            return result;
        }
    }

    public static void main(String[] args) {

    }
}
