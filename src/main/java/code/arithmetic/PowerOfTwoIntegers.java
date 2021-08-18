package code.arithmetic;


/**
 * Given a positive integer which fits in a 32 bit signed integer,
 * find if it can be expressed as A^P where P > 1 and A > 0. A and P both should be integers.
 *
 * Input :
 * 9
 *
 * Output :
 * true
 */
public class PowerOfTwoIntegers {

    public class Solution1 {
        public int isPower(int A) {
            if (A == 1) return 1;
            for (int i = 2; i <= Math.sqrt(A); i++) {
                int j = 2;

                double p = Math.pow(i, j);

                while (p <= A && p > 0) {
                    if (p == A)
                        return 1;
                    j++;
                    p = Math.pow(i, j);
                }
            }
            return 0;
        }
    }

    /**
     * ToDo
     */
    public class Solution {
        public int isPower(int a) {
            if (a == 1)
                return 1;

            for (int i = 2; i * i <= a; i++) {
                double val = Math.log(a) / Math.log(i);
                if ((val - (int)val) < 0.00000001)
                    return 1;
            }

            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new PowerOfTwoIntegers().new Solution1().isPower(9) == 1 ? true : false
        );
    }

}
