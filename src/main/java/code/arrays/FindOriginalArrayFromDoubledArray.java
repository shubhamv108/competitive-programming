package code.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

public class FindOriginalArrayFromDoubledArray {
    class Solution {
        public int[] findOriginalArray(int[] A) {
            int len = A.length;
            if ((len & 1) == 1)
                return new int[0];

            TreeMap<Integer, Integer> freq = new TreeMap<>();
            for (int a : A)
                freq.put(a, freq.getOrDefault(a, 0) + 1);

            Integer dF;
            int n, f;
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                n = entry.getKey();

                f = entry.getValue();

                if (f == 0)
                    continue;

                dF = freq.get(n * 2);
                if (dF == null || dF < f)
                    return new int[0];
                else
                    freq.put(n * 2, dF - f);
            }

            int r = 0, result[] = new int[len/2];
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                n = entry.getKey();
                f = entry.getValue();
                while (f-- > 0)
                    result[r++] = n;
            }

            return result;
        }
    }

    public static void main(String[] args) {
        int[] A = new FindOriginalArrayFromDoubledArray().
                        new Solution().
                        findOriginalArray(
                                new int[] { 0, 0, 0, 0 });
        Arrays.stream(A).forEach(System.out::println);
    }
}
