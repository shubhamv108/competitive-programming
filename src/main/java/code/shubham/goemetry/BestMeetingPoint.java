package code.shubham.goemetry;

import java.util.ArrayList;
import java.util.Collections;

public class BestMeetingPoint {

    public class Solution {
        public int minTotalDistance(int[][] grid) {
            ArrayList<Integer> rows = new ArrayList<>();
            ArrayList<Integer> cols = new ArrayList<>();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        rows.add(i);
                        cols.add(j);
                    }
                }
            }
            Collections.sort(cols);
            return minDist(rows) + minDist(cols);
        }

        public int minDist(ArrayList<Integer> vals) {
            int l = 0, r = vals.size() - 1, result = 0;
            while (l < r) {
                result += (vals.get(r) - vals.get(l));
                l++;
                r--;
            }
            return result;
        }
    }

}
