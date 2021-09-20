package code.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxNumberOfMeetingsThatCanBeScheduledInOneMeetingRoom {

    class Solution {
        int solve(int[] start, int[] duration) {
            int result = 1;
            List<int[]> intervals = new ArrayList<>();
            for (int i = 0; i < start.length; i++)
                intervals.add(new int[] { start[i], start[i] + duration[i] - 1 });
            Collections.sort(intervals, (a, b) -> a[1] - b[1]);
            int curEnd = intervals.get(0)[1];
            for (int i = 1; i < intervals.size(); i++) {
                if (intervals.get(i)[0] > curEnd) {
                    result++;
                    curEnd = intervals.get(i)[1];
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MaxNumberOfMeetingsThatCanBeScheduledInOneMeetingRoom()
                    .new Solution()
                        .solve(new int[] { 1, 3, 0, 5, 8, 5 }, new int[] {  2, 2, 7, 3, 2, 5 })
        );
    }
}
