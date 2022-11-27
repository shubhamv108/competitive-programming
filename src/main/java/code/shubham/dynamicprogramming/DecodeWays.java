package code.shubham.dynamicprogramming;

public class DecodeWays {

    class Solution {
        public int numDecodings(String s) {
            char[] c = s.toCharArray();
            int l = c.length;
            int[] ways = new int[l+1];
            ways[l] = 1;
            ways[l-1] = (c[l-1] == '0' ? 0 : ways[l]);
            for (int i = l-2; i > -1; i--)
            ways[i] = (((c[i] == '1') || (c[i] == '2' && c[i+1] < '7' )) ? ways[i + 2] : 0)
                       +
                      (c[i] == '0' ? 0 : ways[i + 1]);
            return ways[0];
        }
    }

    class Solution2 {
        public int numDecodings(String s) {
            char[] c = s.toCharArray();
            int l = c.length;
            int ways = 0, waysNextNext = 1, waysNext = (c[l-1] == '0' ? 0 : waysNextNext);
            for (int i = l-2; i > -1; i--) {
                ways = (((c[i] == '1') || (c[i] == '2' && c[i+1] < '7' )) ? waysNextNext : 0)
                        +
                        (c[i] == '0' ? 0 : waysNext);
                waysNextNext = waysNext;
                waysNext = ways;
            }
            return waysNext;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new DecodeWays().new Solution().numDecodings("1226")
        );
    }

}
