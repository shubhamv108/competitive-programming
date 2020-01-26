package code.binarysearch;

import static java.lang.Math.log;
import static java.lang.Math.pow;

public class SquareRootOfARealNumber {

    public static double squareRootOfPerfectSquare(int n, double l, double r) {
        double mid = (l+r) / 2;
        double square = mid * mid;
        if (square > n)  return squareRootOfPerfectSquare(n, l, mid);
        if (square < n)  return squareRootOfPerfectSquare(n, mid, r);
        return mid;
    }

    public static int sqrtOfIntegerFloor (int x) {
        if (x == 0 || x == 1) return x;
        int l = 1, r = x, ans = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            int m2 = m * m;
            if (m2 == x) return m;
            if (m2 < x) {
                l = m + 1;
                ans = m;
            } else r = m - 1;
        }
        return ans;
    }

    public static float squareRoot(int n, int precision) {
        int l = 0, r = n;
        int m;
        double ans = 0.0;
        while (l <= r)
        {
            m = (l + r) / 2;
            int m2 = m * m;
            if (m2 == n) {
                return m;
            }

            if (m2 < n) {
                l = m + 1;
                ans = m;
            }

            else {
                r = m - 1;
            }
        }

        double increment = 0.1;
        for (int i = 0; i < precision; i++) {
            while (ans * ans <= n) {
                ans += increment;
            }

            ans = ans - increment;
            increment = increment / 10;
        }
        return (float)ans;
    }

    /**
     * Square Root using log
     *
     * @param n
     * @return
     */
    public static double squareRoot(double n) {
        return pow(2, 0.5 * (log(n) / log(2)));
    }

    /**
     * Babylonian method for square root
     *
     * @param n
     * @return
     */
    public static float squareRootOfFloat(float n) {
        float x = n;
        float y = 1;
        double e = 0.001;
        while(x - y > e) {
            x = (x + y)/2;
            y = n/x;
        }
        return x;
    }

    class NewtonRaphson {
        private static final double EPSILON = 0.001;
        double f(double x) { return x*x*x - x*x + 2; }
        double df_dx(double x) { return 3*x*x - 2*x; }
        double get(double x) {
            double h = f(x)/df_dx(x);
            while (Math.abs(h) >= EPSILON) {
                h = f(x)/df_dx(x);
                x -= h;
            }
            return Math.round(x * 100.0) / 100.0;
        }
    }

    public static void main(String[] args) {
        System.out.print(new SquareRootOfARealNumber().new NewtonRaphson().get(2));
    }
}
