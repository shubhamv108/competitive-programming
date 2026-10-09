package code.shubham.strings.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class BraceExpressionII {
    class Solution {
        String A;
        int ai = 0, n = 0;

        public List<String> braceExpansionII(String A) {
            this.n = A.length();
            this.A = A;
            return new ArrayList<>(parseString());
        }

        TreeSet<String> parseString() {
            TreeSet<String> result = new TreeSet<>();
            for (; ai < n; ++ai) {
                char c = A.charAt(ai);
                if (c == ',' || c == '}')
                    return result;
                if (c == '{') {
                    TreeSet<String> options = parseBrace();
                    if (!options.isEmpty()) {
                        if (result.isEmpty())
                            result.add("");
                        TreeSet<String> newResult = new TreeSet<>();
                        for (String r : result)
                            for (String o : options)
                                newResult.add(r + o);
                        result = newResult;
                    }
                } else {
                    if (result.isEmpty())
                        result.add("" + c);
                    else {
                        TreeSet<String> newResult = new TreeSet<>();
                        for (String r : result)
                            newResult.add(r + c);
                        result = newResult;
                    }
                }
            }
            return result;
        }

        TreeSet<String> parseBrace() {
            ++ai;
            TreeSet<String> options = new TreeSet<>();
            for (; ai < n; ++ai) {
                options.addAll(parseString());
                char c = A.charAt(ai);
                if (c == '}')
                    break;
            }
            return options;
        }
    }

    void main(String[] args) {
        System.out.println(new Solution().braceExpansionII("abc"));
        System.out.println(new Solution().braceExpansionII("{abc}"));
        System.out.println(new Solution().braceExpansionII("a{abc}"));
        System.out.println(new Solution().braceExpansionII("a{a{b,c}}"));
        System.out.println(new Solution().braceExpansionII("{abc}a"));
        System.out.println(new Solution().braceExpansionII("a{a{b,c},a}"));
    }
}
