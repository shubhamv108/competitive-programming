package code.shubham.strings;

public class PasswordStrength {
    class Solution {
        public int passwordStrength(String A) {
            boolean[] f = new boolean[128];
            int n = A.length();
            for (int i = 0; i < n; ++i) {
                int ch = A.charAt(i);
                f[ch] = true;
            }

            int result = 0;
            for (int i = 0; i < 128; ++i) {
                if (f[i]) {
                    if      (i > 64 && i < 91)  result += 2;
                    else if (i > 96 && i < 123) ++result;
                    else if (i > 47 && i < 58)  result += 3;
                    else                        result += 5;
                }
            }
            return result;
        }
    }

    static void main() {
        System.out.println(new PasswordStrength().new Solution().passwordStrength("bbB11#"));
    }
}
