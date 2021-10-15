package code.oa.amazon;

import java.util.Arrays;

public class ParkingDilemma {

    class Solution {
        int solve(int[] cars, int k) {
            Arrays.sort(cars);
            int min  = Integer.MAX_VALUE;
            for (int i = 2; i < cars.length; i+=2) {
                int length = cars[i] - cars[i - 2] + 1;
                min = Math.min(min, length);
            }
            return min;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new ParkingDilemma().new Solution().solve(
                        new int[] {2, 10, 8, 17},
                        3
                )
        );
    }

}
