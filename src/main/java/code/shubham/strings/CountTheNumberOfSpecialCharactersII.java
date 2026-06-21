package code.shubham.strings;

public class CountTheNumberOfSpecialCharactersII {
    class Solution {
        public int numberOfSpecialChars(String A) {
            int n = A.length();

            Integer[][] pos = new Integer[26][2];
            for (int i = 0; i < n; ++i) {
                int ch = A.charAt(i);
                if (ch > 64 && ch < 91) {
                    ch -= 'A';
                    if (pos[ch][1] == null)
                        pos[ch][1] = i;
                } else
                    pos[ch - 'a'][0] = i;
            }

            int result = 0;
            for (int i = 0; i < 26; ++i)
                if (pos[i][0] != null && pos[i][1] != null && pos[i][0] < pos[i][1])
                    ++result;

            return result;
        }
    }

    static void main() {
        System.out.println(new CountTheNumberOfSpecialCharactersII().new Solution().numberOfSpecialChars("aaAbcBC"));
    }
}
