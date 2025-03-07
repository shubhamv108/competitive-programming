package code.shubham.slidingwindow;

public class MinimumWindowSubstring {

    class Solution {
        public String minWindow(String s, String t) {
            int result = s.length() + 1, resultStart = 0, resultEnd = -1;
            int[] f = new int[58];
            for (int i = 0; i < t.length(); ++i)
                ++f[t.charAt(i) - 'A'];

            int start = 0, k = t.length();
            for (int end = 0; end < s.length(); ++end) {
                int c = s.charAt(end) - 'A';
                if (f[c]-- > 0)
                    --k;

                while (k == 0) {
                    if (end - start + 1 < result) {
                        resultStart = start;
                        resultEnd = end;
                        result = end - start + 1;
                    }
                    if (f[s.charAt(start++) - 'A']++ == 0)
                        ++k;
                }
            }

            return resultEnd == -1 ? "" : s.substring(resultStart, resultEnd + 1);
        }
    }

}
