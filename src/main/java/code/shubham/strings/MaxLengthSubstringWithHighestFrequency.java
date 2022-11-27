package code.shubham.strings;

import java.util.HashMap;
import java.util.Map;

public class MaxLengthSubstringWithHighestFrequency {
    class Solution {
        String solve(String A) {
            return solve(A.toCharArray());
        }

        String solve(char[] A) {

            int len = A.length;

            Map<String, Integer> freq = new HashMap<>();

            for (int i = 0; i < len; i++) {
                String s = "";
                for (int j = i; j < len; j++) {
                    s += A[j];
                    freq.put(s, freq.getOrDefault(s, 0) + 1);
                }
            }

            int maxFreq = 0;

            String result = "";
            for (Map.Entry<String, Integer> entry : freq.entrySet()) {
                if (entry.getValue() > maxFreq) {
                    maxFreq = entry.getValue();
                    result = entry.getKey();
                } else if (entry.getValue() == maxFreq) {
                    String ss = entry.getKey();
                    if (ss.length() > result.length())
                        result = ss;
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        MaxLengthSubstringWithHighestFrequency obj = new MaxLengthSubstringWithHighestFrequency();
        Solution solution = obj.new Solution();
        System.out.println(
                solution.solve("abcabc")
        );
    }
}
