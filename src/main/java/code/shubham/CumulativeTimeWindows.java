package code.shubham;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class CumulativeTimeWindows {

    private final TreeMap<Long, Double> cumulativeFrequency = new TreeMap<>();

    public void accumulate() {
        long time = System.currentTimeMillis();
        double accumulated = 1d + cumulativeFrequency.getOrDefault(
                time,
                Optional.ofNullable(cumulativeFrequency.lastEntry())
                        .map(Map.Entry::getValue)
                        .orElse(0.0));
        cumulativeFrequency.put(time, accumulated);
    }

    public double getCount(long from, long to) {
        var leftEntry  = cumulativeFrequency.lowerEntry(from);
        var rightEntry = cumulativeFrequency.floorEntry(to);
        if (leftEntry == rightEntry)
            return leftEntry == null ? 0.0 : leftEntry.getValue();

        Double leftFreq  = Optional.ofNullable(leftEntry).map(Map.Entry::getValue).orElse(0.0);
        Double rightFreq = Optional.ofNullable(rightEntry).map(Map.Entry::getValue).orElse(0.0);
        return rightFreq - leftFreq;
    }

    void main() throws InterruptedException {
        CumulativeTimeWindows lot = new CumulativeTimeWindows();

        System.out.println(lot.getCount(4324, 32414));
        long a = System.currentTimeMillis();
        Thread.sleep(10);
        lot.accumulate();
        Thread.sleep(10);
        long b = System.currentTimeMillis();
        System.out.println(lot.getCount(a, b));
        Thread.sleep(10);
        lot.accumulate();
        Thread.sleep(10);
        long c = System.currentTimeMillis();
        Thread.sleep(10);
        lot.accumulate();
        Thread.sleep(10);
        long d = System.currentTimeMillis();
        Thread.sleep(10);
        lot.accumulate();
        Thread.sleep(10);
        long e = System.currentTimeMillis();

        System.out.println(lot.getCount(b, d));
        System.out.println(lot.getCount(a, e));
    }

}
