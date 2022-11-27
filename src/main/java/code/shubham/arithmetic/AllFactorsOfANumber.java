package code.shubham.arithmetic;

import java.util.ArrayList;

public class AllFactorsOfANumber {

    class Solution {
        ArrayList<Integer> get(int n) {
            ArrayList<Integer> result = new ArrayList<>();
            double sqrt = Math.sqrt(n);
            for (int i = 1; i <= sqrt; i++) {
                if (n % i == 0) {
                    int divisor = n / i;
                    if (divisor == i) result.add(i);
                    else {
                        result.add(divisor);
                        result.add(i);
                    }
                }
            }
            return result;
        }
    }

    class Solution2 {
        ArrayList<Integer> get(int n) {
            ArrayList<Integer> result = new ArrayList<>();
            double sqrt = Math.sqrt(n);
            for (int i = 1; i < sqrt; i++)
                if (n % i == 0)
                   result.add(i);
            for (int i = (int) sqrt; i > 0; i--)
                if (n % i == 0)
                    result.add(n / i);
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new AllFactorsOfANumber().new Solution2().get(100)
        );
    }

}
