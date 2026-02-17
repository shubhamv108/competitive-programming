package code.shubham._01Knapsack.PalindromicSubsequence;

public class LongestAlmostPalidromicSubsequence {
    class Solution {
        int result = 0;
        public int almostPalindromic(String A) {
            int al = A.length(), result = 0;
            for (int i = 0; i < al; ++i) {
                result = Math.max(result,
                                  Math.max(
                                          expandAlmost(A, al , i, i),
                                          expandAlmost(A, al , i, i + 1)));
            }

            return Math.min(result, al);
        }

        int expandAlmost(String A, int al, int l, int r) {
            int[] p = expand(A, al, l, r);
            int[] pl = expand(A, al, p[0] - 1, p[1]);
            int[] pr = expand(A, al, p[0], p[1] + 1);

            return Math.max(p[1] - p[0], Math.max(pl[1] - pl[0] - 1, pr[1] - pr[0] - 1));
        }

        int[] expand(String A, int al, int l, int r) {
            while (l >= 0 && r < al && A.charAt(l) == A.charAt(r)) {
                --l;
                ++r;
            }
            return new int[] { l, r };
        }
    }
}
