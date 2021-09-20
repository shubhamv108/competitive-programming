package code.arithmetic;

public class PowXN {

    public class Solution {
        public double pow(double x, int n) {
            if (n == 0)
                return 1;
            if (n < 0) {
                n = -n  ;
                x = 1/x;
            }
            return ((n & 1) == 0) ? pow(x * x, n/2) : x * pow(x * x, n/2);
        }
    }

    class Solution2 {
        public double myPow(double x, int n) {
            if (x == 1) return 1;
            if (n == 1) return x;
            if (n == 0) return 0;
            if (n < 0) {
                n = -n;
                x = 1 / x;
            }
            return ((n & 1) == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n/ 2);
        }
    }

}
