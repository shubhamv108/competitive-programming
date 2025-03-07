package code.shubham.slidingwindow;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInAString {

    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            ArrayList<Integer> result = new ArrayList<>();

            if (s.length() < p.length())
                return result;

            int[] f1 = new int[26], f2 = new int[26];
            for (int i = 0; i < p.length(); ++i) {
                ++f1[p.charAt(i) - 'a'];
                ++f2[s.charAt(i) - 'a'];
            }

            if (equal(f1, f2))
                result.add(0);

            for (int end = p.length(); end < s.length(); ++end) {
                --f2[s.charAt(end - p.length()) - 'a'];
                ++f2[s.charAt(end) - 'a'];
                if (equal(f1, f2))
                    result.add(end - p.length() + 1);
            }

            return result;
        }

        boolean equal(int[] A, int[] B) {
            for (int i = 0; i < 26; ++i)
                if (A[i] != B[i])
                    return false;
            return true;
        }
    }

    class Solution2 {
        public List<Integer> findAnagrams(String s, String p) {
            var result = new ArrayList<Integer>();
            int np = p.length(), ns = s.length();
            if (np > ns)
                return result;

            int[] f = new int[26];
            for (int i = 0; i < np; ++i){
                ++f[p.charAt(i) - 'a'];
                --f[s.charAt(i) - 'a'];
            }

            int diffCount = 0;
            for(int diff : f)
                if(diff > 0)
                    diffCount++;

            if (diffCount == 0)
                result.add(0);

            for (int i = 0, n = ns - np; i < n; ++i) {
                if (++f[s.charAt(i) - 'a'] == 1)
                    ++diffCount;

                if (--f[s.charAt(i + np) - 'a'] == 0)
                    --diffCount;

                if (diffCount == 0)
                    result.add(i+1);
            }
            return result;
        }
    }
}
