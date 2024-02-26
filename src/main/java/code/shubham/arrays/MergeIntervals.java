package code.shubham.arrays;

import java.util.*;

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

    class Solution2 {
        public int[][] merge(int[][] A) {
            Arrays.sort(A, (x, y) -> x[1] - y[1]);

            ArrayList<int[]> result = new ArrayList<>();
            result.add(A[0]);
            for (int i = 1; i < A.length; ++i) {
                if (result.get(result.size() - 1)[1] > A[i][0]) {
                    result.get(result.size() - 1)[1] = A[i][1];
                } else {
                    result.add(A[i]);
                }
            }
            return result.stream().toArray(int[][]::new);
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
