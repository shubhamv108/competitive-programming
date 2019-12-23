package code.strings;

/**
 * ToDo
 */
public class MinimumCharactersRequiredToMakeAStringPalindromicByInsertingInStarting {
    public class Solution {
        public int solve(String A) {
            StringBuilder sb = new StringBuilder();
            sb.append(A).append('$').append(new StringBuilder(A).reverse());
            int n = sb.length();
            int lps[] = new int[n];
            int i = 1, len = 0;
            lps[0] = 0;

            while (i < n) {
                if (sb.charAt(i) == sb.charAt(len)) {
                    len++;
                    lps[i] = len;
                    i++;
                } else {
                    if (len != 0) {
                        len = lps[len - 1];
                    } else {
                        lps[i] = 0;
                        i++;
                    }
                }
            }
            return A.length()- lps[sb.length() - 1];
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinimumCharactersRequiredToMakeAStringPalindromicByInsertingInStarting().new Solution().solve("AACECAAAA"));
    }

}
