package code.strings;

public class ZigZagString {

    private class Solution {
        private String convert(String A, int B) {
            if(B == 1) return A;
            StringBuilder result = new StringBuilder();
            int n = B + B - 2;
            for(int i = 0; i < B; i++) {
                int cur = i;
                while(cur < A.length()) {
                    result.append(A.charAt(cur));
                    cur += n;
                    if(i > 0 && i < B - 1 && (cur - i - i) < A.length())
                        result.append(A.charAt(cur - i - i));
                }
            }
            return result.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new ZigZagString().
                        new Solution().
                        convert("PAYPALISHIRING", 3)
        );
    }

}
