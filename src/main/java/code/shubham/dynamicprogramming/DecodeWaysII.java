package code.shubham.dynamicprogramming;

public class DecodeWaysII {

    class Solution {
        int M = 1000000007;
        public int numDecodings(String s) {
            char[] c = s.toCharArray();
            long dp1 = 1, dp2 = c[0] == '*' ? 9 : c[0] == '0' ? 0 : 1;
            for (int i = 1; i < s.length(); i++) {
                long temp = dp2;
                if (c[i] == '*') {
                    dp2 = (9 * dp2) % M;
                    if (c[i - 1] == '1')
                        dp2 = (dp2 + 9 * dp1) % M;
                    else if (c[i - 1] == '2')
                        dp2 = (dp2 + 6 * dp1) % M;
                    else if (c[i - 1] == '*')
                        dp2 = (dp2 + 15 * dp1) % M;
                } else {
                    dp2 = c[i] != '0' ? dp2 : 0;
                    if (s.charAt(i - 1) == '1')
                        dp2 = (dp2 + dp1) % M;
                    else if (c[i - 1] == '2' && c[i] <= '6')
                        dp2 = (dp2 + dp1) % M;
                    else if (c[i - 1] == '*')
                        dp2 = (dp2 + (c[i] <= '6' ? 2 : 1) * dp1) % M;
                }
                dp1 = temp;
            }
            return (int) dp2;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new DecodeWaysII().new Solution().numDecodings("*")
        );
    }

}
