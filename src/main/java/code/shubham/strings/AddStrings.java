package code.shubham.strings;

public class AddStrings {
    class Solution {
        public String addStrings(String a, String b) {
            StringBuilder result = new StringBuilder();
            int ai = a.length(), bi = b.length(), carry = 0;
            while (ai > 0 || bi > 0) {
                ai--;
                bi--;

                int sum = carry;
                if (ai > -1)
                    sum += (a.charAt(ai) - 48);
                if (bi > -1)
                    sum += (b.charAt(bi) - 48);

                carry = sum / 10;
                result.append((char) ((sum  % 10) + 48));
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
