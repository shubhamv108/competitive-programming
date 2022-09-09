package code.dynamicprogramming.digit;

import java.util.ArrayList;

public class SumOfDigitsOfNumbersBetweenAToB {
    class Solution {
        long solve(int a, int b) {
            ArrayList<Integer> dA = this.digits(a - 1);
            ArrayList<Integer> dB = this.digits(b);
            long sumA1 = this.digitSum(dA, dA.size() - 1, 0, true);
            long sumB  = this.digitSum(dB, dB.size() - 1, 0, true);
            return sumB - sumA1;
        }

        Long[][][] dp = new Long[18][172][2];
        long digitSum(ArrayList<Integer> originalDigits, int idx, int sum, boolean tight) {
            if (idx == -1) return sum;
            if (dp[idx][sum][tight ? 1 : 0] != null && !tight)
                return dp[idx][sum][tight ? 1 : 0];
            long curSum = 0;
            int maxDigit = tight ? originalDigits.get(idx) : 9;
            for (int d = 0; d <= maxDigit; d++) {
                curSum += digitSum(originalDigits, idx - 1, sum + d, d == originalDigits.get(idx));
            }
            if (!tight)
                dp[idx][sum][0] = curSum;
            return curSum;
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
        SumOfDigitsOfNumbersBetweenAToB sumOfDigitsOfNumbersBetweenAToB = new SumOfDigitsOfNumbersBetweenAToB();
        System.out.println(sumOfDigitsOfNumbersBetweenAToB.new Solution().solve(1000, 1024));
    }
}
