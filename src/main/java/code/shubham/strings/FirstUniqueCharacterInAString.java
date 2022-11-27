package code.shubham.strings;

import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;

public class FirstUniqueCharacterInAString {

    class Solution {
        public int firstUniqChar(String s) {
            LinkedHashMap<Integer, Integer> a = new LinkedHashMap<>();
            BitSet set = new BitSet(26);
            char[] chrs = s.toCharArray();
            for (int i = 0; i < s.length(); i++) {
                int c = chrs[i]-'a';
                if (a.containsKey(c)) a.remove(c);
                else if (!set.get(c)) {
                    set.set(c);
                    a.put(c, i);
                }
            }
            return a.entrySet().stream().findFirst().map(Map.Entry::getValue).orElse(-1);
        }
    }

    class Solution2 {
        public int firstUniqChar(String s) {
            LinkedHashMap<Integer, Integer> a = new LinkedHashMap<>();
            int set = 0;
            char[] chrs = s.toCharArray();
            for (int i = 0; i < s.length(); i++) {
                int c = chrs[i]-'a';
                if (a.containsKey(c)) a.remove(c);
                else if ((set & (1 << c)) == 0) {
                    set |= 1 << c;
                    a.put(c, i);
                }
            }
            return a.entrySet().stream().findFirst().map(Map.Entry::getValue).orElse(-1);
        }
    }

    class Solution3 {
        public int firstUniqChar(String s) {
            char[] chrs = s.toCharArray();
            int len = chrs.length;
            s.lastIndexOf(0);
            Integer[] a = new Integer[26];
            for (int i = 0; i < len; i++) {
                int c = chrs[i] - 'a';
                if (a[c] == null) a[c] = i;
                else a[c] = -1;
            }
            return Arrays.stream(a)
                    .filter(Objects::nonNull)
                    .filter(e -> e != -1)
                    .mapToInt(e -> e)
                    .min()
                    .orElse(-1);
        }
    }

}
