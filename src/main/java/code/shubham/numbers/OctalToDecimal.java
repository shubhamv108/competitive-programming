package code.shubham.numbers;

public class OctalToDecimal {

    class Solution {
        int solve(int octal) {
            int base = 1, result = 0;
            while (octal > 0) {
                int d = octal % 10;
                octal /= 10;

                result += d * base;
                base *= 8;
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new OctalToDecimal().new Solution().solve(67)
        );
    }

}
