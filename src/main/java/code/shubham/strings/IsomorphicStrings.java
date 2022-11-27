package code.shubham.strings;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {

    class Solution1 {
        public boolean isIsomorphic(String s, String t) {
            Map<Character, Character> ch = new HashMap<>();
            Map<Character, Character> mappedTo = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char a = s.charAt(i); // r
                char b = t.charAt(i); // e
                Character c = ch.get(a); // null
                Character m = mappedTo.get(b); // null
                if (m != null && m != a) {
                    return false;
                }
                if (c != null) {
                    if (c != b) {
                        return false;
                    }
                } else {
                    ch.put(a, b);
                    mappedTo.put(b, a);
                }
            }
            return true;
        }
    }

    class Solution2 {
        public boolean isIsomorphic(String s1, String s2) {
            char[] a = s1.toCharArray();
            char[] b = s2.toCharArray();
            int[] m = new int[512];
            for (int i = 0; i < s1.length(); i++) {
                if (m[a[i]] != m[b[i]+256]) return false;
                m[a[i]] = m[b[i]+256] = i+1;
            }
            return true;
        }
    }


    public static void main(String[] args) {
        System.out.println(
                new IsomorphicStrings().new Solution2().isIsomorphic("egg", "add")
        );
    }

}
