package code.arrays;

import java.util.ArrayList;

public class NegativePositivePeakInArray {

    class Pair {
        Integer index;
        Integer peak;
        Pair(int a, int b) {
            this.index = a;
            this.peak = b;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "index=" + index +
                    ", peak=" + peak +
                    '}';
        }
    }

    class Solution {
        ArrayList<Pair> solve(int[] A) {
            int peak = A[0], index = 0;
            ArrayList<Pair> output = new ArrayList<>();
            for (int x = 1; x < A.length - 1; x++) {
                if (A[x] * A[x-1] > 0) {
                    if (peak < 0 && A[x] < peak) {
                        peak = A[x];
                        index = x;
                    }
                    if (peak >= 0 && A[x] > peak) {
                        peak = A[x];
                        index = x;
                    }
                } else {
                    output.add(new Pair(index, peak));
                    peak = A[x];
                    index = x;
                }
            }
            return output;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new NegativePositivePeakInArray().new Solution().solve(new int[] { 1, 4, 2, -2, -9, 10, 2, 12, 2, -4, -4, -4, -4, 2, 6, 7 })
        );
    }

}
