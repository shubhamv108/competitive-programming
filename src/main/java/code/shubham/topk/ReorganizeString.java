package code.shubham.topk;

public class ReorganizeString {
    class Solution {
        public String reorganizeString(String s) {
            int[] f = new int[26];
            for (int i = 0; i < s.length(); ++i)
                ++f[s.charAt(i) - 97];

            int max = f[0], ch = 0;
            for (int i = 1; i < 26; ++i)
                if (f[i] > max) {
                    max = f[i];
                    ch = i;
                }

            if (max > (s.length() + 1) / 2)
                return "";


            char[] result = new char[s.length()];
            int ri = 0;

            char chr = (char) (ch + 'a');
            while (f[ch]-- > 0) {
                result[ri] = chr;
                ri += 2;
            }

            for (int i = 0; i < 26; ++i) {
                while (f[i]-- > 0) {
                    if (ri >= s.length())
                        ri = 1;
                    result[ri] = (char) (i + 97);
                    ri += 2;
                }
            }


            return String.valueOf(result);
        }
    }
}
