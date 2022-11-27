package code.shubham.dynamicprogramming.digit;

import java.util.ArrayList;

/**
 * O(log(n^2))
 */
public class CountOfNumbersWithSumKBetweenAToB {

    class Solution {
        int solve(int a, int b, int k) {
            ArrayList<Integer> dA1 = this.digits(a - 1);
            ArrayList<Integer> dB = this.digits(b);
            int countA1 = this.count(dA1, dA1.size() - 1, k, true);
            int countB = this.count(dB, dB.size() - 1, k, true);
            return countB - countA1;
        }

        Integer[][][] dp = new Integer[32][288][2];
        int count(ArrayList<Integer> originalDigits, int idx, int k, boolean tight) {
            if (idx == -1) return k == 0 ? 1 : 0;
            if (k < 0) return 0;
            if (dp[idx][k][tight ? 1 : 0] != null && !tight)
                return dp[idx][k][tight ? 1 : 0];
            int count = 0;
            int maxDigit = tight ? originalDigits.get(idx) : 9;
            for (int i = 0; i <= maxDigit; i++) {
                count += count(originalDigits, idx - 1, k - i, i == maxDigit);
            }
            if (!tight) dp[idx][k][tight ? 1 : 0] = count;
            return count;
        }

        ArrayList<Integer> digits(int n) {
            ArrayList<Integer> result = new ArrayList<>();
            while (n > 0) {
                result.add(n % 10);
                n /= 10;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        CountOfNumbersWithSumKBetweenAToB countOfNumbersWithSumKBetweenAToB = new CountOfNumbersWithSumKBetweenAToB();
        System.out.println(
        countOfNumbersWithSumKBetweenAToB.new Solution().solve(2700, 4000, 29));
    }

}
