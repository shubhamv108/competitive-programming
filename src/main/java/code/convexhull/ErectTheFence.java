package code.convexhull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Stack;

public class ErectTheFence {

    class Solution {
        public int[][] outerTrees(int[][] points) {
            Arrays.sort(points, new Comparator<int[]>() {
                public int compare(int[] p, int[] q) {
                    return q[0] - p[0] == 0 ? q[1] - p[1] : q[0] - p[0];
                }
            });
            Stack<int[]> hull = new Stack<>();
            for (int i = 0; i < points.length; i++) {
                while (hull.size() >= 2
                        && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) > 0)
                    hull.pop();
                hull.push(points[i]);
            }
            hull.pop();
            for (int i = points.length - 1; i >= 0; i--) {
                while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) > 0)
                    hull.pop();
                hull.push(points[i]);
            }
            // remove redundant elements from the stack
            HashSet<int[]> ret = new HashSet<>(hull);
            return ret.toArray(new int[ret.size()][]);
        }

        public int orientation(int[] p, int[] q, int[] r) {
            return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
        }
    }

    public static void main(String[] args) {
        Arrays.stream(
                new ErectTheFence().new Solution().outerTrees(new int[][] {
                        {1,2},
                        {2,2},
                        {4,2}
                })
        ).forEach(e -> System.out.println(e[0] + " " + e[1]));
    }

}
