package code.shubham.bitmanipulation;

import java.util.Arrays;
import java.util.Comparator;

class SortIntegerByBitCount {
    class Solution1 {
        public int[] sortByBits(int[] A) {
            Integer[] a = Arrays.stream(A).boxed().toArray(Integer[]::new);
            Comparator<Integer> c = (x, y) -> Integer.bitCount(x) - Integer.bitCount(y);
            Arrays.sort(a, c.thenComparing((x, y) -> x - y));
            return Arrays.stream(a).mapToInt(e -> e).toArray();
        }
    }
    class Solution2 {
        public int[] sortByBits(int[] A) {
            Integer[] a = Arrays.stream(A).boxed().toArray(Integer[]::new);
            Arrays.sort(a, new BitCountComparator());
            return Arrays.stream(a).mapToInt(Integer::intValue).toArray();
        }

        class BitCountComparator implements Comparator<Integer> {
            private int bitCount1(int n) {
                int mask = 1;
                int count = 0;

                while (n > 0) {
                    if ((n & mask) > 0) {
                        count++;
                        n ^= mask;
                    }

                    mask <<= 1;
                }

                return count;
            }

            private int bitCount2(int n) {
                int count = 0;

                while (n > 0) {
                    count++;
                    n &= (n - 1); // 0 or power of two
                }

                return count;
            }

            @Override
            public int compare(Integer a, Integer b) {
                int ac = bitCount2(a);
                int bc = bitCount2(b);
                return ac == bc ? a - b : ac - bc;
            }
        }
    }

    class Solution3 {
        public int[] sortByBits(int[] A) {
            for(int i = 0; i < A.length; i++)
                A[i] += 10001 * Integer.bitCount(A[i]);

            Arrays.sort(A);
            for (int i=0; i < A.length; i++)
                A[i] %= 10001;
            return A;
        }
    }

    public static void main(String[] args) {
        Arrays.stream(
            new SortIntegerByBitCount().new Solution3().sortByBits(new int[] { 1024,512,256,128,64,32,16,8,4,2,1 })
        ).forEach(System.out::println);
    }

}