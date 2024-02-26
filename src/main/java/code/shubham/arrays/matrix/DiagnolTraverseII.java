package code.shubham.arrays.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DiagnolTraverseII {
    class Solution {
        public int[] findDiagonalOrder(List<List<Integer>> A) {
            ArrayList<Integer> result = new ArrayList<>();
            LinkedList<int[]> q = new LinkedList<>();
            q.offer(new int[] {0, 0});

            while (!q.isEmpty()) {
                int[] p = q.poll();
                result.add(A.get(p[0]).get(p[1]));
                if (p[1] == 0 && p[0] + 1 < A.size())
                    q.offer(new int[] { p[0] + 1, p[1] });

                if (p[1] + 1 < A.get(p[0]).size())
                    q.offer(new int[] { p[0], p[1] + 1 });
            }

            return result.stream().mapToInt(e -> e).toArray();
        }
    }

    public static void main(String[] args) {
        Arrays.stream(
                new DiagnolTraverseII().new Solution().findDiagonalOrder(
                    Arrays.asList(
                        Arrays.asList(1,2,3),
                        Arrays.asList(4, 5, 6),
                        Arrays.asList(7, 8, 9)
                    )
                )
        ).forEach(System.out::println);
    }
}
