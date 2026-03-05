package code.shubham.bitmanipulation;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SortIntegersByTheNumberOf1Bits {
    class Solution {
        public int[] sortByBits(int[] A) {
            int n = A.length;
            Integer[] N = Arrays.stream(A).boxed().toArray(Integer[]::new);
            Arrays.sort(N, (x, y) -> {
                int d = setBitCount(x) - setBitCount(y);
                return d == 0 ? x - y : d;
            });
            IntStream.range(0, n).forEach(i -> A[i] = N[i]);
            return A;
        }

        int setBitCount(int n) {
            int c = 0;
            for (int i = 0; i <= 31; ++i)
                if ((n & (1 << i)) != 0)
                    ++c;
            return c;
        }
    }

    public static void main(String[] args) {
        Arrays.stream(new SortIntegersByTheNumberOf1Bits()
                              .new Solution()
                              .sortByBits(new int[] { 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1 }))
                .forEach(System.out::println);
    }
}
