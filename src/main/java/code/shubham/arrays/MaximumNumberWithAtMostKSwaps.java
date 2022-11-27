package code.shubham.arrays;

public class MaximumNumberWithAtMostKSwaps {

    class Solution {
        String result = "";
        int solve(int n, int k) {
            recurse(Integer.toString(n).toCharArray(), 0, k);
            return Integer.valueOf(result);
        }
        void recurse(char[] n, int index, int k) {
            if (k == 0) return;
            for (int i = index; i < n.length - 1; i++) {
                for (int j = i+1; j < n.length; j++) {
                    if (n[j] > n[i]) {
                        swap(n, i, j);
                        String t = new String(n);
                        if (t.compareTo(this.result) > 0)
                            this.result = t;
                        recurse(n, index + 1, k-1);
                        swap(n, i, j);
                    }
                }
            }
        }

        void swap(char[] A, int i, int j) {
            char t = A[i];
            A[i] = A[j];
            A[j] = t;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MaximumNumberWithAtMostKSwaps().new Solution().solve(129814999, 4)
        );
    }

}
