package code.shubham;

import java.util.Map;
import java.util.TreeMap;

public class HitCounter {

    TreeMap<Integer, Integer> cumulativeCount = new TreeMap<>();

    public void hit(int timestamp) {
        Map.Entry<Integer, Integer> last = cumulativeCount.lastEntry();
        Integer currentCount = last == null ? 0 : last.getValue();
        cumulativeCount.put(timestamp, currentCount + 1);
    }

    public int getHits(int timestamp) {
        Map.Entry<Integer, Integer> entry = cumulativeCount.floorEntry(timestamp);
        if (entry == null)
            return 0;

        Map.Entry<Integer, Integer> entry300 = cumulativeCount.floorEntry(timestamp - 300);
        int fiveMinsAgo = 0;
        if (entry300 != null)
            fiveMinsAgo = entry300.getValue();
        return entry.getValue() - fiveMinsAgo;
    }
}
