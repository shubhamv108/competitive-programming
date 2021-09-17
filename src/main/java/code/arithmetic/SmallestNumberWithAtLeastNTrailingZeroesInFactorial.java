package code.arithmetic;

public class SmallestNumberWithAtLeastNTrailingZeroesInFactorial {

    class Solution {
        int powerOfPrimeInNFactorial(int n, int prime) {
            int t = n / prime;
            if (t == 0) return 0;
            return t + powerOfPrimeInNFactorial(t, prime);
        }

        boolean hasNZeroesInNFactorial(int n, int N) {
            return powerOfPrimeInNFactorial(n, 5) >= N;
        }

        boolean check(int p, int n) {
            int temp = p, count = 0, f = 5;
            while (f <= temp) {
                count += temp / f;
                f = f * 5;
            }
            return (count >= n);
        }

        int smallestWithNTrailingZeroesInFactorial(int N) {
            if (N == 1)
                return 5;

            int low = 0;
            int high = 5 * N;

            while (low < high) {
                int mid = (low + high) >> 1;
                if (this.hasNZeroesInNFactorial(mid, N))
                    high = mid;
                else
                    low = mid + 1;
            }

            return low;
        }

    }

    public static void main(String[] args) {
        System.out.println(
                new SmallestNumberWithAtLeastNTrailingZeroesInFactorial()
                    .new Solution().smallestWithNTrailingZeroesInFactorial(5)
        );
    }


}
