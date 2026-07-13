package code.shubham.strings;

public class StringCompression {
    class Solution {
        public int compress(char[] A) {
            int n = A.length, result = 0;
            char prev = ' ';
            int c = 0;
            for (char a : A) {
                if (a != prev) {
                    if (c == 1)
                        ++result;
                    else if (c > 0)
                        result += ((int) Math.log10(c) + 1) + 1;
                    c = 1;
                    prev = a;
                } else
                    ++c;
            }
            if (c == 1)
                ++result;
            else
                result += ((int) Math.log10(c) + 1) + 1;
            return result;
        }
    }

    void main() {
        System.out.println(new Solution().compress(new char[] { 'a','a','b','b','c','c','c' }));
        System.out.println(new Solution().compress(new char[] { 'a' }));
        System.out.println(new Solution().compress(new char[] { 'a','b','b','b','b','b','b','b','b','b','b','b','b' }));
    }
}
