package code.contestpractice.toptal.codility;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.stream.IntStream;

public class Question3 {
    class Solution {
        public <R> R solve() {
            return null;
        }
    }

    public static void main(String[] args) {
        Object result = new Question3().new Solution().<Object>solve();
        System.out.println(
            result
        );

        MathContext mathContext = new MathContext(34, RoundingMode.HALF_EVEN);
        BigDecimal a = new BigDecimal("200.0");
        BigDecimal b = new BigDecimal("300.0");
        System.out.println(a);
        System.out.println(b);
        System.out.println(a.divide(b, 34, RoundingMode.HALF_EVEN));
        System.out.println(a.divide(b, mathContext));
    }
}
