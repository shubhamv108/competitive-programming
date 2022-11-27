package code.shubham.contestpractice.oa.microsoft;

import java.util.HashSet;

public class LargestAlphabeticCharacterInString {
    class Solution {
        String solve(String s) {
            if (s == null || s.isEmpty())
                return "-1";
            int result = 64;
            HashSet<Integer> present = new HashSet<>();
            char[] chrs = s.toCharArray();
            for (char c : chrs) {
                if (c < 91 && present.contains(c + 32))
                    result = Math.max(result, c);
                present.add((int) c);
            }
            return result == 64 ? "-1" : "" + (char) result;
        }
    }

    public static void main(String[] args) {
        LargestAlphabeticCharacterInString object = new LargestAlphabeticCharacterInString();
        Solution solution = object.new Solution();
        String result = solution.solve("admeDCAB");
        System.out.println(result);
    }
}
