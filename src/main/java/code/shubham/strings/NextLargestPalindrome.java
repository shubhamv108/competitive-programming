package code.shubham.strings;

public class NextLargestPalindrome {

    class Solution {
        public String nextPalindrome(String A) {
            int n = A.length();
            if (n == 1)
                return "";

            int midIdx = n / 2;
            String mid = (n & 1) == 1 ? "" + A.charAt(midIdx) : "";
            String f = A.substring(0, midIdx);
            StringBuilder sb = nextGreater(f);
            if (sb.length() == 0)
                return "";
            String first = sb.toString();
            String mirror = sb.reverse().toString();
            return first + mid + mirror;
        }

        StringBuilder nextGreater(String A) {
            char[] s = A.toCharArray();
            int n = s.length;
            int i = 0;
            int digit = -1;
            int[] f =  new int[10];
            ++f[s[n-1] - '0'];
            for (i = n-2; i >= 0; --i) {
                ++f[s[i] - '0'];
                if (s[i] < s[i+1]) {
                    digit = s[i] - '0';
                    break;
                }
            }

            if (digit == -1)
                return new StringBuilder();

            for (int j = digit + 1; j < 10; ++j) {
                if (f[j] > 0) {
                    --f[j];
                    s[i++] = (char) (j + '0');
                    break;
                }
            }

            for (int j = 0; j < 10; ++j)
                while (f[j]-- > 0)
                    s[i++] = (char) (j + '0');

            return new StringBuilder(new String(s));
        }
    }

    public static void main(String[] args) {
        System.out.println(new NextLargestPalindrome().new Solution().nextPalindrome("23143034132"));
    }

}
