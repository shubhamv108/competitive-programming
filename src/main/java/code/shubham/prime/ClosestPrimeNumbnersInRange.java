package code.shubham.prime;

public class ClosestPrimeNumbnersInRange {

    class Solution {

        static boolean[] isNotPrime = new boolean[1000001];

        static {
            notPrime(1000000);
        }

        public int[] closestPrimes(int l, int r) {
            int[] result = new int[] {-1, -1};
            Integer prev = null;
            var min = r+1;
            for (int i = l; i <= r; ++i) {
                if (i == 2) {
                    result[0] = 2;
                    prev = 2;
                    continue;
                }
                if ((i & 1) == 0 || isNotPrime[i])
                    continue;
                if (prev == null) {
                    prev = i;
                    continue;
                }

                int diff = i - prev;
                if (diff == 2 || diff == 1)
                    return new int[] { prev, i };
                if (diff < min) {
                    min = diff;
                    result[0] = prev;
                    result[1] = i;
                }

                prev = i;
            }

            return result;
        }

        static void notPrime(int r) {
            isNotPrime[0] = isNotPrime[1] = true;
            var sqrt = Math.sqrt(r);
            for (int i = 3; i <= sqrt; i += 2)
                if (!isNotPrime[i])
                    for (int j = i * i; j <= r; j += i)
                        isNotPrime[j] = true;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ClosestPrimeNumbnersInRange().new Solution().closestPrimes(19, 31)[0]);
    }

}
