package code.shubham.strings;

public class Largest3SameDigitNumberInString {
    class Solution {
        public String largestGoodInteger(String A) {
            char result = '/';
            for (int i = 2; i < A.length(); ++i)
                if (A.charAt(i) == A.charAt(i-1) && A.charAt(i) == A.charAt(i-2) && A.charAt(i) > result)
                    result = A.charAt(i);

            return result == '/' ? "" : "" + result + result + result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Largest3SameDigitNumberInString().new Solution().largestGoodInteger("6777133339"));
    }
}
