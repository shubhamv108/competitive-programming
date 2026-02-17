package code.shubham.bitmanipulation;

import java.util.HashMap;

public class CinemaSeatAllocation {
    class Solution {
        public int maxNumberOfFamilies(int n, int[][] A) {
            HashMap<Integer, Integer> R = new HashMap<>();
            for (int[] a : A)
                R.put(a[0],  R.getOrDefault(a[0], 0) | (1 << a[1]));

            int result = 0;
            for (int r : R.values()) {
                boolean b1 = (r & (1 << 2 | 1 << 3 | 1 << 4 | 1 << 5)) == 0;
                boolean b3 = (r & (1 << 6 | 1 << 7 | 1 << 8 | 1 << 9)) == 0;
                boolean b2 = !b1 && !b3 && (r & (1 << 4 | 1 << 5 | 1 << 6 | 1 << 7)) == 0;

                if (b1 & b3)
                    result += 2;
                else if (b1 || b2 || b3)
                    ++result;
            }
            return result + (2 * (n - R.size()));
        }
    }

    public static void main(String[] args) {
        System.out.println(new CinemaSeatAllocation().new Solution().maxNumberOfFamilies(4, new int[][] {{4,3},{1,4},{4,6},{1,7}}));
    }
}
