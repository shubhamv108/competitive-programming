package code.shubham.contestpractice.oa.microsoft;

import java.util.HashSet;

public class MinDeletionsToMakeFreqOfCharacterUnique {

    class Solution {
        int solve(String s) {
            char[] chrs = s.toCharArray();
            int[] freq = new int[26];
            for (char c : chrs)
                freq[c - 'a']++;

            int result = 0;
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < freq.length; i++) {
                while (freq[i] > 0 && set.contains(freq[i])) {
                    freq[i]--;
                    result++;
                }
                if (freq[i] > 0)
                    set.add(freq[i]);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        MinDeletionsToMakeFreqOfCharacterUnique minDeletionsToMakeFreqOfCharacterUnique = new MinDeletionsToMakeFreqOfCharacterUnique();
        Solution solution = minDeletionsToMakeFreqOfCharacterUnique.new Solution();
        System.out.println(solution.solve("abcabc"));
    }

}
