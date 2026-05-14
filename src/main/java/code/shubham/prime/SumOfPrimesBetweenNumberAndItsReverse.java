package code.shubham.prime;

public class SumOfPrimesBetweenNumberAndItsReverse {
    class Solution {
        public int sumOfPrimesInRange(int n) {
            int r = rev(n);
            int max = Math.max(r, n), min = Math.min(r, n);
            int result = 0;
            boolean[] np = new boolean[max+1];
            np[0] = true;
            np[1] = true;
            int sqrt = (int) Math.ceil(Math.sqrt(max));
            for (int i = 3; i <= sqrt; i += 2)
                if (!np[i])
                    for (int j = i * i; j <= max; j += i)
                        np[j] = true;

            if (min <= 2 && max >= 2)
                result += 2;

            for (int i = (min & 1) == 0 ? min + 1: min; i <= max; i += 2)
                if (!np[i])
                    result += i;

            return result;
        }

        int rev(int n) {
            int rev = 0;
            while (n > 0) {
                rev = (rev * 10) + (n % 10);
                n /= 10;
            }
            return rev;
        }
    }

    void main() {
        System.out.println(new SumOfPrimesBetweenNumberAndItsReverse().new Solution().sumOfPrimesInRange(2));
    }
}
