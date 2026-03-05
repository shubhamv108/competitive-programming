package code.shubham.strings;

public class WildcardMatching {
    class Solution {
        public boolean isMatch(String A, String P) {
            return recurse(A.toCharArray(), P.toCharArray(), 0, 0);
        }

        boolean recurse(char[] A, char[] P, int ai, int pi) {
            if (pi == P.length && ai == A.length)
                return true;

            if (pi == P.length)
                return false;

            if (ai == A.length) {
                for (int i = pi; i < P.length; ++i)
                    if (P[i] != '*')
                        return false;
                return true;
            }

            if (P[pi] == '?' || (ai < A.length  && P[pi] == A[ai]))
                return recurse(A, P, ai + 1, pi + 1);
            else if (P[pi] == '*') {
                return recurse(A, P, ai, pi + 1) || recurse(A, P, ai + 1, pi);
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new WildcardMatching().new Solution().isMatch("acdcb", "a*c?b")); // expected false, actcual true
    }
}
