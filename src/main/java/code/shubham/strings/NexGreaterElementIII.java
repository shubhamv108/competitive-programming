package code.shubham.strings;

public class NexGreaterElementIII {
    class Solution {
        public int nextGreaterElement(int n) {
            char[] s = String.valueOf(n).toCharArray();
            int l = s.length;
            int[] f = new int[10];
            ++f[s[l-1] - '0'];
            int digit = -1;
            int i = 0;
            for (i = l - 2; i >= 0; --i) {
                ++f[s[i] - '0'];
                if (s[i] < s[i+1]) {
                    digit = s[i] - '0';
                    break;
                }
            }

            if (digit == -1)
                return -1;

            for (int j = digit + 1; j < 10; ++j) {
                if (f[j] > 0) {
                    --f[j];
                    s[i++] = (char) (j + '0');
                    break;
                }
            }

            for (int j = 0; j < 10; ++j) {
                while (f[j]-- > 0) {
                    s[i++] = (char) (j + '0');
                }
            }

            String result = new String(s);
            return Long.valueOf(result) > Integer.MAX_VALUE ? -1 : Integer.valueOf(result);

        }
    }

    public static void main(String[] args) {
        System.out.println(new NexGreaterElementIII().new Solution().nextGreaterElement(12));
    }
}
