package code.dynamicprogramming;

public class LargestNumberWithSumOfCostsOfDigitsIsK {

    // https://www.geeksforgeeks.org/construct-the-largest-number-whose-sum-of-cost-of-digits-is-k/
    class Solution {
        int solve(int k, int[] costsOFDigits) {
            String[][] dp = new String[costsOFDigits.length+1][k + 1];
            String result = recurse(costsOFDigits, 0, costsOFDigits.length, k, dp);
            return Integer.valueOf(result);
        }

        String recurse(int[] costsOFDigits, int index, int length, int k, String[][] dp) {
            if (k == 0) return "";
            if (k < 0 || index == length) return "0";
            if (dp[index][k] != null) return dp[index][k];
            String exclude = recurse(costsOFDigits, index+1, length, k, dp);
            String include = (index + 1) + recurse(costsOFDigits, 0, length, k - costsOFDigits[index], dp);
            return dp[index][k] = max(include, exclude);
        }

        String max(String a, String b) {
            if (a.indexOf("0") < 0) return b;
            if (b.indexOf("0") < 0) return a;
            return a.length() > b.length() ? a : b;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new LargestNumberWithSumOfCostsOfDigitsIsK().new Solution().solve(5, new int[] {12, 3, 5, 1, 2, 6, 8, 9, 1})
        );
    }

}
