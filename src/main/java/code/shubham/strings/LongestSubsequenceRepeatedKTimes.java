package code.shubham.strings;

import java.util.LinkedList;
import java.util.Queue;

public class LongestSubsequenceRepeatedKTimes {
    class Solution {
        public String longestSubsequenceRepeatedK(String S, int k) {
            int len = S.length();
            char[] s = S.toCharArray();
            String result = "";

            Queue<String> q = new LinkedList<>();
            q.offer("");

            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    String p = q.poll();
                    for (int i = 0; i < 26; i++) {
                        String next = p + (char) ('a' + i);
                        if (isKSub(s, next, k)) {
                            result = next;
                            q.offer(next);
                        }
                    }
                }
            }
            return result;
        }

        boolean isKSub(char[] s, String sub, int k) {
            int j = 0, repeat = 0;
            for (int i = 0; i < s.length; i++) {
                if (s[i] == sub.charAt(j)) {
                    j++;
                    if (j == sub.length()) {
                        repeat++;
                        if (repeat == k)
                            return true;
                        j = 0;
                    }
                }
            }
            return false;
        }
    }
}
