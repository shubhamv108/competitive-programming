package code.shubham.graphs.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class DetermineIfACellIsReachableAtAGivenTime {
    class Solution {
        public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
            int width = Math.abs(fx - sx);
            int height = Math.abs(fy - sy);

            if (width == 0 && height == 0 && t == 1)
                return false;

            return t >= Math.max(width, height);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new DetermineIfACellIsReachableAtAGivenTime().new Solution()
                        .isReachableAtTime(2, 4, 7, 7, 6)
        );
    }
}
