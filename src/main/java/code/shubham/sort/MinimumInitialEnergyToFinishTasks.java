package code.shubham.sort;

import java.util.Arrays;

public class MinimumInitialEnergyToFinishTasks {
    class Solution {
        public int minimumEffort(int[][] A) {
            Arrays.sort(A, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
            int result = 0, cur = 0;
            for (int[] a : A) {
                if (cur < a[1]) {
                    result += (a[1] - cur);
                    cur = a[1];
                }
                cur -= a[0];
            }
            return result;
        }
    }

    void main() {
        System.out.println(new MinimumInitialEnergyToFinishTasks().new Solution().minimumEffort(
            new int[][] { { 1, 3 }, { 2, 4 }, { 10, 11 }, { 10, 12 }, {8, 9} } ));
    }
}
