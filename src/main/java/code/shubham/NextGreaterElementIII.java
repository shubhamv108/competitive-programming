package code.shubham;

public class NextGreaterElementIII {

    class Solution {
        public int nextGreaterElement(int n) {
            int[] A = new int[(int) Math.log10(n) + 1];
            int ni = A.length;
            while (n > 0) {
                A[--ni] = n % 10;
                n /= 10;
            }

            int i = A.length - 2;
            for (; i > -1; --i)
                if (A[i] < A[i + 1])
                    break;

            if (i == -1)
                return -1;

            int j = A.length - 1;
            for (; j > i; --j)
                if (A[j] > A[i])
                    break;

            int t = A[i];
            A[i] = A[j];
            A[j] = t;

            reverse(A, i + 1, A.length - 1);

            long result = 0;
            for (i = 0; i < A.length; ++i)
                result = (result * 10) + A[i];

            return result > Integer.MAX_VALUE ? -1 : (int) result;
        }

        void reverse(int[] A, int x, int y) {
            while (x < y) {
                int t = A[x];
                A[x++] = A[y];
                A[y--] = t;
            }
        }
    }

}
