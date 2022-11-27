package code.shubham.contestpractice.oa.swiggy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FlexibleStrings {
    class Solution {
        boolean canBeConverted(String s, char[][] opts) {
            Map<Character, HashSet<Character>> operations = new HashMap<>();
            for (char[] operation : opts) {
                operations.putIfAbsent(operation[0], new HashSet<>());
                operations.get(operation[0]).add(operation[1]);
            }

            HashMap<Character, HashSet<Character>> convert = new HashMap<>();
            for (char[] operation : opts)
                visit(operation[0], operations, convert);

            char[] chrs = s.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                boolean flag = false;
                for (char ch : chrs) {
                    HashSet set = convert.get(ch);
                    if (ch != c && (set == null || !set.contains(c))) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) return true;
            }
            return false;
        }

        Set<Character> visit(char ch, Map<Character, HashSet<Character>> operations, HashMap<Character, HashSet<Character>> convert) {
            if (convert.get(ch) != null) return convert.get(ch);
            convert.put(ch, new HashSet<>());
            HashSet<Character> children = operations.get(ch);
            if (children != null) {
                for (char child : children) {
                    convert.get(ch).add(child);
                    convert.get(ch).addAll(visit(child, operations, convert));
                }
            }
            return convert.get(ch);
        }
    }

    public static void main(String[] args) {
        FlexibleStrings flexibleStrings = new FlexibleStrings();
        Solution solution = flexibleStrings.new Solution();
        System.out.println(solution.canBeConverted("abcdc", new char[][] {
                {'b', 'c'},
                {'a', 'b'},
                {'c', 'd'}
        }));
    }
}
