package code.shubham;

public class NextPalindromeUsingSameDigits {

    class Solution {
        public String nextPalindrome(String num) {
            final int n = num.length();
            int[] A = new int[n / 2];

            for (int i = 0; i < A.length; ++i)
                A[i] = num.charAt(i) - '0';

            if (!nextPermutation(A))
                return "";

            StringBuilder sb = new StringBuilder();
            for (int a : A)
                sb.append(a);

            if ((n & 1) == 1)
                return sb.toString() + num.charAt(n / 2) + sb.reverse().toString();
            return sb.toString() + sb.reverse().toString();
        }

        private boolean nextPermutation(int[] A) {
            final int n = A.length;

            // From the back to the front, find the first num < nums[i + 1].
            int i;
            for (i = n - 2; i >= 0; --i)
                if (A[i] < A[i + 1])
                    break;

            if (i < 0)
                return false;

            // From the back to the front, find the first num > nums[i] and swap it with
            // nums[i].
            for (int j = n - 1; j > i; --j)
                if (A[j] > A[i]) {
                    swap(A, i, j);
                    break;
                }

            // Reverse nums[i + 1..n - 1].
            reverse(A, i + 1, n - 1);
            return true;
        }

        private void reverse(int[] A, int l, int r) {
            if (l >= r)
                return;

            swap(A, l, r);
            reverse(A, ++l, --r);
        }

        private void swap(int[] A, int x, int y) {
            int t = A[x];
            A[x] = A[y];
            A[y] = t;
        }
    }

    public static void main(String[] args) {
        System.out.println(new NextPalindromeUsingSameDigits().new Solution().nextPalindrome("1234123"));
        System.out.println(new NextPalindromeUsingSameDigits().new Solution().nextPalindrome("423431724"));
    }

}
