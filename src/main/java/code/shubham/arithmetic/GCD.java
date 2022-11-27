package code.shubham.arithmetic;

public class GCD {
    public class Solution {
        public int gcd(int A, int B) {
            if (B == 0) return A;
            else return gcd(B, A % B);
        }

        public int gcd(int[] A) {
            if (A == null || A.length == 0) return -1;
            int gcd = A[0];
            for (int n : A) {
                gcd = gcd(gcd, n);
                if (1 == gcd) return 1;
            }
            return gcd;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new GCD().new Solution().gcd(new int[] { -2, 0, 2, 4, 6, 8, 10 })
        );
    }

}
