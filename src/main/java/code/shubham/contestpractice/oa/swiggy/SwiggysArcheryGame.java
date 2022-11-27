package code.shubham.contestpractice.oa.swiggy;

import java.util.HashMap;
import java.util.Map;

public class SwiggysArcheryGame {

    class Solution {
        int solve(int N, int[][] positions) {
            Map<Integer, Integer> rows = new HashMap<>();
            int maxBallonRow = 0;
            int maxRowBallon = 0;
            Map<Integer, Integer> cols = new HashMap<>();
            int maxBallonColumn = 0;
            int maxColumnBallon = 0;
            for (int[] pos : positions) {
                rows.put(pos[0], rows.getOrDefault(pos[0], 0) + 1);
                if (rows.get(pos[0]) > maxRowBallon) {
                    maxRowBallon = rows.get(pos[0]);
                    maxBallonRow = pos[0];
                }
                cols.put(pos[1], cols.getOrDefault(pos[1], 0) + 1);
                if (cols.get(pos[1]) > maxColumnBallon) {
                    maxColumnBallon = cols.get(pos[1]);
                    maxBallonColumn = pos[1];
                }
            }

            int burst = 0;
            while (burst < N) {
                if (maxRowBallon > maxColumnBallon) {
                    for (Map.Entry<Integer, Integer> entry : cols.entrySet()) {
                        if (entry.getValue() > 0) burst += entry.getValue();
                        entry.setValue(Math.max(0, entry.getValue() - 1));
                    }
                    maxColumnBallon = Math.max(0, maxColumnBallon-1);
                }
                else if (maxRowBallon < maxColumnBallon) {
                    for (Map.Entry<Integer, Integer> entry : rows.entrySet()) {
                        if (entry.getValue() > 0) burst += entry.getValue();
                        entry.setValue(Math.max(0, entry.getValue() - 1));
                    }
                    maxRowBallon = Math.max(0, maxRowBallon-1);
                }
            }
            return 0;
        }
    }

}
