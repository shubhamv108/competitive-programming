package code.shubham.priorityqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SkylineProblem {

    class Solution {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            List<List<Integer>> result = new ArrayList<>();
            List<int[]> height = new ArrayList<>();
            for (int[] b : buildings) {
                height.add(new int[] { b[0], -b[2] });
                height.add(new int[] { b[1], b[2] });
            }
            Collections.sort(height, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
            pq.offer(0);
            int prev = 0;
            for (int[] h : height) {
                if (h[1] < 0) {
                    pq.offer(-h[1]);
                } else {
                    pq.remove(h[1]);
                }
                int cur = pq.peek();
                if(prev != cur) {
                    result.add(new ArrayList(Arrays.asList(h[0], cur)));
                    prev = cur;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new SkylineProblem().new Solution().getSkyline(
                        new int[][] {{3,7,8},{3,8,7},{3,9,6},{3,10,5},{3,11,4},{3,12,3},{3,13,2},{3,14,1}}
                )
//                new SkylineProblem().new Solution().getSkyline(
//                        new int[][] {
//                                {0, 2, 3},
//                                {2, 5, 3}
//                        }
//                )
        );
    }
    
}
