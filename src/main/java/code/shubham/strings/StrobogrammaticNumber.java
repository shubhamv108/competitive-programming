package code.shubham.strings;

import java.util.HashMap;

public class StrobogrammaticNumber {
    public class Solution {
        public boolean isStrobogrammatic(String A) {
            HashMap<Character, Character> m = new HashMap<>() {{
                put('0', '0');
                put('1', '1');
                put('6', '9');
                put('9', '6');
                put('8', '8');
            }};
            int l = 0, r = A.length() - 1;
            Character c;
            while (l <= r) {
                c = m.get(A.charAt(l++));
                if (c == null || c != A.charAt(r--))
                    return false;
            }

            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new StrobogrammaticNumber().new Solution().isStrobogrammatic("7")
        );
    }
}
