package code.arrays;

public class FarthestGreaterOnRight {

    int diff(int[] arr) {
        int[] A = new int[arr.length];
        int len = arr.length;
        for (int i = len - 2; i > -1; i--)
            A[i] = Math.max(A[i], A[i+1]);

        for (int i = 0; i < len; i++) {
            int l = i + 1, r = len - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (A[m] > A[i]) {

                }
            }
        }
        return -1;
    }

    int getFirstLowerBoundInReverse(int[] A, int l, int r, int k) {
        if (A[l] < A[r]) {

        }
        return 0;
    }

}
