package code.shubham.arithmetic;

public class IsPrime {
    class Solution {
        boolean solve(int n) {
            if (n == 0 || n == 1 || (n & 1) == 0)
                return false;

            for (int i = 5; i * i <= n; i += 2)
                if (n % i == 0)
                    return false;
            return true;
        }
    }


    public static void main(String[] args) {
        System.out.println(
            new IsPrime().new Solution().solve(2)
        );
    }
}
