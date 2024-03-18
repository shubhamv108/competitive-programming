package code.shubham.strings;

public class    AddStrings {
    class Solution {
        public String addStrings(String a, String b) {
            StringBuilder result = new StringBuilder();
            int ai = a.length() - 1, bi = b.length() - 1, carry = 0;
            while (ai > -1 || bi > -1) {
                int av = ai < 0 ? 0 : (a.charAt(ai--) - 48);
                int bv = bi < 0 ? 0 : (b.charAt(bi--) - 48);
                int sum = av + bv + carry;
                carry = sum / 10;
                result.append(sum % 10);
            }

            if (carry > 0)
                result.append(carry);

            return result.reverse().toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new AddStrings().new Solution().addStrings("1234", "1234")
        );
    }
}
