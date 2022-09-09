package code.strings;

public class ValidPalindrome {
    class Solution {
        public boolean isPalindrome(String s) {
            char[] chrs = s.toCharArray();
            int l = 0, r = chrs.length - 1, left, right;

            while (l < r) {
                left = toLowerCase(chrs[l]);
                while (left < 97 || left > 122)
                    left = toLowerCase(chrs[++l]);

                right = toLowerCase(chrs[r]);
                while (right < 97 || right > 122)
                    right = toLowerCase(chrs[--r]);

                if (left != right)
                    return false;
                l++;
                r--;
            }
            return true;
        }

        int toLowerCase(int ch) {
            if (ch < 97)
                return ch + 32;
            return ch;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new ValidPalindrome().new Solution().isPalindrome("A man, a plan, a canal: Panama")
        );
    }
}
