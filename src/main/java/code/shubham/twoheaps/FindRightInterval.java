package code.shubham.twoheaps;

import java.util.Arrays;
import java.util.TreeMap;

public class FindRightInterval {

    class Solution {
        public int[] findRightInterval(int[][] A) {
            int[] result = new int[A.length];

            TreeMap<Integer, Integer> m = new TreeMap<>();
            for(int i = 0; i < A.length; ++i)
                m.put(A[i][0], i);


            for (int i = 0; i < A.length; ++i) {
                var e = m.ceilingEntry(A[i][1]);
                result[i] = e == null ? -1 : e.getValue();
            }
            return result;
        }
    }

    class Solution2 {
        public int[] findRightInterval(int[][] A) {
            int[] result = new int[A.length];

            int min = A[0][0], max = A[0][0];
            for (int[] a : A) {
                min = Math.min(min, a[0]);
                max = Math.max(max, a[0]);
            }

            int[] m = new int[max - min + 1];
            for (int i = 0; i < A.length; ++i)
                m[A[i][0] - min] = i + 1;

            for(int i = 0; i < A.length; ++i) {
                int end = A[i][1];
                if (end > max) {
                    result[i] = -1;
                    continue;
                }

                while(m[end - min] == 0)
                    ++end;

                result[i] = m[end - min] - 1;
            }

            return result;
        }
    }

    public static void main(String[] args) {
        Arrays.stream(new FindRightInterval().new Solution2().findRightInterval(new int[][] {
            { 3, 4 },
            { 2, 3 },
            { 1, 2 }
        })).forEach(System.out::println);
    }

}
