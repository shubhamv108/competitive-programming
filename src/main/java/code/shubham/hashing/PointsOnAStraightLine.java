package code.shubham.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PointsOnAStraightLine {
    public class Solution {
        public int maxPoints(ArrayList<Integer> a, ArrayList<Integer> b) {
            if (null == a || null == b || a.size() != b.size()) return 0;
            if (1 == a.size()) return 1;
            int max = 0;
            int duplicate, vertical, count;
            double slope;
            Map<Double, Integer> slopeCount = new HashMap<>();
            for (int i = 0;  i < a.size(); i++) {
                duplicate = 1; vertical = 0;
                for (int j = i + 1; j < b.size(); j++) {
                    if (a.get(i) == a.get(j)) {
                        if (b.get(i) == b.get(j)) duplicate++;
                        else vertical++;
                    } else {
                        slope = b.get(i) == b.get(j) ? 0.0 : (1.0 * (b.get(j) - b.get(i))) / (a.get(j) - a.get(i));
                        count = slopeCount.getOrDefault(slope, 0) + 1;
                        slopeCount.put(slope, count);
                        max = Math.max(max, count);
                    }
                }
                for (int c : slopeCount.values()) max = Math.max(max, c + duplicate);
                slopeCount.clear();
                max = Math.max(max, vertical + duplicate);
            }
            return max;
        }

    }
}
