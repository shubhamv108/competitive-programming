package code.shubham.strings;

import java.util.HashMap;

public class WordPattern {
    class Solution {
        public boolean wordPattern(String pattern, String s) {
            String[] words = s.split("\\s");
            Integer[] alphabets = new Integer[26];
            HashMap<String, Integer> m = new HashMap<>();

            Integer curAlpha, existingAlpha;
            if (pattern.length() != words.length)
                return false;

            for (int i = 0; i < words.length; i++) {
                curAlpha = pattern.charAt(i) - 'a';
                existingAlpha = m.get(words[i]);
                if (alphabets[curAlpha] == null && existingAlpha == null) {
                    alphabets[curAlpha] = i;
                    m.put(words[i], curAlpha);
                    continue;
                }

                if (alphabets[curAlpha] == null || !words[i].equals(words[alphabets[curAlpha]]))
                    return false;

                if (existingAlpha == null || existingAlpha != curAlpha)
                    return false;

            }

            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(new WordPattern().new Solution().wordPattern("abba", "dog cat cat dog"));
    }
}
