package code.shubham.arrays;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RemoveRecoveredIntervals {

    class Solution {

        public int removeCoveredIntervals(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            System.out.println(Arrays.stream(intervals).map(i -> new Interval(i[0], i[1])).collect(Collectors.toList()));
            int count = intervals.length;
            int row = 0;
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[row][0] <= intervals[i][0] && intervals[row][1] >= intervals[i][1]) {
                    count--;
                } else if (intervals[i][0] <= intervals[row][0] && intervals[i][1] >= intervals[row][1]) {
                    count--;
                    row = i;
                } else row = i;
            }

            return count;
        }

        class Interval {
            int start, end;
            Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public String toString() {
                return start + " " + end;
            }
        }
    }

    public static void main(String... args) {
        int[][] a = new int[][] {{66672,75156},{59890,65654},{92950,95965},{9103,31953},{54869,69855},{33272,92693},{52631,65356},{43332,89722},{4218,57729},{20993,92876}};
        System.out.println(
        new RemoveRecoveredIntervals().new Solution().removeCoveredIntervals(a));
    }

}
