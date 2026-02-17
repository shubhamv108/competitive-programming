package code.shubham.strings.sort;

import java.util.Arrays;

public class RadixSort {

    class StringRadixSort {

        String[] sort(String[] A) {
            int maxLength = Arrays.stream(A).mapToInt(String::length).max().orElse(0);
            for (int i = maxLength - 1; i >= 0; --i)
                A = sort(A, i);
            return A;
        }

        String[] sort(String[] A, int idx) {
            String[] result = new String[A.length];
            int[] count = new int[257];

            for (String a : A)
                ++count[charAt(a, idx) + 1];

            for (int i = 1; i < 257; ++i)
                count[i] += count[i - 1];

            for (String a : A) {
                result[count[charAt(a, idx)]++] = a;
            }

            return result;
        }

        int charAt(String s, int idx) {
            if (idx >= s.length())
                return 0;
            return s.charAt(idx);
        }
    }

    public static void main(String[] args) {
        Arrays.stream(new RadixSort().new StringRadixSort().sort(new String[] { "shubham", "varshney", "software", "engineer" }))
                .forEach(System.out::println);
    }

}
