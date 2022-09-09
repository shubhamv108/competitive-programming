package code.strings;

public class ReverseOnlyLetters {
    class Solution {
        public String reverseOnlyLetters(String s) {
            char[] chrs = s.toCharArray();
            int l = 0, r = chrs.length - 1;
            while (l < r) {
                while (l < r && !isAlpha(chrs[l]))
                    l++;
                while (l < r && !isAlpha(chrs[r]))
                    r--;
                if (l > r)
                    break;
                swap(chrs, l, r);
                l++;
                r--;
            }
            return new String(chrs);
        }

        boolean isAlpha(int a) {
            return (a > 64 && a < 91) || (a > 96 && a < 123);
        }

        void swap(char[] A, int x, int y) {
            char temp = A[x];
            A[x] = A[y];
            A[y] = temp;
        }
    }
}
