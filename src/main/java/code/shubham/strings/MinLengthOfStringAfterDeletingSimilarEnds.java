package code.shubham.strings;

public class MinLengthOfStringAfterDeletingSimilarEnds {
    class Solution {
        public int minimumLength(String s) {
            return recurse(s, 0, s.length() - 1);
        }

        int recurse(String s, int l, int r) {
            if (l > r)
                return 0;

            if (l == r)
                return 1;

            if (s.charAt(l) != s.charAt(r))
                return r - l + 1;

            char c = s.charAt(l);
            int prefixEnd = l + 1;
            for (; prefixEnd < s.length(); ++prefixEnd)
                if (s.charAt(prefixEnd) != c)
                    break;

            --prefixEnd;

            int suffixStart = r - 1;
            for (; suffixStart > -1 && suffixStart > prefixEnd; --suffixStart)
                if (s.charAt(suffixStart) != c)
                    break;

            ++suffixStart;

            return recurse(s, prefixEnd + 1, suffixStart - 1);
        }
    }

    class Solution2 {
        public int minimumLength(String s) {
            int l = 0, r = s.length() - 1, c;
            while (l < r && s.charAt(l) == s.charAt(r)) {
                c = s.charAt(l);
                while (l <= r && s.charAt(l) == c)
                    ++l;
                while (l < r && s.charAt(r) == c)
                    --r;
            }
            return r - l + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MinLengthOfStringAfterDeletingSimilarEnds()
                        .new Solution()
                        .minimumLength("bbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbbbbbbbbbbbbccbcbcbccbbabbb"));
    }
}
