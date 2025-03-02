package code.shubham.intervals;

/**
 * https://leetcode.com/problems/meeting-rooms-ii/description/
 */
public class MeetingRoomsII {

    // Time Complexity: O(n), Space Complexity: O(n)
    class Solution {

        public int solve(int[][] A) {
            int result = 0;

            int min = A[0][0], max = A[0][1];
            for (int[] a : A) {
                min = Math.min(min, a[0]);
                max = Math.max(max, a[1]);
            }

            int[] timings = new int[max - min + 1];
            for (int[] a : A) {
                ++timings[a[0] - min];
                --timings[a[1] - min];
            }

            int rooms = 0;
            for (int timing : timings) {
                rooms += timing;
                result = Math.max(result, rooms);
            }
            return result;
        }
    }

}
