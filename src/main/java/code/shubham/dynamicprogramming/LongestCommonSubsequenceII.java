package code.shubham.dynamicprogramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestCommonSubsequenceII {

    class Solution {
        public int findLUSlength(String[] strs) {
            Arrays.sort(strs, (a, b) -> b.length() - a.length());

            Set<String> duplicates = getDuplicates(strs);
            for(int i = 0; i < strs.length; i++) {
                if(!duplicates.contains(strs[i])) {
                    if(i == 0) return strs[0].length();
                    for(int j = 0; j < i; j++) {
                        if(this.isSubsequence(strs[j], strs[i])) break;
                        if(j == i-1) return strs[i].length();
                    }
                }
            }
            return -1;
        }

        public boolean isSubsequence(String a, String b) {
            int i = 0, j = 0;
            while(i < a.length() && j < b.length()) {
                if(a.charAt(i) == b.charAt(j)) j++;
                i++;
            }
            return j == b.length();
        }

        private Set<String> getDuplicates(String[] strs) {
            Set<String> set = new HashSet<String>();
            Set<String> duplicates = new HashSet<String>();
            for(String s : strs) {
                if(set.contains(s)) duplicates.add(s);
                set.add(s);
            }
            return duplicates;
        }
    }

    class Solution2 {
        public int findLUSlength(String[] strs) {
            int res = -1, n = strs.length;
            for (int i=0; i<n; i++) {
                if (strs[i].length() < res) continue;
                int j = -1;
                while(++j < n)
                    if (i != j && this.isSubsequence(strs[i], strs[j]))
                        break;
                if (j == n) res = Math.max(res, strs[i].length());
            }
            return res;
        }
        public boolean isSubsequence(String a, String b){
            int i = 0, j = 0;
            while(i < a.length() && j < b.length())
                if (a.charAt(i) == b.charAt(j++))
                    i++;
            return i == a.length();
        }
    }

    public static void main(String[] args) {

    }

}
