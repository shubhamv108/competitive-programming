package code.arrays;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Interval {
    public int start;
    public int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class MergeIntervals {

    public class Solution {
        public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
            intervals.sort(Comparator.comparingInt(i -> i.start));

            for (int i = 0; i < intervals.size() - 1; i++) {
                Interval first = intervals.get(i);
                Interval second = intervals.get(i + 1);

                if (first.start <= second.start && first.end >= second.end) {
                    intervals.remove(i + 1);
                    i--;
                }

                if (second.start < first.end && first.end < second.end) {
                    Interval temp = new Interval(first.start, second.end);
                    intervals.remove(i + 1);
                    intervals.set(i, temp);
                    i--;
                }

            }

            return intervals;
        }
    }

    public static void main(String[] args) {
        ArrayList<Interval> l = new ArrayList<>();
        l.add(new Interval(1, 1));
        l.add(new Interval(2, 9));
        l.add(new Interval(3, 8));
        l.add(new Interval(4, 7));
        l.add(new Interval(5, 6));
        l.add(new Interval(6, 6));
        l.add(new Interval(5, 19));
        new MergeIntervals().new Solution().merge(l).
                forEach(e -> System.out.println(e.start + " " + e.end));
    }

}
