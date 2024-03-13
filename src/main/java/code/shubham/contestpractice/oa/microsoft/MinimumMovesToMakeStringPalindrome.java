package code.shubham.contestpractice.oa.microsoft;

public class MinimumMovesToMakeStringPalindrome {

    class Solution {
        public int minMovesToMakePalindrome(String S) {
            int result = 0;
            char[] s = S.toCharArray();
            int l = 0, r = s.length - 1;
            while (l < r) {
                int high = r;
                if (s[l] == s[r]) {
                    ++l;
                    --r;
                } else {
                    while(s[l] != s[high])
                        --high;

                    if (l == high) {
                        swap(s, high, high + 1);
                        ++result;
                    } else {
                        while (high < r) {
                            swap(s, high, high + 1);
                            ++result;
                            ++high;
                        }
                    }
                }
            }

            return result;
        }

        void swap(char[] ch, int i, int j) {
            char t = ch[i];
            ch[i] = ch[j];
            ch[j] = t;
        }
    }

}
