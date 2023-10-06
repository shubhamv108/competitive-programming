package code.shubham.numbers;

public class BaseToDecimal {
    class Solution {
        int solve(int n, int base) {
            int mul = 1, result = 0;
            while (n > 0) {
                int d = n % 10;
                n /= 10;

                result += d * mul;
                mul *= base;
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new BaseToDecimal().new Solution().solve(112, 3)
        );
    }
}
