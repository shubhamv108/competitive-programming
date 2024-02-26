package code.shubham;

public class NumberOfWaysToDivideALongCorridor {
    class Solution {
        public int numberOfWays(String s) {
            long result = 1, prevSeatIdx = 0, k = 0, mod = (long)1e9 + 7;
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == 'S') {
                    if (++k > 2 && (k & 1) == 1)
                        result = result * (i - prevSeatIdx) % mod;
                    prevSeatIdx = i;
                }
            }
            return ((k & 1) == 0) && k > 0 ? (int) result : 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new NumberOfWaysToDivideALongCorridor().new Solution().numberOfWays("PPSPSP")
        );
    }
}
