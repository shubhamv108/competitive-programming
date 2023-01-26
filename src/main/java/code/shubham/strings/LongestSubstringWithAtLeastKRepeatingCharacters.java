package code.shubham.strings;

import java.util.HashSet;

public class LongestSubstringWithAtLeastKRepeatingCharacters {
    class Solution {
        public int longestSubstring(String S, int k) {
            char[] s = S.toCharArray();
            int result = 0, freq[] = new int[26];

            for (int i = 0; i < s.length; i++) {
                fill(freq, 0);
                for (int j = i; j < s.length; j++) {
                    freq[s[j] - 'a']++;
                    if (freq[(int) (s[j] - 'a')] >= k && isAllGreater(freq, k))
                        result = Math.max(result, j - i + 1);
                }
            }

            return result;
        }

        void fill(int[] A, int v) {
            for (int i = 0; i < A.length; i++)
                A[i] = v;
        }

        boolean isAllGreater(int[] A, int k) {
            for (int a : A)
                if (a != 0 && a < k)
                    return false;

            return true;
        }
    }

    class Solution1 {
        public int longestSubstring(String S, int k) {
            char[] s = S.toCharArray();
            int result = 0, freq[] = new int[26], maxUnique = getMaxUniqueLetters(s);
            HashSet<Character> uniq = new HashSet<>();

            for (int curUniq = 0; curUniq <= maxUnique; curUniq++) {
                fill(freq, 0);
                uniq.clear();

                int l = 0, r = 0;
                while (r < s.length) {
                    uniq.add(s[r]);
                    freq[s[r] - 'a']++;

                    while (uniq.size() > curUniq) {
                        freq[s[l] - 'a']--;
                        if (freq[s[l] - 'a'] == 0)
                            uniq.remove(s[l]);
                        l++;
                    }

                    if (uniq.size() == curUniq && freq[s[r] - 'a'] >= k && isAllGreater(freq, k))
                        result = Math.max(result, r - l + 1);

                    r++;
                }

            }

            return result;
        }

        void fill(int[] A, int v) {
            for (int i = 0; i < A.length; i++)
                A[i] = v;
        }

        boolean isAllGreater(int[] A, int k) {
            for (int a : A)
                if (a != 0 && a < k)
                    return false;

            return true;
        }

        int getMaxUniqueLetters(char[] s) {
            boolean map[] = new boolean[26];
            int maxUnique = 0;
            for (int i = 0; i < s.length; i++) {
                if (!map[s[i] - 'a']) {
                    maxUnique++;
                    map[s[i] - 'a'] = true;
                }
            }
            return maxUnique;
        }
    }
}
