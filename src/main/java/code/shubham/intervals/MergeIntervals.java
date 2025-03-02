package code.shubham.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;



class Interval {
    public int start;
    public int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

/**
 * https://leetcode.com/problems/merge-intervals/description/
 */
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

    class Solution3 {
        public int[][] merge(int[][] A) {
            int min = A[0][0], max = A[0][0];
            for (int[] a : A) {
                min = Math.min(min, a[0]);
                max = Math.max(max, a[0]);
            }

            int[] range = new int[max - min + 1];
            for (int[] a : A)
                range[a[0] - min] = Math.max(range[a[0] - min], a[1] - min);

            int start = 0, end = 0;
            ArrayList<int[]> result = new ArrayList<>();
            for (int i = 0; i < range.length; ++i) {
                if (range[i] == 0)
                    continue;

                if (i <= end) {
                    end = Math.max(range[i], end);
                } else {
                    result.add(new int[] { start + min, end + min});
                    start = i;
                    end = range[i];
                }
            }

            result.add(new int[] {start + min, end + min});
            return result.toArray(int[][]::new);
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
        int[][] mergeintervals = new MergeIntervals().new Solution3().merge(new int[][] {
            {8, 10},
            {10, 12},
            {14, 16}
        });
        Arrays.stream(mergeintervals).forEach(e -> System.out.println(e[0] + " " + e[1]));
    }

}
