package code.shubham.arithmetic;

import java.util.ArrayList;

public class AllPrimeFactorsOfANumber {

    class Solution {
        ArrayList<Integer> get(int n) {
            ArrayList<Integer> result = new ArrayList<>();
            while ((n & 1) == 0) {
                result.add(2);
                n /= 2;
            }
            double sqrt = Math.sqrt(n);
            for (int i = 3; i <= sqrt; i += 2) {
                while (n % i == 0) {
                    result.add(i);
                    n /= i;
                }
            }
            if (n > 2) result.add(n);
            return result;
        }
    }

    class Solution2 {
        int maxNumbers;
        int[] spf;
        Solution2(int maxNumbers) {
            this.maxNumbers = maxNumbers;
            this.spf = new int[this.maxNumbers];
            this.populateSmallestPrimeFactors();
        }
        void populateSmallestPrimeFactors() {
            this.spf[1] = 1;
            for (int i = 2; i < this.maxNumbers; i++)
                if ((i & 1) == 0) this.spf[i] = 2;
                else this.spf[i] = i;
            for (int i = 3; i * i < this.maxNumbers; i++)
                if (this.spf[i] == i)
                    for (int j = i*i; j < this.maxNumbers; j += i)
                        if (this.spf[j] == j)
                            this.spf[j] = i;
        }

        ArrayList<Integer> get(int n) {
            ArrayList<Integer> result = new ArrayList<>();
            while (n != 1) {
                result.add(this.spf[n]);
                n /= spf[n];
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new AllPrimeFactorsOfANumber().new Solution2(10000001).get(100)
        );
    }

}
