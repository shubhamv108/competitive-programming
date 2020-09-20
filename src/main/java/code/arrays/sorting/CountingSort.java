package code.arrays.sorting;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CountingSort {

    void sort(int[] a) {
        int[] minMax = minMax(a);
        int range = minMax[1] - minMax[0] + 1;
        int[] count = new int[range];
        int[] output = new int[a.length];
        IntStream.range(0, a.length).forEach(i -> count[a[i] - minMax[0]]++);
        IntStream.range(1, range).forEach(i -> count[i] += count[i - 1]);
        for (int i = a.length - 1; i > -1; i--) {
            output[count[a[i] - minMax[0]] - 1] = a[i];
            count[a[i] - minMax[0]]--;
        }
        IntStream.range(0, a.length).forEach(i -> a[i] = output[i]);
    }

    int[] minMax(int[] arr) {
        int[] minMax = new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE };
        for (int n : arr) {
            minMax[0] = Math.min(minMax[0], n);
            minMax[1] = Math.max(minMax[1], n);
        }
        return minMax;
    }

    public static void main(String[] args) {
        int[] a = new int[] { -1, 3, 5, 2 };
        new CountingSort().sort(a);
        Arrays.stream(a).forEach(e -> System.out.print(e + " "));
    }

}
