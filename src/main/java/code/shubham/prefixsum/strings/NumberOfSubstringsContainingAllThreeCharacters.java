package code.shubham.prefixsum.strings;

public class NumberOfSubstringsContainingAllThreeCharacters {
    class Solution {
        public int numberOfSubstrings(String s) {
            int n = s.length(), result = 0;
            int[][] pre = new int[n + 1][3];

            pre[1][s.charAt(0) - 'a'] = 1;
            for (int i = 1; i <= n; ++i) {
                pre[i][0] = pre[i-1][0];
                pre[i][1] = pre[i-1][1];
                pre[i][2] = pre[i-1][2];
                int ch = s.charAt(i-1) - 'a';
                ++pre[i][ch];
            }

            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j <= n; ++j) {
                    int ac = pre[j][0] - pre[i][0];
                    int bc = pre[j][1] - pre[i][1];
                    int cc = pre[j][2] - pre[i][2];
                    if (ac > 0 && bc > 0 && cc > 0)
                        ++result;
                }
            }

            return result;
        }
    }

    void main() {
        System.out.println(new Solution().numberOfSubstrings("abcabc"));
    }
}
