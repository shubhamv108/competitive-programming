package code.dynamicprogramming;

import java.util.HashSet;
import java.util.Set;

public class LongestUnCommonSubsequenceI {

    class Solution {
        public int findLUSlength(String a, String b) {
            Set<String> p = new HashSet<>();
            Set<String> q = new HashSet<>();
            Set<String> r = new HashSet<>();
            int l1 = a.length();
            int l2 = b.length();
            char[] x = a.toCharArray();
            char[] y = b.toCharArray();
            p.add("");
            for (int i = l1-1; i > -1; i--) {
                Set<String> temp = new HashSet<>();
                for (String t : p) temp.add(x[i] + t);
                p.addAll(temp);
            }

            q.add("");
            for (int i = l2-1; i > -1; i--) {
                Set<String> temp = new HashSet<>();
                for (String t : q) {
                    String w = y[i] + t;
                    if (p.contains(w)) r.add(w);
                    temp.add(w);
                }
                q.addAll(temp);
            }

            r.add("");
            p.removeAll(r);
            q.removeAll(r);
            Integer result = p.stream().mapToInt(String::length).max().orElse(-1);
            return Math.max(result, q.stream().mapToInt(String::length).max().orElse(-1));
        }
    }

    class Solution2 {
        public int findLUSlength(String a, String b) {
            return a.equals(b) ? -1 : Math.max(a.length(), b.length());
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new LongestUnCommonSubsequenceI()
                .new Solution()
                .findLUSlength("aaa", "aaa")
        );
    }

}
