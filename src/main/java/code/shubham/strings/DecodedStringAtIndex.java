package code.shubham.strings;

public class DecodedStringAtIndex {
    class Solution {
        public String decodeAtIndex(String s, int k) {
            int i;
            long len = 0;
            for (i = 0; len < k; i++)
                len = Character.isDigit(s.charAt(i)) ? len * (s.charAt(i) - '0') : len + 1;

            for (i--; i > 0; i--) {
                if (Character.isDigit(s.charAt(i))) {
                    len /= s.charAt(i) - '0';
                    k %= len;
                }
                else {
                    if (k % len == 0)
                        break;
                    len--;
                }
            }
            return Character.toString(s.charAt(i));
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new DecodedStringAtIndex().new Solution().decodeAtIndex("leet2code3", 16)
        );
    }
}
