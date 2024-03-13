package code.shubham.strings;

public class MinimumDeletionsToMakeStringBalanced {
    class Solution {
        public int minimumDeletions(String s) {
            int bCount = 0, result = 0;
            for (char ch : s.toCharArray()) {
                if (ch == 'b')
                    ++bCount;
                else if (bCount > 0) {
                    ++result;
                    --bCount;
                }
            }

            return result;
        }
    }
}
