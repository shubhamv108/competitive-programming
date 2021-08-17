package code.strings;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindownSubstring {

    class Solution {
        public String minWindow(String s, String t) {
            int sl = s.length();
            int tl = t.length();
            char[] cs = s.toCharArray();
            char[] ts = t.toCharArray();
            Map<Character, Integer> m = new HashMap<>();
            for (int i = 0; i < tl; i++) {
                m.put(ts[i], m.getOrDefault(ts[i], 0) + 1);
            }

            int start = 0, resultStart = 0, resultEnd = sl-1;
            Map<Character, Integer> sm = new HashMap<>(m);
            Map<Character, Integer> temp = new HashMap<>();
            boolean isFound = false;
            for (int i = 0; i < sl; i++) {
                if (m.containsKey(cs[i])) {
                    Integer c = sm.get(cs[i]);
                    if (c == null) temp.put(cs[i], temp.getOrDefault(cs[i], 0) + 1);
                    else if (c == 1) sm.remove(cs[i]);
                    else sm.put(cs[i], sm.get(cs[i]) - 1);
                }
                while (sm.isEmpty() && start <= i) {
                    isFound = true;
                    if ((i - start + 1) < (resultEnd - resultStart + 1)) {
                        resultEnd = i;
                        resultStart = start;
                    }
                    if (m.containsKey(cs[start])) {
                        sm.put(cs[start], sm.getOrDefault(cs[start], 0) + 1);
                        if (temp.containsKey(cs[start])) {
                            temp.put(cs[start], temp.get(cs[start]) - 1);
                            if (temp.get(cs[start]) == 0) temp.remove(cs[start]);
                            sm.remove(cs[start]);
                        }
                    }
                    start++;
                }
            }
            return isFound ? s.substring(resultStart == 0 ? resultStart : resultStart, resultEnd + 1) : "";
        }
    }

    class Solution2 {
        public String minWindow(String s, String t) {
            char[] sArray = s.toCharArray();
            char[] tArray = t.toCharArray();
            int[] map = new int[256];
            int right = 0,
                left = 0,
                minLeft = 0,
                minLength = Integer.MAX_VALUE,
                count = tArray.length;
            for(int i = 0; i < tArray.length; i++) map[tArray[i]] ++;
            while (right < sArray.length) {
                if (map[sArray[right]] > 0) count--;
                map[sArray[right]]--;
                while (count == 0) {
                    if ((right - left + 1) < minLength) {
                        minLength = right - left + 1;
                        minLeft = left;
                    }
                    map[sArray[left]] ++;
                    if (map[sArray[left]] > 0) count ++;
                    left++;
                }
                right++;
            }

            if( minLeft + minLength > sArray.length)
                return "";

            return s.substring(minLeft, minLeft + minLength);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MinimumWindownSubstring().new Solution().minWindow("ab", "b")
        );
    }

}
