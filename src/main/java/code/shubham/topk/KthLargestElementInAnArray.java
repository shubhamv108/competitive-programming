package code.shubham.topk;

public interface KthLargestElementInAnArray {

    class Solution {
        public int findKthLargest(int[] A, int k) {
            int min = 10001;
            int max = -10001;

            for (int a: A) {
                min = Math.min(min, a);
                max = Math.max(max, a);
            }

            int[] count = new int[max - min + 1];
            for (int a: A)
                ++count[a - min];

            int remain = k;
            for (int n = count.length - 1; n > -1; --n) {
                remain -= count[n];
                if (remain <= 0)
                    return n + min;
            }

            return -1;
        }
    }

}
