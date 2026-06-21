package code.shubham.sort.count;

public class KthLargestElementInAnArray {
    class Solution {
        public int findKthLargest(int[] A, int k) {
            int min = A[0], max = A[0];
            for (int a : A) {
                min = Math.min(min, a);
                max = Math.max(max, a);
            }

            int[] f = new int[max - min + 1];
            for (int a : A)
                ++f[a - min];

            for (int i = f.length - 1; i >= 0; --i) {
                k -= f[i];
                if (k <= 0)
                    return i + min;
            }

            return -1;
        }
    }

    void main() {

    }
}
