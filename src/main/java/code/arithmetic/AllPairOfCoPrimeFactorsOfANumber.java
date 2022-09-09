package code.arithmetic;

import java.util.ArrayList;

public class AllPairOfCoPrimeFactorsOfANumber {

    class Solution {
        ArrayList<int[]> get(int n) {
            ArrayList<int[]> result = new ArrayList<>();
            double sqrt = Math.sqrt(n);
            for (int i = 2; i <= sqrt; i++) {
                if (n % i == 0) {
                    while (n % i == 0)
                        n /= i;
                    if (n > 1)
                        result.add(new int[] { i, n });
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new AllPairOfCoPrimeFactorsOfANumber().new Solution().get(45)
        );
    }

}
