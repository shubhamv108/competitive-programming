package code.shubham.trees;

import java.util.ArrayList;
import java.util.HashMap;

public class MosProfitablePathInATree {

    class Solution {
        int result = 0;

        int solve(int[][] E, int B, int[] cost) {
            int n = cost.length;
            ArrayList<ArrayList<Integer>> g = new ArrayList<>();
            for (int i = 0; i < n; ++i)
                g.add(new ArrayList<>());

            for (int[] e : E) {
                g.get(e[0]).add(e[1]);
                g.get(e[1]).add(e[0]);
            }

            HashMap<Integer, Integer> upTime = new HashMap<>();
            traverseUp(B, -1, 0, upTime, g, cost);
            recurseDown(0, -1, 0, upTime, g, cost);

            return result;
        }

        boolean traverseUp(int n, int p, int t, HashMap<Integer, Integer> upTime, ArrayList<ArrayList<Integer>> g, int[] cost) {
            if (n == 0) {
                upTime.put(n, t);
                return true;
            }

            for (int next : g.get(n)) {
                if (next == p)
                    continue;

                if (traverseUp(next, n, t + 1, upTime, g, cost)) {
                    upTime.put(n, t);
                    return true;
                }
            }
            return false;
        }

        void recurseDown(int n, int p, int t, HashMap<Integer, Integer> upTime, ArrayList<ArrayList<Integer>> g, int[] cost) {
            int score = 0;
            if (!upTime.containsKey(n) || t < upTime.get(n))
                score = cost[n];
            else if (t == upTime.get(n))
                score = cost[n] / 2;

            if (g.get(n).isEmpty())
                result = Math.max(result, score);

            for (int next : g.get(n)) {
                if (next == p)
                    continue;
                recurseDown(next, n, t + 1, upTime, g, cost);
            }
        }
    }

    public static void main(String[] args) {

    }

}
