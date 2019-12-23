package code.math;

public class ExcelColumnTitle {
    public class Solution {
        public String convertToTitle(int A) {
            StringBuilder result = new StringBuilder();
            while (A > 0) {
                int r = A%26;
                if (r == 0) { r = 26; A = (A/26) - 1;} else { A /= 26; }
                result.append((char)('A' + (r - 1)));
            }
            return result.reverse().toString();
        }
    }

}
