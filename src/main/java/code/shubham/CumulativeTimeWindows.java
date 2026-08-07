package code.shubham;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class CumulativeTimeWindows {

    private final TreeMap<Long, Double> cumulativeFrequency = new TreeMap<>();

    public void accumulate() {
        long time = System.currentTimeMillis();
        double accumulated = 1d + Optional.ofNullable(cumulativeFrequency.get(time))
                .orElse(Optional.ofNullable(cumulativeFrequency.lastEntry())
                            .map(Map.Entry::getValue)
                            .orElse(0.0));

        cumulativeFrequency.put(time, accumulated);
        System.out.println("Added Entry-> " + time + " " + accumulated);
    }

    public double getCount(long start, long end) {
        var s = cumulativeFrequency.lowerEntry(start);
        var e = cumulativeFrequency.floorEntry(end);
        if (s != null && s == e) {
            System.out.println("Accumulated For" + start + " " + end + " ->");
            return s.getValue();
        }

        Double startFreq = Optional.ofNullable(s).map(Map.Entry::getValue).orElse(0.0);
        Double endFreq = Optional.ofNullable(e).map(Map.Entry::getValue).orElse(0.0);

        if (e != null)
            System.out.println("Taken end Entry = " + e.getKey());
        System.out.println("Accumulated For" + start + " " + end + " ->");
        return endFreq - startFreq;
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
