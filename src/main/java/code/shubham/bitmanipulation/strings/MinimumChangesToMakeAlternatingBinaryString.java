package code.shubham.bitmanipulation.strings;

public class MinimumChangesToMakeAlternatingBinaryString {
    class Solution {
        public int minOperations(String s) {
            int result = 0, n = s.length();
            for (int i = 0, even = 0; i < n; ++i, even = Math.abs(even - 1))
                if (s.charAt(i) - '0' != even)
                    ++result;
            return Math.min(result, n - result);
        }
    }
}
