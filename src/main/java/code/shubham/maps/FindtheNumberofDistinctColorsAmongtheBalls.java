package code.shubham.maps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindtheNumberofDistinctColorsAmongtheBalls {
    class Solution {
        public int[] queryResults(int limit, int[][] A) {
            int[] result = new int[A.length];
            int ri = 0;
            HashMap<Integer, Integer> color = new HashMap<>();
            int distinct = 0;
            Map<Integer, Integer> cf = new HashMap<>();
            for (int[] a : A) {
                int curColor = color.getOrDefault(a[0], 0);

                if (curColor == a[1]) {
                    result[ri++] = distinct;
                    continue;
                }

                int countC = cf.getOrDefault(curColor, 0);
                int countN = cf.getOrDefault(a[1], 0);
                cf.put(curColor, countC - 1);
                cf.put(a[1], countN + 1);

                if (countC == 1)
                    --distinct;
                if (countN == 0)
                    ++distinct;

                color.put(a[0], a[1]);

                result[ri++] = distinct;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.stream(new FindtheNumberofDistinctColorsAmongtheBalls().new Solution().queryResults(1, new int[][] {{0,1},{0,4},{0,4},{0,1},{1,2}})).mapToObj(e -> e).toList());
    }
}
