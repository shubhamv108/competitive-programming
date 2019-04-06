package code.arrays;

import java.util.Arrays;

public class MergeSortedArrays {

    void merge (int[] a, int[] b) {
        if (a.length < b.length) {
            int[] t = a;
            a = b;
            b = t;
        }
        int n = b.length;
        int m = a.length - n;
        int k = m + n - 1;
        while (m > 0 && n > 0) {
            if (a[m-1] > b[n-1]) {
                a[k--] = a[m-1];
                m--;
            } else {
                a[k--] = b[n-1];
                n--;
            }
        }

        while (n > 0) {
            a[k--] = b[n-1];
            n--;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {5, 18, 22, 100, 105, 1002, -1, -1, -1, -1, -1, -1};
        new MergeSortedArrays().merge(a, new int[] {1, 16, 17, 19, 21, 1001});
        Arrays.stream(a).forEach(e -> System.out.println(e + " "));
    }

}
