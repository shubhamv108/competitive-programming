package code.bitmanipulation;

import java.util.concurrent.ConcurrentHashMap;

public class ConcatenationOfConsecutiveBinaryNumbers {
    class Solution {
        public int concatenatedBinary(int n) {
            int MOD = 1000000007;
            long sum = 0;
            int bitCount = 0;

            for (int i = 1; i <= n; i++) {
                if ( (i & (i - 1)) == 0)
                    bitCount++;
                sum = ((sum << bitCount) | i) % MOD;
            }

            return (int) sum;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new ConcatenationOfConsecutiveBinaryNumbers().new Solution().concatenatedBinary(6)
        );
    }
}
