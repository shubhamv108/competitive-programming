package code.arithmetic;

public class TrailingZeroesInNFactorial {

    class Solution {
        public int trailingZeroes(int n) {
            int power = 0;
            while (n > 1) {
                n /= 5;
                power += n;
            }
            return power;
        }
    }

    class Solution2 {
        public int trailingZeroes(int n) {
            int power = 0;
            int d = 5;
            while (n/d > 0) {
                power += (n/d);
                d *= 5;
            }
            return power;
        }
    }

    class Solution3 {
        public int trailingZeroes(int n) {
            int t = n/5;
            if (t == 0) return 0 ;
            return t + trailingZeroes(t);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new TrailingZeroesInNFactorial().new Solution3().trailingZeroes(10)
        );
    }

}
