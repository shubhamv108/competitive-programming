package code.shubham.slidingwindow;

public interface PermutationInString {

    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            if (s2.length() < s1.length())
                return false;

            int[] f1 = new int[26];
            int[] f2 = new int[26];

            int i = 0;
            for (; i < s1.length(); ++i) {
                ++f1[s1.charAt(i) - 'a'];
                ++f2[s2.charAt(i) - 'a'];
            }

            if (equals(f1, f2))
                return true;

            for (; i < s2.length(); ++i) {
                --f2[s2.charAt(i - s1.length()) - 'a'];
                ++f2[s2.charAt(i) - 'a'];
                if (equals(f1, f2))
                    return true;
            }

            return false;
        }

        boolean equals(int[] a, int[] b) {
            for (int i = 0; i < 26; ++i)
                if (a[i] != b[i])
                    return false;
            return true;
        }
    }

}
