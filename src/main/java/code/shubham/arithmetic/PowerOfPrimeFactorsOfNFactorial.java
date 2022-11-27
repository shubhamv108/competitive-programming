package code.shubham.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;

public class PowerOfPrimeFactorsOfNFactorial {


    class Solution {
        boolean[] SOE(int n) {
            boolean[] isPrime = new boolean[n+1];
            Arrays.fill(isPrime, true);
            isPrime[0] = isPrime[1] = false;
            double sqrt = Math.sqrt(n);
            for (int i = 2; i <= sqrt; i++)
                if (isPrime[i])
                    for (int j = i * i; j <= n; j+=i)
                        isPrime[j] = false;
            return isPrime;
        }

        ArrayList<int[]> get(int n) {
            ArrayList<int[]> result = new ArrayList<>();
            boolean[] isPrime = SOE(n);
            long factorial = 1;
            for (int i = 2; i <= n; i++) {
                if (isPrime[i]) {
                    int k = n, power = 0;
                    while(k > 1) {
                        k /= i;
                        power += k;
                    }
                    result.add(new int[] { i, power });
                    factorial *= Math.pow(i, power);
                }
            }
            System.out.println(factorial);
            return result;
        }

        ArrayList<int[]> getPowerOfPrimeFactorials(int n) {
            ArrayList<int[]> result = new ArrayList<>();
            boolean[] isPrime = SOE(n);
            long factorial = 1;
            for (int i = 2; i <= n; i++) {
                if (isPrime[i]) {
                    int power = 0;
                    result.add(new int[] { i, power = powerOfPrimeForFactorial(n, i) });
                    factorial *= Math.pow(i, power);
                }
            }
            System.out.println(factorial);
            return result;
        }

        int powerOfPrimeForFactorial(int n, int prime) {
            int t = n / prime;
            if (t == 0) return 0;
            return t + powerOfPrimeForFactorial(t, prime);
        }

        long factorial(int n) {
            boolean[] isPrime = SOE(n);
            long factorial = 1;
            for (int i = 2; i <= n; i++) {
                if (isPrime[i]) {
                    int k = n, power = 0;
                    while(k > 1) {
                        k /= i;
                        power += k;
                    }
                    factorial *= Math.pow(i, power);
                }
            }
            return factorial;
        }

        long NFactorial(int n) {
            boolean[] isPrime = SOE(n);
            long factorial = 1;
            for (int i = 2; i <= n; i++) {
                if (isPrime[i]) {
                    factorial *= Math.pow(i, powerOfPrimeForFactorial(n, i));
                }
            }
            return factorial;
        }
    }

    public static void main(String[] args) {
        ArrayList<int[]> l = new PowerOfPrimeFactorsOfNFactorial().new Solution().getPowerOfPrimeFactorials(100);
        l.stream().forEach(e -> System.out.println(e[0] + " " + e[1]));
        System.out.println(
                new PowerOfPrimeFactorsOfNFactorial().new Solution().factorial(100)
        );
    }

}
