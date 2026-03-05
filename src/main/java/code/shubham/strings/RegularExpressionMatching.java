package code.shubham.strings;

public class RegularExpressionMatching {
    class Solution {

        public boolean isMatch(String text, String pattern) {
            char[] t = text.toCharArray();
            char[] p = pattern.toCharArray();
            return match(t, p, 0, 0);
        }

        private boolean match(char[] A, char[] P, int ai, int pi) {
            if (pi == P.length)
                return ai == A.length;

            boolean firstMatch = (ai < A.length && (P[pi] == A[ai] || P[pi] == '.'));


            if (pi + 1 < P.length && P[pi + 1] == '*')
                return match(A, P, ai, pi + 2) ||
                        (firstMatch && match(A, P, ai + 1, pi));

            return firstMatch && match(A, P, ai + 1, pi + 1);

        }
    }
}
