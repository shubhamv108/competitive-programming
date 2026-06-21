package code.shubham.range;

import code.shubham.dynamicprogramming.maximizeminimum.MaximizeMinValue;
import code.shubham.dynamicprogramming.maximizeminimum.MaximizeMinValue.Solution;

public class SumOfCompatibleNumbersInRangeI {
    class Solution {
        public int sumOfGoodIntegers(int n, int k) {
            int sum = 0;
            int max = n + k;
            for (int x = Math.max(1, n - k); x <= max; ++x)
                if (Math.abs(n - x) <= k && (n & x) == 0)
                    sum += x;
            return sum;
        }
    }

    void main() {
//        System.out.println(new Solution().sumOfGoodIntegers(2 ,3));
        System.out.println(new Solution().sumOfGoodIntegers(1 ,13));
    }
}
