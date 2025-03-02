package code.shubham.intervals;

/**
 * https://leetcode.com/problems/car-pooling/description/
 */
public class CarPooling {

    class Solution {
        public boolean carPooling(int[][] A, int capacity) {
            int max = 0, min = 1001;
            for (int[] a : A) {
                min = Math.min(min, a[1]);
                max = Math.max(max, a[2]);
            }

            int[] passengers = new int[max - min + 1];
            for (int[] a : A) {
                passengers[a[1] - min] += a[0];
                passengers[a[2] - min] -= a[0];
            }

            int occupancy = 0;
            for (int p : passengers) {
                occupancy += p;
                if (occupancy > capacity)
                    return false;
            }

            return true;
        }
    }

}
