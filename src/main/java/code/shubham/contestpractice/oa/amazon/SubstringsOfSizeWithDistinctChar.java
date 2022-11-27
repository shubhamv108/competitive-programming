package code.shubham.contestpractice.oa.amazon;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class SubstringsOfSizeWithDistinctChar {

    class Solution {
        List<String> solve(String s, int k) {
            LinkedHashSet<String> result = new LinkedHashSet<>();
            int len = s.length();
            if (k > len) return result.stream().collect(Collectors.toList());
            char[] chrs = s.toCharArray();
            LinkedHashSet<Character> set = new LinkedHashSet<>();
            int start = 0, end = 0;
            while (end < len) {
                while (set.contains(chrs[end]))
                    set.remove(chrs[start++]);
                set.add(chrs[end++]);
                if (set.size() == k) {
                    result.add(s.substring(start, end));
                }
            }
            return result.stream().collect(Collectors.toList());
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new SubstringsOfSizeWithDistinctChar()
                    .new Solution()
                        .solve("abcabc", 3)
        );
    }

}
