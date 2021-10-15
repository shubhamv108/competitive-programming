package code.dynamicprogramming;

public class LongestPalindromicSubstring {

    class Solution {
        String s;
        Solution(String s) {
            this.s  = s;
        }

        int solve() {
            int max = 0;
            for (int i = 0; i < s.length(); i++) {
                int a = getLps(s, i, i);
                int b = getLps(s, i, i + 1);
                max = Math.max(max, Math.max(a, b));
            }
            return max;
        }

        int getLps(String s, int l, int r) {
            while (l > -1 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            return r - l - 1;
        }
    }

    class SolutionDP {
        String s;
        SolutionDP(String s) {
            this.s  = s;
        }


    }

    public static void main(String[] args) {
        System.out.println(
                new LongestPalindromicSubstring().new Solution("ababa").solve()
        );
    }

}
