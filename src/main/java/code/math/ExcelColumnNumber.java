package code.math;

public class ExcelColumnNumber {
    public class Solution {
        public int titleToNumber(String A) {
            int result = 0;
            char[] a = A.toCharArray();
            long sq = 1;
            for (int i = A.length() - 1; i > -1; i--) {
                result += sq * (int) (a[i] - 'A' + 1);
                sq *= 26;
            }
            return result;
        }
    }

}
