package code.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MaximumNumberOfOccurrencesOfASubstring {
    class Solution {
        public int maxFreq(String a, int maxLetters, int minSize, int maxSize) {
            char[] A = a.toCharArray();
            int len = A.length;
            HashMap<String, Integer> freq = new HashMap<>();
            HashSet<Character> unique = new HashSet<>();
            for (int i = 0; i < len; i++) {
                StringBuilder s = new StringBuilder();
                unique.clear();
                for (int j = i; j < len; j++) {
                    s.append(A[j]);
                    unique.add(A[j]);

                    if (unique.size() > maxLetters || s.length() > maxSize)
                        break;

                    if (s.length() < minSize)
                        continue;

                    String S = s.toString();
                    freq.put(S, freq.getOrDefault(S, 0) + 1);
                }
            }

            int result = 0;
            for (Map.Entry<String, Integer> e: freq.entrySet()) {
                result = Math.max(result, e.getValue());
            }

            return result;
        }
    }
}
