package code.shubham.arithmetic;

/**
 * Exactly Four Divisors
 */
public class FourDivisors {
    class Solution {
        public int sumFourDivisors(int[] A) {
            int sum = 0;
            for (int a : A) {
                int div = 0;

                double srt = Math.sqrt(a);
                for (int d = 2; d <= srt; ++d) {
                    if (a % d == 0) {
                        if (div == 0)
                            div = d;
                        else {
                            div = 0;
                            break;
                        }
                    }
                }

                if (div > 0 && div != a / div)
                    sum += 1 + a + div + a / div;
            }
            return sum;
        }
    }
}
